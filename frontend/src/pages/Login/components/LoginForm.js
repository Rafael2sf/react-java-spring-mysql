import { useContext, useState } from "react";
import "./LoginForm.css";
import AuthContext from "../../../global/AuthContext";

const LoginForm = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const { login, register } = useContext(AuthContext);

  const handleLogin = async (e) => {
    e.preventDefault();
    await login({ username, password });
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    await register({ username, password });
  };

  return (
    <div id="LoginForm">
      <form>
        <h1>Login</h1>
        <p className="item">
          <label htmlFor="username"> Username </label>
          <br />
          <input
            type="text"
            name="username"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </p>
        <p className="item">
          <label htmlFor="password"> Password </label>
          <br />
          <input
            type="password"
            name="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </p>
        <div className="flex-center-row">
          <p className="item">
            <input type="submit" value="Login" onClick={handleLogin} />
          </p>
          <p className="item">
            <input type="submit" value="Register" onClick={handleRegister} />
          </p>
        </div>
      </form>
    </div>
  );
};

export default LoginForm;
