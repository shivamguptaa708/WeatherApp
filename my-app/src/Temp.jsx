import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom"; 
import Register from "./Register";
import axios from "axios";

function Temp() {
  const [name, setNameValue] = useState("");
  const [password, setPasswordValue] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const data = {
      name: name,
      password: password
    };

    try {
      const response = await axios.post("http://localhost:8080/login", data, {
        headers: {
          "Content-Type": "application/json"
        }
      });

      console.log("Response data:", response.data);

      if (response.data === "fail") {
        alert("Invalid userId or password");
      } else {
        alert("Login Successfully");

        // Save JWT token in local storage
        localStorage.setItem("token", response.data);

        // Redirect to home page
        navigate("/home");
      }
    } catch (error) {
      if (error.response) {
        alert("Login failed: " + error.response.data);
      } else {
        alert("Network or server error");
      }
      console.error("Error during login:", error);
    }
  };

  return (
    <div className="container">
      <h1>Login</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="name">User ID</label>
          <input
            type="text"
            id="name"
            required
            placeholder="Enter your username"
            value={name}
            onChange={(e) => setNameValue(e.target.value)}
          />
        </div>

        <div>
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            required
            placeholder="Enter your password"
            value={password}
            onChange={(e) => setPasswordValue(e.target.value)}
          />
        </div>

        <div>
          <input type="checkbox" id="rememberMe" />
          <label htmlFor="rememberMe">Remember Me</label>
          <span style={{ marginLeft: "1rem" }}>Forgot Password?</span>
        </div>

        <button type="submit">Login</button>

        <div style={{ marginTop: "1rem" }}>
          <span>
            New here? <Link to="/register">Create an Account</Link>
          </span>
        </div>
      </form>
    </div>
  );
}

export default Temp;