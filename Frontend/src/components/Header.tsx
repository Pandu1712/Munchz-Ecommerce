
import { ShoppingCart, User, Search } from "lucide-react";

export default function Header() {
  return (
    <header className="w-full bg-white shadow-sm sticky top-0 z-50">
      <div className="max-w-7xl mx-auto px-4 py-4 flex items-center justify-between">

        {/* Logo */}
        <div className="flex items-center gap-3">
          <img
            src="/logo.png"
            alt="Munchz"
            className="h-18 w-full object-cover"
          />
        {/*   <span className="text-2xl font-bold text-gray-700">Munchz</span> */}
        </div>

        {/* Menu */}
        <nav className="hidden md:flex gap-10 text-lg font-medium text-gray-700">
          <a href="/" className="hover:text-green-600">Home</a>
          <a href="#" className="hover:text-green-600">Shop</a>
          <a href="#" className="hover:text-green-600">About</a>
          <a href="#" className="hover:text-green-600">Track</a>
          <a href="#" className="hover:text-green-600">Contact</a>
        </nav>

        {/* Search + Icons */}
        <div className="flex items-center gap-4">
          {/* Search Bar */}
          <div className="hidden md:flex items-center border rounded-full px-4 py-2 gap-2 w-72">
            <input
              type="text"
              placeholder="Search"
              className="outline-none w-full"
            />
            <Search className="h-5 w-5 text-gray-600" />
          </div>

          <ShoppingCart className="h-6 w-6 text-gray-700 cursor-pointer" />
          <a href="/login">
          <User className="h-6 w-6 text-gray-700 cursor-pointer" />
          </a>
        </div>
      </div>
    </header>
  );
}
