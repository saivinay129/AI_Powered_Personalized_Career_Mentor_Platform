import { useState } from "react";
import { signup } from "../services/auth";
import { useNavigate, Link } from "react-router-dom";

export default function Signup() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await signup(email, password);
      navigate("/chat");
    } catch (err) {
      alert(err?.response?.data?.message || "Signup failed");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div class="form-group">
        <label for="exampleInputEmail1">Email address</label>
        <br></br>
        <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" value={email} onChange={e => setEmail(e.target.value)} />
        <br></br>
        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
      </div>
      <br></br>
      <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <br></br>
        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" value={password} onChange={e => setPassword(e.target.value)} />
      </div>
      <br></br>
      <div class="form-group form-check">
        <small id="emailHelp" class="form-text text-muted">You have an account? <Link to="/login" class="btn btn-light" >login</Link></small>
      </div>
      <br></br>
      <button type="submit" class="btn btn-primary">Signup</button>
    </form>
  );
}


