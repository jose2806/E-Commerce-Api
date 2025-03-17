import axios from "axios";

const API_URL = "http://localhost:8080";

export const getProducts = async(token) =>{
  const response = await axios.get(`${API_URL}/products`,{
    headers:{Authorization: `Bearer ${token}`, "Content-Type":"application/json"}
  });
  return response.data;
};

export const getProductById = async (id) => {
  const response = await axios.get(`${API_URL}/products/${id}`);
  return response.data;  
};

export const addToCart = async (productId,quantity) => {
  const response = await axios.post(`${API_URL}/cart`, {productId,quantity});
  return response;  
};

export const loginUser = async (email,password) => {
  const response = await axios.post(`${API_URL}/login`,{email,password});
  return response;  
}

export const registerUser = async(name,email,password,role) =>{
  const response = await axios.post(`${API_URL}/users`,{name,email,password,role});
  return response;
}