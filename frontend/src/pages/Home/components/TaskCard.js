import React from "react";
import { deleteTask } from "../utils/Requests";

const TaskCard = ({ task, setTasks }) => {
  console.log(task);
  const handleDelete = (e) => {
    e.preventDefault();
    deleteTask(task.id)
      .then((res) => {
        setTasks((tasks) => tasks.filter((elem) => elem.id !== task.id));
      })
      .catch((e) => console.error(e));
  };
  return (
    <div className="task flex-collumn">
      <div className="flex-center-row">
        <h3>{task.title}</h3>
        <button onClick={handleDelete}>X</button>
      </div>
      <p>{task.description}</p>
      <span>{new Date(task.createdAt).toLocaleDateString()}</span>
    </div>
  );
};

export default TaskCard;
