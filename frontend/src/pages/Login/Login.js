import React, { useContext } from "react";
import LoginForm from "./components/LoginForm";
import "./Login.css";
import AuthContext from "../../global/AuthContext";

const Login = () => {
  const { user } = useContext(AuthContext);

  console.log(user);
  return (
    <div id="LoginPage">
      <img alt="logo" src="/logo.png"></img>
      <LoginForm />
    </div>
  );
};

export default Login;
