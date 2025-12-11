import { BrowserRouter, Routes, Route } from "react-router-dom";

import TopHeader from "./components/TopHeader";
import Header from "./components/Header";
import Hero from "./components/Hero";

import LoginPage from "./pages/LoginPage";
import OtpPage from "./pages/OtpPage";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Home Page */}
        <Route
          path="/"
          element={
            <>
              <TopHeader />
              <Header />
              <Hero />
            </>
          }
        />

        {/* Login */}
        <Route path="/login" element={
          <>
          <TopHeader/>
          <Header/>
          <LoginPage />
          </>
          } />

        {/* OTP */}
        <Route path="/otp" element={ <>
          <TopHeader/>
          <Header/>
          <LoginPage />
          </>} />
      </Routes>
    </BrowserRouter>
  );
}
