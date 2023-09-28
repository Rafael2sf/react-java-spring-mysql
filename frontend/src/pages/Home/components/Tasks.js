import React from "react";
import TaskCard from "./TaskCard";

const Tasks = ({ tasks, setTasks }) => {
  const tasksRender = tasks.map((task, i) => (
    <TaskCard key={i} task={task} setTasks={setTasks} />
  ));

  return <div id="Tasks">{tasksRender}</div>;
};

export default Tasks;
