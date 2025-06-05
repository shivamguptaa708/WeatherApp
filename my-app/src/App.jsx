import Temp from "./Temp"; 
import "./App.css";
import { BrowserRouter, Route, Routes  } from "react-router-dom";
import Register from "./Register";
import Home from "./Home";
 
function App() {
  return (
    
      <BrowserRouter>
      <Routes>
       <Route path="/login" element ={<Temp/>} />
       <Route path="/register" element ={<Register/>} />
       <Route path="/home" element={<Home />} /> 
      </Routes>

      </BrowserRouter>
      
  );
}

export default App;
