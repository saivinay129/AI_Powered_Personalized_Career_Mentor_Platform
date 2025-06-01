# AI_Powered_Personalized_Career_Mentor_Platform
An intelligent career guidance platform that provides users with personalized career advice based on their goals and current skills. Leveraging cutting-edge technologies like LLMs (via Spring AI), JWT-based authentication, and MongoDB, the platform empowers users with actionable guidance for their career growth.

🚀 Features
✅ User Authentication

Secure login and signup with JWT-based authentication

Role-based access for authorized users

🎯 Personalized Career Advice

Users input their career goals and current skill set

Platform generates tailored guidance using LLMs (e.g., OpenAI, Gemini via Spring AI)

📦 Advice Management

Advice saved and linked to each user using MongoDB

Each entry includes goals, skills, and AI-generated suggestions

🛠️ Modern Tech Stack

Frontend: React.js

Backend: Java Spring Boot with Spring AI

Database: MongoDB (with user-advice relationship using foreign keys)

Authentication: JSON Web Tokens (JWT)

🧱 Architecture
plaintext
React.js (Frontend)
      ⬇
Spring Boot API (Backend) ─────> Spring AI + LLM (Gemini/OpenAI)
      ⬇
   MongoDB (User & Advice Storage)

🛠️ Installation (Local Setup)
Clone the Repository

git clone https://github.com/saivinay129/AI_Powered_Personalized_Career_Mentor_Platform.git
cd AI_Powered_Personalized_Career_Mentor_Platform


# Frontend
cd frontend_project
npm install
npm start


# Backend
Configure application.properties with MongoDB URI and OpenAI/Gemini API keys.

Build and run the Spring Boot app:

mvn clean install
mvn spring-boot:run


💡 Future Enhancements
Dashboard with progress tracking

Email summaries of advice

Integration with job portals (e.g., LinkedIn API)

Resume analysis and feedback

👨‍💻 Author
Sai Vinay
Aspiring Software Developer | Passionate about AI & Career-Tech

