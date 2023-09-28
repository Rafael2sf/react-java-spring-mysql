import axios from "axios";

export const getTasks = async () => {
  return axios.get("http://localhost:8080/tasks", { withCredentials: true });
};

export const createTask = async (title, description) => {
  console.log(title, description);
  return axios.post(
    "http://localhost:8080/tasks",
    { title, description },
    { withCredentials: true }
  );
};

export const deleteTask = async (id) => {
  return axios.delete("http://localhost:8080/tasks/" + id, {
    withCredentials: true,
  });
};
