import { Container, Grid, ToggleButton, ToggleButtonGroup } from "@mui/material";
import { useState } from "react";
import LaunchScreen from "./LaunchScreen";
import OnboardingScreen from "./OnboardingScreen";
import { FormState } from "./types/LandingPageExports";

export default function LandingContainer() {

  const [formState, setFormState] = useState<FormState>({
    tab: "Reset",
    mode: "Idle"
  })

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
    <Container maxWidth="sm" style={{backgroundColor: "white"}}>
      <Grid id="toggle-button" container spacing={24} padding={"5px"}>
        <Grid item xs={24}>
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
      </Grid>
      <Grid id="form" container spacing={24} padding={"5px"}>
        <Grid item xs={24}>
          { renderScreen() }
        </Grid>
      </Grid>
    </Container>
  );

}