import API from "./api";

export const login = async (email, password) => {
  const res = await API.post("/chat/login", { email, password });
  localStorage.setItem("token", res.data.token);
  return res;
};

export const signup = async (email, password) => {
  const res = await API.post("/chat/signup", { email, password });
  localStorage.setItem("token", res.data.token);
  return res;
};

export const logout = () => {
  localStorage.removeItem("token");
};

export const isLoggedIn = () => !!localStorage.getItem("token");
