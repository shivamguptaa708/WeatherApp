import React from "react";
import axios from "axios";

  function Register() {

    const[register, setRegister] = React.useState({
      name : "",
      email : "",
      password : ""
  });

    const handleChange = (e)=> {
      setRegister({
          ...register,
          [e.target.name]: e.target.value
        })
    }

   const handleSubmit = async  (e) =>
     {
      e.preventDefault();
      console.log(register);

      try
      {
        const response =  await  axios.post("http://localhost:8080/addUser", register);
        console.log(response.data);
        alert("user added successfully");
      }
      catch(error)
      {
       console.log(error);
      }

    }
  


  return (
    <div className="container">
    <h1>Register</h1>
    <form onSubmit={handleSubmit}>
        <div>
            <label htmlFor = "username">Name</label>
            <input type="text" id="username" name="name" required  value={register.name} onChange = {handleChange} />
        </div>

      <div> 
        <label htmlFor="email">User Id</label>
        <input type="email" id="email" name="email" required  placeholder="enter email" value={register.email} onChange = {handleChange}/>
      </div>
  
      <div>
        <label htmlFor="password">Your Password</label>
        <input type="password" id="password" name="password" required value={register.password} onChange = {handleChange}/>
      </div>
  
      <button type="submit">Register</button>

    </form>
  </div>
  )
}

export default Register;
