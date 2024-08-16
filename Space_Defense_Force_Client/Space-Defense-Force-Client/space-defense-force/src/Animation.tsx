import { RocketLaunch, Album } from "@mui/icons-material";
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import './css/animation.css';

export default function Animation() {

  const navigate = useNavigate();
  const [animationStage, setAnimationStage] = useState(0)
  const [meteorStage, setMeteorStage] = useState({
    first: 0,
    second: 0
  })

  useEffect(() => {
    const delay = (ms: number) => new Promise((resolve) => setTimeout(resolve, ms));

    const startAnimation = async () => {
      await delay(5000);
      setAnimationStage(2);
      setMeteorStage({
        first: 1,
        second: 0,
      });

      await delay(2500); // Delay between the stages
      setAnimationStage(3);
      setMeteorStage({
        first: 1,
        second: 2,
      });

      await delay(2500); // Delay between the stages
      setAnimationStage(4);

      await delay(2500); // Delay between the stages
      setAnimationStage(5);

      // After all the animations are done, navigate to the home page
      await delay(2500); // Delay before navigating to home
      navigate('/home');
    };

    setAnimationStage(1); // Initial stage
    startAnimation();
    
  }, [])

  return(
    <div style={{ width: "100vw", height: "100vh", display: "grid", placeItems: "center" }} >
      <div className={`icon stage-${animationStage}`}>
        <RocketLaunch style={{ fontSize: 'inherit', color: "red"}}/>
      </div>
      <div className={`meteor-icon meteor-${meteorStage.first}`}>
        <Album style={{ fontSize: 'inherit', color: 'brown'}}/>
      </div>
      <div className={`meteor-icon-2 meteor-${meteorStage.second}`}>
        <Album style={{ fontSize: 'inherit', color: 'brown'}}/>
      </div>
    </div>
  )

}