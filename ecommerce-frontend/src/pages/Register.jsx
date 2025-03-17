import {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import { registerUser } from '../services/api';
import "../style/Register.css";

function Register() {
  const [name, setName] = useState("");
  const [email,setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  
  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    try {
      const response = await registerUser(name,email,password);
      if(response.status === 201){
        alert("Cuenta creada con exito");
        navigate("/login");
      }
    } catch (error) {
      setError("Error al registrar. Intenta de nuevo.");
      console.log(error.message);  
    }
  };

  return(
    <div className='register-container'>
      <h2>Registrarse</h2>
      {error && <p className='error'>{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Nombre:</label>
          <input type='text' value={name} onChange={(e) => setName(e.target.value)} 
          required autoComplete="name" />   
        </div>
        <div>
          <label>Email:</label>
          <input type='email' value={email} onChange={(e)=>setEmail(e.target.value)} 
          required autoComplete="email" />
        </div>
        <div>
          <label>Contraseña:</label>
          <input type='password' value={password} onChange={(e) => setPassword(e.target.value)} 
          required autoComplete="current-password" />
        </div>
        <button type='submit'>Registrarse</button>
        <button className="back" onClick={() => navigate("/login")}>Volver al login</button>
      </form>
    </div>
  )
}

export default Register;