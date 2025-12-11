import  { useState, useEffect } from "react";
import { confirmLoginOtp } from "../api/api";
import { useLocation } from "react-router-dom";

export default function OtpPage() {
  const location = useLocation();
  const emailOrPhone = location.state?.emailOrPhone;

  const [otp, setOtp] = useState(["", "", "", "", "", ""]);
  const [timer, setTimer] = useState(50);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const countdown = setInterval(() => {
      setTimer((prev) => (prev > 0 ? prev - 1 : 0));
    }, 1000);

    return () => clearInterval(countdown);
  }, []);

  const handleOtpChange = (value: string, index: number) => {
    if (!/^[0-9]?$/.test(value)) return;

    const updated = [...otp];
    updated[index] = value;
    setOtp(updated);
  };

  const handleVerify = async () => {
    const enteredOtp = otp.join("");

    if (enteredOtp.length !== 6) {
      alert("Enter full 6-digit OTP");
      return;
    }

    setLoading(true);
    try {
      const res = await confirmLoginOtp(emailOrPhone, enteredOtp);
      localStorage.setItem("token", res.data.token);
      alert("Login successful!");
        window.location.href = "/";
    } catch {
      alert("Invalid OTP");
    }
    setLoading(false);
  };

  return (
    <div className="min-h-screen flex items-center justify-center">
      <div className="bg-white w-[450px] rounded-lg shadow-xl p-10 text-center">

        <img src="/logo.png" className="w-20 mx-auto" />
        <h1 className="text-2xl font-semibold mt-4">Munchz</h1>

        <h2 className="text-xl font-medium mt-4">Enter OTP</h2>

        {/* OTP Boxes */}
        <div className="flex justify-center gap-4 mt-8">
          {otp.map((digit, i) => (
            <input
              key={i}
              type="text"
              maxLength={1}
              value={digit}
              onChange={(e) => handleOtpChange(e.target.value, i)}
              className="border-b-2 border-gray-500 w-10 text-center text-xl focus:outline-none"
            />
          ))}
        </div>

        <p className="text-gray-600 mt-4">
          A One-Time Password (OTP) has been sent to your registered mobile
          number or email address.
        </p>

        <p className="text-green-600 mt-3 text-sm">
          Resend OTP in : {timer} sec
        </p>

        <button
          onClick={handleVerify}
          className="bg-green-600 text-white w-full py-3 rounded-lg mt-8 font-semibold"
        >
          {loading ? "Verifying..." : "Login"}
        </button>

        <a href="/signup" className="text-green-600 block mt-4 underline">
          Sign up
        </a>
      </div>
    </div>
  );
}
