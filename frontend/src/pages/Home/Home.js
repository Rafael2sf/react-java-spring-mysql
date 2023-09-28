import React, { useEffect, useContext, useState } from "react";
import AuthContext from "../../global/AuthContext";
import Menu from "./components/Menu";
import { getTasks } from "./utils/Requests";
import Tasks from "./components/Tasks";
import CreateTask from "./components/CreateTask";
import "./Home.css";

const Home = () => {
  const [tasks, setTasks] = useState([]);
  const { logout, user } = useContext(AuthContext);
  const [activeMenu, setActiveMenu] = useState(0);

  useEffect(() => {
    getTasks()
      .then((res) => {
        setTasks(res.data);
      })
      .catch((e) => console.error(e));
  }, []);

  return (
    <div id="HomePage" className="flex-center-collumn">
      <Menu user={user} logout={logout} setActiveMenu={setActiveMenu} />
      {activeMenu === 0 ? (
        <Tasks tasks={tasks} setTasks={setTasks} />
      ) : (
        <CreateTask setTasks={setTasks} setActiveMenu={setActiveMenu} />
      )}
    </div>
  );
};

export default Home;
