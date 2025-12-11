
export default function Hero() {
  return (
    <section className="relative w-full h-[85vh]">

      {/* Background Image */}
      <img
        src="/hero.png"
        className="absolute inset-0 w-full h-full object-cover"
      />

      {/* Dark Overlay */}
      <div className="absolute inset-0 bg-opacity-50"></div>

      {/* Content */}
      <div className="relative max-w-5xl mx-auto h-full flex flex-col justify-center px-6 text-white">
        
        <div className="bg-black/5 backdrop-blur-md p-8 rounded-lg w-fit">
          <h1 className="text-6xl font-semibold leading-tight">
            Snack Smarter Live <br/> Better.
          </h1>
        </div>

        <p className="mt-5 text-lg max-w-xl text-white/90">
          Real ingredients. Unreal flavor. The healthy boost your daily routine
          has been waiting for.
        </p>

        <button className="mt-6 px-2 py-3 bg-green-600 text-white rounded-lg font-semibold hover:bg-green-700 transition w-35">
          Shop Now
        </button>
      </div>
    </section>
  );
}
