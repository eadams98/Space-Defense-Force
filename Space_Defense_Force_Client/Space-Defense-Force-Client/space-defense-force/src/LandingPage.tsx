import { useEffect, useRef } from "react";
import OnboardingScreen from "./OnboardingScreen";
import StarfieldCanvas from "./starfieldCanvas";
import LaunchScreen from "./LaunchScreen";
import LandingContainer from "./LandingContainer";



export default function LandingPage() {
  return(
    <div style={{ width: "100vw", height: "100vh", display: "grid", placeItems: "center"}}>
      <StarfieldCanvas></StarfieldCanvas>
      {/*<OnboardingScreen></OnboardingScreen>*/}
      {/*<LaunchScreen></LaunchScreen>*/}
      {<LandingContainer></LandingContainer>}
    </div>
  );
}