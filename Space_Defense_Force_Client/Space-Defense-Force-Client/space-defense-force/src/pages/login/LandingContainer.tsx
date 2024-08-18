import { Button, Container, Grid, ToggleButton, ToggleButtonGroup } from "@mui/material";
import { useState } from "react";
import LaunchScreen from "./forms/LaunchScreen";
import OnboardingScreen from "./forms/OnboardingScreen";
import { FormState } from "../../types/LandingPageExports";
import { useNavigate } from 'react-router-dom';

export default function LandingContainer() {

  const [formState, setFormState] = useState<FormState>({
    tab: "Reset",
    mode: "Idle"
  })
  const navigate = useNavigate();

  const goToLoading = () => {
    navigate('/Welcome');
  };

  // Handler to update formState when a field changes
  const handleInputChange = (
    event: React.MouseEvent<HTMLElement>,
    newToggle: "Launch" | "Onboard" | "Reset" | null
  ) => {
    if (newToggle !== null) { // Prevent state update if the toggle is null
      setFormState({ ...formState, tab: newToggle });
      console.log(newToggle);
    }
  };

  const renderScreen = () => {
    switch (formState.tab) {
      case "Launch":
        return <LaunchScreen 
          formState={formState}
          setFormState={setFormState}
        />;
      case "Onboard":
        return <OnboardingScreen 
          formState={formState}
          setFormState={setFormState}
        />;
      default:
        return null; // Or return a default component if needed
    }
  };

  return(
    <Container maxWidth="sm" sx={{backgroundColor: "white", minHeight: '500px', opacity: "0.8", height: "500px"}}>
      <Grid id="toggle-button" spacing={24} padding={"5px"} border={"solid blue"} height={"15%"}>
          <ToggleButtonGroup
            color="primary"
            value={formState.tab}
            exclusive
            onChange={handleInputChange}
            aria-label="Platform"
          >
            <ToggleButton value="Launch" disabled={formState.mode == "Loading"} >Launch</ToggleButton>
            <ToggleButton value="Onboard" disabled={formState.mode == "Loading"}>Onboard</ToggleButton>
            <ToggleButton value="Reset" disabled={formState.mode == "Loading"}>Reset</ToggleButton>
          </ToggleButtonGroup>
      </Grid>
      <Grid id="form" spacing={24} padding={"5px"} height={"85%"} border={"solid yellow"}>
        
          { renderScreen() }
          {/*<Button variant="contained" onClick={goToLoading}>Animation</Button>*/}
        
      </Grid>
    </Container>
  );

}