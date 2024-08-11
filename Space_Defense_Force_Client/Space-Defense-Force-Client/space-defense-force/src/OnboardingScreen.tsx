import { Button, Container, Grid, TextField } from "@mui/material";
import { useEffect, useState } from "react";
import LoadingEllipse from "./utility/loading";
import { Rocket, RocketLaunch } from "@mui/icons-material";
import { BoardBtnState, OnboardingScreenProps } from "./types/LandingPageExports";

export default function OnboardingScreen({
  formState: formToggleState,
  setFormState: setFormToggleState,
}: OnboardingScreenProps) {
  const [formState, setFormState] = useState({
    email: "",
    password: "",
    matchPassword: "",
    username: ""
  })
  const [isDisabled, setIsDisabled] = useState(true)
  /*const [boardBtnState, setBoardBtnState] = useState<BoardBtnState>({
    action: "Idle",
    isDisabled: true
  })*/

  // Handler to update formState when a field changes
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormState({
      ...formState,
      [name]: value,
    });
  };

  const handleBoardRequest = () => {
    //setBoardBtnState({ ...boardBtnState, action: "Loading"});
    setFormToggleState({
      ...formToggleState,
      mode: "Loading"
    })
    setTimeout(() => {
      //setBoardBtnState({ ...boardBtnState, action: "Idle"});
      setFormToggleState({
        ...formToggleState,
        mode: "Idle"
      })
    }, 10000)
  }

  useEffect(() => {
    const emailRegex = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/;
    const passwordRegex = /[a-zA-Z0-9]{8, 20}/
    const usernameRegex = /[a-zA-Z0-9]{8, 20}/

    if (formState.email == "" || formState.password == "" || formState.matchPassword == "" || formState.username == "") {
      //setBoardBtnState({ ...boardBtnState, isDisabled: true });
      setIsDisabled(true);
      return;
    }

    if (formState.password != formState.matchPassword) {
      //setBoardBtnState({ ...boardBtnState, isDisabled: true });
      setIsDisabled(true);
      return;
    }

    if (!emailRegex.test(formState.email)) {
      //setBoardBtnState({ ...boardBtnState, isDisabled: true });
      setIsDisabled(true);
      return;
    }

    console.log("passed validation")
    //setBoardBtnState({ ...boardBtnState, isDisabled: false });
    setIsDisabled(false);
  }, [formState])

  return(
    <Container maxWidth="sm" style={{backgroundColor: "white", display: "grid", placeItems: "center"}}>
      <h3>Onboarding Screen</h3>
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
        </Grid>f
      </Grid>
      <Grid id="middle-row" container spacing={24}  padding={"5px"}>
        <Grid item xs={12}>
          <TextField
              id="filled-basic"
              label="Password"
              variant="filled"
              name="password"
              type="password"
              value={formState.password}
              onChange={handleInputChange} // Update state when input changes
              disabled={formToggleState.mode == "Loading"}
            />
        </Grid>
      </Grid>
      <Grid id="middle-row" container spacing={24}  padding={"5px"}>
        <Grid item xs={12}>
          <TextField
              id="filled-basic"
              label="Re-enter Password"
              variant="filled"
              name="matchPassword"
              type="password"
              value={formState.matchPassword}
              onChange={handleInputChange} // Update state when input changes
              disabled={formToggleState.mode == "Loading"}
            />
        </Grid>
      </Grid>
      <Grid id="bottom-row" container spacing={24} padding={"5px"}>
        <Grid item xs={12}>
          <TextField
              id="standard-basic"
              label="Username"
              variant="standard"
              name="username"
              value={formState.username}
              onChange={handleInputChange} // Update state when input changes
              disabled={formToggleState.mode == "Loading"}
            />
        </Grid>
      </Grid>
      <Grid id="bottom-row" container spacing={24} padding={"5px"}>
        <Grid item xs={12}>
          <Button variant="contained" disabled={isDisabled || formToggleState.mode == "Loading"} onClick={handleBoardRequest}>{ formToggleState.mode == "Loading" ? (<><LoadingEllipse/><RocketLaunch/></>) : <Rocket/> } </Button>
        </Grid>
      </Grid>
    </Container>
  );
}