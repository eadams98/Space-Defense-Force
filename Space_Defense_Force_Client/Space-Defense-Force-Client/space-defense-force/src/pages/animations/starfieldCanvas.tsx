import { useEffect, useRef } from "react";

const StarfieldCanvas = () => {
  const canvasRef = useRef<HTMLCanvasElement>(null);

  useEffect(() => {
    const canvas = canvasRef.current;
    if (!canvas) 
      return;

    const ctx = canvas.getContext('2d');
    if (!ctx) 
      return;

    const stars: any = [];
    const numStars = 200;
    const resizeCanvas = () => {
      canvas.width = window.innerWidth;
      canvas.height = window.innerHeight;
    };

    // Resize canvas on window resize
    window.addEventListener('resize', resizeCanvas);
    resizeCanvas();

    // initialize stars
    for (let i = 0; i < numStars; i++) {
      stars.push({
        x: Math.random() * canvas.width,
        y: Math.random() * canvas.height,
        size: Math.random() * 2,
        speed: Math.random() * .5 + .2
      })
    }

    const update = () => {
      ctx.clearRect(0,0, canvas.width, canvas.height);
      for (let star of stars) {
        star.x += star.speed;

        if (star.x > canvas.width) {
          star.x = 0;
          star.y = Math.random() * canvas.width;
        }

        ctx.fillStyle = "white"
        ctx.beginPath();
        ctx.arc(star.x, star.y, star.size, 0, Math.PI * 2);
        ctx.fill();
      }
      requestAnimationFrame(update);
    };
    update();

    return () => {
      window.removeEventListener('resize', resizeCanvas);
    };
  }, []);

  return <canvas
    ref={canvasRef}
    style={{ position: "fixed", top: 0, left: 0, zIndex:-1, backgroundColor: "black" }} />;
};

export default StarfieldCanvas;