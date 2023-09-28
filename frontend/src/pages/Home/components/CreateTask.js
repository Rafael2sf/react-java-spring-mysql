import React, { useState } from "react";
import { createTask } from "../utils/Requests";

const CreateTask = ({ setTasks, setActiveMenu }) => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    createTask(title, description)
      .then((res) => {
        setTasks((prevTasks) => [...prevTasks, res.data]);
        setActiveMenu(0);
      })
      .catch((e) => console.error(e));
  };

  return (
    <div id="CreateTaskForm">
      <form>
        <h1>Create Task</h1>
        <p className="item">
          <label htmlFor="title"> title </label>
          <br />
          <input
            type="text"
            name="title"
            id="TaskTitle"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </p>
        <p className="item">
          <label htmlFor="description"> description </label>
          <br />
          <textarea
            name="description"
            id="TaskDescription"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
        </p>
        <p className="item">
          <input type="submit" value="create" onClick={handleSubmit} />
        </p>
      </form>
    </div>
  );
};

export default CreateTask;
