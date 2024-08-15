import { SetStateAction, useEffect, useRef, useState } from "react";
import OnboardingScreen from "./OnboardingScreen";
import StarfieldCanvas from "./starfieldCanvas";
import LaunchScreen from "./LaunchScreen";
import LandingContainer from "./LandingContainer";
import Animation from "./Animation";
import HomePage from "./Homepage";



export default function LandingPage() {
  const [showAnimation, setShowAnimation] = useState(0)
  const [loggedIn, setLoggedIn] = useState(false);
  return(
    <div style={{ width: "100vw", height: "100vh", display: "grid", placeItems: "center"}}>
      {<StarfieldCanvas></StarfieldCanvas>}
      {/*<OnboardingScreen></OnboardingScreen>*/}
      {/*<LaunchScreen></LaunchScreen>*/}
      {showAnimation == 0 ? <LandingContainer toggleAnimation={setShowAnimation}></LandingContainer> : null}
      {showAnimation == 1 ? <Animation toggleStatus={setShowAnimation}></Animation> : null}
      {showAnimation == 2 ? <HomePage/> : null}
    </div>
  );
}