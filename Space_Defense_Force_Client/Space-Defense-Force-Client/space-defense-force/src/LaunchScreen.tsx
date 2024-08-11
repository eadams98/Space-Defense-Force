import { Button, Container, Grid, TextField } from "@mui/material";
import { useEffect, useState } from "react";
import { LaunchScreenProps } from "./types/LandingPageExports";
import LoadingEllipse from "./utility/loading";
import { Rocket, RocketLaunch } from "@mui/icons-material";
import axios from "axios";

export default function LaunchScreen({
  formState: formToggleState,
  setFormState: setFormToggleState,
}: LaunchScreenProps) {
  const [formState, setFormState] = useState({
    username: "",
    password: "",
  });
  const [isDisabled, setIsDisabled] = useState(true);

  // Handler to update formState when a field changes
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormState({
      ...formState,
      [name]: value,
    });
  };

  useEffect(() => {
    if (formState.username === "" || formState.password === "") {
      setIsDisabled(true);
      return;
    }

    setIsDisabled(false);
  }, [formState]);

  const handleBoardRequest = async () => {
    setFormToggleState({
      ...formToggleState,
      mode: "Loading"
    });
    const fetchData = async () => {
      try {
        const response = await axios.post<any>("http://localhost:8765/auth/signin", formState);
        setFormToggleState({
          ...formToggleState,
          mode: "Idle"
        });
        // Handle successful response here (e.g., store tokens, navigate to another page, etc.)
      } catch (err) {
        setFormToggleState({
          ...formToggleState,
          mode: "Idle"
        });
        // Handle error here (e.g., display error message)
      }
    }
    fetchData()
  };

  return (
    <Container
      maxWidth="sm"
      style={{ backgroundColor: "white", display: "grid", placeItems: "center" }}
    >
      <h3>Launch Screen</h3>
      <Grid id="top-row" container spacing={2} padding={"5px"}>
        <Grid item xs={12}>
          <TextField
            id="outlined-basic"
            label="Email"
            variant="outlined"
            name="username"
            value={formState.username}
            onChange={handleInputChange}
            disabled={formToggleState.mode === "Loading"}
          />
        </Grid>
      </Grid>
      <Grid id="middle-row" container spacing={2} padding={"5px"} />
      <Grid id="bottom-row" container spacing={2} padding={"5px"}>
        <Grid item xs={12}>
          <TextField
            id="standard-basic"
            label="Password"
            variant="standard"
            name="password"
            type="password"
            value={formState.password}
            onChange={handleInputChange}
            disabled={formToggleState.mode === "Loading"}
          />
        </Grid>
      </Grid>
      <Grid id="bottom-row" container spacing={2} padding={"5px"}>
        <Grid item xs={12}>
          <Button
            variant="contained"
            disabled={isDisabled || formToggleState.mode === "Loading"}
            onClick={handleBoardRequest}
          >
            {formToggleState.mode === "Loading" ? (
              <>
                <LoadingEllipse />
                <RocketLaunch />
              </>
            ) : (
              <Rocket />
            )}
          </Button>
        </Grid>
      </Grid>
    </Container>
  );
}
