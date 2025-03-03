import axios from "axios"

// In-memory storage for the access token
let accessToken: string | null = null

export const setAccessToken = (token: string | null) => {
  accessToken = token
}

// Base URL
const API_BASE_URL = "http://localhost:8765"

// Create Custom Axios
const api = axios.create({
  baseURL: API_BASE_URL,
  withCredentials: true // Allows sending cookies (for refresh token)
})

api.interceptors.request.use(
  (config) => {
    if (accessToken) {
      config.headers.Authorization = `Bearer ${accessToken}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

api.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;

    if ((error.response?.status === 401 || error.response?.status === 500) && !originalRequest._retry) {
      console.log(error.response)

      try {
        // Request new access token
        const {data} = await axios.post(`${API_BASE_URL}/auth/refresh`, {},{withCredentials: true})

        setAccessToken(data);

        //Retry the original request
        originalRequest.headers.Authorization = `Bearer ${data.accessToken}`;
        return api(originalRequest);
      } catch (refreshError) {
        console.error("Refresh token failed, logging out...");
        logoutUser();
      }
    }

    return Promise.reject(error);
  }
);

// Function to log out user
export const logoutUser = () => {
  setAccessToken(null);
  window.location.href = "/"; // back to whence you came. You shall not pass
};

export default api;