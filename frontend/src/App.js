import { Navigate, Route, Routes } from "react-router-dom";
import Login from "./pages/Login/Login.js";
import Home from "./pages/Home/Home.js";
import ProtectedRoute from "./global/ProtectedRoute.js";
import "./App.css";
import { useContext, useEffect, useState } from "react";
import AuthContext from "./global/AuthContext.js";

function App() {
  const { load } = useContext(AuthContext);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const isLoggedIn = async () => {
      await load();
    };
    isLoggedIn();
    setIsLoading(false);
  }, [load]);

  return (
    <div className="app">
      {isLoading ? (
        <p>Loading ...</p>
      ) : (
        <Routes>
          <Route
            exact
            path="/"
            element={
              <ProtectedRoute>
                <Home />
              </ProtectedRoute>
            }
          />
          <Route path="/login" element={<Login />} />
          <Route path="*" element={<Navigate to="/" />} />
        </Routes>
      )}
    </div>
  );
}

export default App;
