import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8081", // your backend URL
});

export const sendLoginOtp = async (emailOrPhone: string) => {
  return API.post("/auth/login-otp", { email: emailOrPhone });
};

export const confirmLoginOtp = async (emailOrPhone: string, otp: string) => {
  return API.post("/auth/login-otp/confirm", { email: emailOrPhone, otp });
};
