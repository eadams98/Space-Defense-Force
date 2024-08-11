import { Button, Container, Grid, TextField } from "@mui/material";
import { useEffect, useState } from "react";
import { LaunchScreenProps } from "./types/LandingPageExports";
import LoadingEllipse from "./utility/loading";
import { Rocket, RocketLaunch } from "@mui/icons-material";

export default function LaunchScreen({
  formState: formToggleState,
  setFormState: setFormToggleState,
}: LaunchScreenProps) {
  const [formState, setFormState] = useState({
    email: "",
    password: "",
  })
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
    if (formState.email == "" || formState.password == "" ) {
      setIsDisabled(true);
      return;
    }

    setIsDisabled(false)
  },[formState])

  const handleBoardRequest = () => {
    setFormToggleState({
      ...formToggleState,
      mode: "Loading"
    })
    setTimeout(() => {
      setFormToggleState({
        ...formToggleState,
        mode: "Idle"
      })
    }, 10000)
  }

  return(
    <Container maxWidth="sm" style={{backgroundColor: "white", display: "grid", placeItems: "center"}}>
      <h3>Launch Screen</h3>
      <Grid id="top-row" container spacing={24} padding={"5px"}>
        <Grid item xs={12}>
          <TextField
              id="outlined-basic"
              label="Email"
              variant="outlined"
              name="email"
              value={formState.email}
              onChange={handleInputChange}
              disabled={formToggleState.mode == "Loading"}
            />
        </Grid>
      </Grid>
      <Grid id="middle-row" container spacing={24}  padding={"5px"}>
      </Grid>
      <Grid id="bottom-row" container spacing={24} padding={"5px"}>
        <Grid item xs={12}>
          <TextField
              id="standard-basic"
              label="Password"
              variant="standard"
              name="password"
              value={formState.password}
              onChange={handleInputChange} // Update state when input changes
              disabled={formToggleState.mode == "Loading"}
            />
        </Grid>
      </Grid>
      <Grid id="bottom-row" container spacing={24} padding={"5px"}>
        <Grid item xs={12}>
          <Button variant="contained" disabled={isDisabled || formToggleState.mode == "Loading"} onClick={handleBoardRequest}> {formToggleState.mode == "Loading" ? (<><LoadingEllipse/><RocketLaunch/></>) : <Rocket/>} </Button>
        </Grid>
      </Grid>
    </Container>
  );

}