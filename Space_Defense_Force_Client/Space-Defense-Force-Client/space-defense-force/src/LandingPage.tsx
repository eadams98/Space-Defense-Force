import { SetStateAction, useEffect, useRef, useState } from "react";
import OnboardingScreen from "./OnboardingScreen";
import StarfieldCanvas from "./starfieldCanvas";
import LaunchScreen from "./LaunchScreen";
import LandingContainer from "./LandingContainer";
import Animation from "./Animation";
import HomePage from "./Homepage";
import { Outlet } from "react-router-dom";



export default function LandingPage() {
  const [showAnimation, setShowAnimation] = useState(0)
  const [loggedIn, setLoggedIn] = useState(false);
  return(
    <div style={{ width: "100vw", height: "100vh", display: "grid", placeItems: "center"}}>
      <StarfieldCanvas></StarfieldCanvas>
      <Outlet />
    </div>
  );
}