import React, { useState, useEffect} from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";


const Home = () => {
    const [city, setCity] = useState("");
    const [weather, setWeather] = useState(null);
    const [error, setError] = useState("");

    const navigate = useNavigate();

    useEffect(()=> {
        const token = localStorage.getItem("token");
        if(!token)
        {
            navigate("/login");
        }
    }, [navigate]);
 
    const fetchWeather = async () => {
        const token = localStorage.getItem("token");
        if (!token) {
            navigate("/login");
            return;
        }

        try {
            const response = await axios.get(`http://localhost:8080/api/weather/${city}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            setWeather(response.data);
            setError("");
        } catch (err) {
            console.error("Error fetching weather:", err);
            
            // 🔁 If token expired or invalid
            if (err.response?.status === 401 || err.response?.status === 403) {
                localStorage.removeItem("token");
                navigate("/login");
            } else {
                setError("Error fetching data. Please try again.");
            }

            setWeather(null);
        }
    };

    return (
        <div className="flex flex-col items-center bg-blue-200 p-6 rounded-xl shadow-lg w-96">
            <h2 className="text-2xl font-bold mb-4">Weather App</h2>
            <input
                type="text"
                value={city}
                onChange={(e) => setCity(e.target.value)}
                placeholder="Enter city name"
                className="p-2 border rounded w-full"
            />
            <button onClick={fetchWeather} className="mt-3 bg-blue-500 text-white p-2 rounded w-full">
                Get Weather
            </button>

            {error && <p className="text-red-500 mt-2">{error}</p>}

            {weather && (
                <div className="mt-4 p-4 bg-white rounded-lg shadow-md w-full">
                    <h3 className="text-xl font-semibold">{weather.name}, {weather.sys.country}</h3>
                    <p className="text-gray-700">🌡 Temperature: {weather.main.temp}°C</p>
                    <p className="text-gray-700">🤒 Feels Like: {weather.main.feels_like}°C</p>
                    <p className="text-gray-700">🌤 Weather: {weather.weather[0].description}</p>
                    <p className="text-gray-700">💧 Humidity: {weather.main.humidity}%</p>
                    <p className="text-gray-700">💨 Wind Speed: {weather.wind.speed} m/s</p>
                    <p className="text-gray-700">🌅 Sunrise: {new Date(weather.sys.sunrise * 1000).toLocaleTimeString()}</p>
                    <p className="text-gray-700">🌇 Sunset: {new Date(weather.sys.sunset * 1000).toLocaleTimeString()}</p>
                </div>
            )}
        </div>
    );
};

export default Home;
