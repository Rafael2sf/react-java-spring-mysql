import React from "react";

const Menu = ({ user, logout, setActiveMenu }) => {
  const handleActiveMenu = (e, value) => {
    e.preventDefault();
    setActiveMenu(value);
  };

  const logoutHandler = (e) => {
    e.preventDefault();
    logout();
  };

  return (
    <div id="TaskMenu" className="flex-center-collumn">
      <h1>{user}</h1>
      <div>
        <button onClick={(e) => handleActiveMenu(e, 0)}>My Tasks</button>
        <button onClick={(e) => handleActiveMenu(e, 1)}>Create Task</button>
        <button onClick={logoutHandler}>Logout</button>
      </div>
    </div>
  );
};

export default Menu;
