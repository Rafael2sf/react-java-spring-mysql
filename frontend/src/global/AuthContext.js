import axios from "axios";
import { createContext, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

const AuthContext = createContext();

export const AuthContextProvider = ({ children }) => {
  const [user, setUser] = useState("");
  const navigate = useNavigate();
  const location = useLocation();

  const load = async () => {
    if (user) return;
    try {
      const res = await axios.get("http://localhost:8080/user", {
        withCredentials: true,
      });

      if (res.data) {
        setUser(res.data.username);
        navigate("/");
      }
    } catch (e) {
      if (location.pathname !== "/login") navigate("/login");
    }
  };

  const login = async (payload) => {
    try {
      await axios.post("http://localhost:8080/auth/signin", payload, {
        withCredentials: true,
      });
      setUser(payload.username);
      navigate("/");
    } catch (e) {}
  };

  const register = async (payload) => {
    try {
      await axios.post("http://localhost:8080/auth/signup", payload, {
        withCredentials: true,
      });
      setUser(payload.username);
      navigate("/");
    } catch (e) {}
  };

  const logout = async () => {
    try {
      await axios.post(
        "http://localhost:8080/auth/logout",
        {},
        {
          withCredentials: true,
        }
      );
      setUser(null);
      navigate("/login");
    } catch (e) {}
  };

  return (
    <>
      <AuthContext.Provider value={{ user, load, login, register, logout }}>
        {children}
      </AuthContext.Provider>
    </>
  );
};

export default AuthContext;
