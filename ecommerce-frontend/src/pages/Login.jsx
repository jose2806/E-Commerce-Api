import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../services/api";
import "../style/Login.css";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleSubmit = async(e) =>{
    e.preventDefault();
    setError(null);

    try {
      const response = await loginUser(email,password);
      if(response.data.jwtToken){
        localStorage.setItem("token", response.data.jwtToken);
        navigate("/home")
      } else{
        setError("Credenciales incorrectas")
      }    
    } catch (error) {
      setError("Error al iniciar sesión. Verifica tus datos.");
      setError(error.message);
    }
  };

  return(
    <div className="login-container">
      <h2>Iniciar sesion</h2>
      {error && <p className="error">{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Email:</label>
          <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} 
          required autoComplete="email" />
        </div>
        <div>
          <label>Contraseña:</label>
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} 
          required  autoComplete="current-password" />
        </div>
        <button className="login" type="submit">Iniciar sesion</button>
        <button className="signin" onClick={() => navigate("/register")}>Crear Cuenta</button>
      </form>
    </div>
  );
}

export default Login;