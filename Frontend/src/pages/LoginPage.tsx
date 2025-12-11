import { useState } from "react";
import { sendLoginOtp } from "../api/api";
import { useNavigate } from "react-router-dom";

export default function LoginPage() {
  const [emailOrPhone, setEmailOrPhone] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSendOtp = async () => {
    if (!emailOrPhone) return alert("Please enter Email or Phone");

    setLoading(true);
    try {
      await sendLoginOtp(emailOrPhone);
      navigate("/otp", { state: { emailOrPhone } });
    } catch (e) {
      alert("Failed to send OTP");
    }
    setLoading(false);
  };

  return (
    <div className="min-h-screen flex items-center justify-center ">
      <div className="bg-white w-[450px] rounded-lg shadow-xl p-10 text-center">

        <img src="/logo.png" className="w-20 mx-auto" />
        <h1 className="text-2xl font-semibold mt-4">Munchz</h1>

        <input
          type="text"
          placeholder="Email id or Phone number"
          className="border border-green-600 w-full mt-10 py-3 px-4 rounded-md"
          value={emailOrPhone}
          onChange={(e) => setEmailOrPhone(e.target.value)}
        />

        <button
          onClick={handleSendOtp}
          className="bg-green-600 text-white w-full py-3 rounded-lg mt-10 font-semibold"
        >
          {loading ? "Sending..." : "Send OTP"}
        </button>

        <a href="/signup" className="text-green-600 block mt-4 underline">
          Sign up
        </a>
      </div>
    </div>
  );
}
