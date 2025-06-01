import React, { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import { isLoggedIn, logout } from "../services/auth";  // Added logout import
import axios from "axios";
import API from "../services/api";
import "bootstrap/dist/css/bootstrap.min.css";

const Chatbot = () => {
  const [goal, setGoal] = useState("");
  const [skills, setSkills] = useState("");
  const [messages, setMessages] = useState([]);
  const chatEndref = useRef(null);
  const [isSubmitted, setIsSubmitted] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    if (!isLoggedIn()) navigate("/login");
  }, []);

  const sendMessage = async () => {
    if (!goal.trim() || !skills.trim()) return;

    const userMessage = {
      text: `Goal: ${goal}\nSkills: ${skills}`,
      sender: "user",
    };
    setMessages((prev) => [...prev, userMessage]);
    setIsSubmitted(true);

    try {
      const res = await API.post("/chat", { goal, skills });
      const botMessage = { text: res.data, sender: "bot" };
      setMessages((prev) => [...prev, botMessage]);
      setGoal("");
      setSkills("");
      setIsSubmitted(false);
    } catch (error) {
      console.error("Error fetching response", error);
      setMessages((prev) => [
        ...prev,
        { text: "Error retrieving response", sender: "bot" },
      ]);
      setIsSubmitted(false);
    }
  };

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  useEffect(() => {
    chatEndref.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  return (
    <div className="container mt-5">
      <div className="d-flex justify-content-end mb-2">
        <button className="btn btn-danger" onClick={handleLogout}>
          Logout
        </button>
      </div>
      <div className="card shadow-lg">
        <div className="card-header bg-primary text-white text-center">
          <h4>Career Mentor</h4>
        </div>
        <div
          className="card-body chat-box"
          style={{ height: "400px", overflowY: "auto" }}
        >
          {messages.map((msg, index) => (
            <div
              key={index}
              className={`d-flex ${
                msg.sender === "user"
                  ? "justify-content-end"
                  : "justify-content-start"
              }`}
            >
              <div
                className={`p-3 rounded shadow ${
                  msg.sender === "user"
                    ? "bg-primary text-white"
                    : "bg-light text-dark"
                }`}
              >
                {msg.text}
              </div>
            </div>
          ))}
          <div ref={chatEndref} />
        </div>
        <div className="card-footer">
          <div className="mb-2">
            <input
              type="text"
              className="form-control mb-2"
              placeholder="Enter your career goal..."
              value={goal}
              onChange={(e) => setGoal(e.target.value)}
              disabled={isSubmitted}
            />
            <input
              type="text"
              className="form-control"
              placeholder="Enter your skills..."
              value={skills}
              onChange={(e) => setSkills(e.target.value)}
              disabled={isSubmitted}
            />
          </div>
          <button
            className="btn btn-primary w-100"
            onClick={sendMessage}
            disabled={isSubmitted}
          >
            {isSubmitted ? "Submitted" : "Send"}
          </button>
        </div>
      </div>
    </div>
  );
};

export default Chatbot;
