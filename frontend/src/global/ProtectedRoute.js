import { useContext } from "react";
import { Navigate } from "react-router-dom";
import AuthContext from "./AuthContext";

const ProtectedRoute = ({ children }) => {
  const { user } = useContext(AuthContext);
  console.log(user);
  if (user) return children;
  return <Navigate to="/"></Navigate>;
};
export default ProtectedRoute;
