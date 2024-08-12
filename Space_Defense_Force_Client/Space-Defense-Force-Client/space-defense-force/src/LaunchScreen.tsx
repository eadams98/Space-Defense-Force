import { Alert, Button, Container, Grid, TextField } from "@mui/material";
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
  const [axiosResponse, setAxiosResponse] = useState({
    errorMessage: "",
    successMessge: ""
  })

  // Handler to update formState when a field changes
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormState({
      ...formState,
      [name]: value,
    });
  };

  useEffect(() => {
    setAxiosResponse({
      errorMessage: "",
      successMessge: ""
    })

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

    // A helper function to create a delay
    const delay = (ms: number) => new Promise(resolve => setTimeout(resolve, ms)); 

    const fetchData = async () => {
      try {
        const axiosPromise = axios.post<any>("http://localhost:8765/auth/signin", formState);
        const delayPromise = delay(5000); // 5 seconds delay

        let response: any;
        try {
            response = await axiosPromise;
        } catch (error) {
            response = error;
        }
        
        // Wait for the delay to finish
        await delayPromise;

        console.log(response)

        // Handle successful response here (e.g., store tokens, navigate to another page, etc.)
        if (response && response.status >= 200 && response.status < 300) {
          setAxiosResponse({
            errorMessage: "",
            successMessge: "success"
          })
        } else {
          setAxiosResponse({
            errorMessage: response.response.data.errorMessage,
            successMessge: ""
          })
        }
      } finally {
        // This will always be executed after both the Axios request and delay are resolved
        setFormToggleState({
          ...formToggleState,
          mode: "Idle"
        });
      }
    }
    fetchData()
  };

  return (
    <Container
      maxWidth="sm"
      style={{ backgroundColor: "white", display: "grid", placeItems: "center" }}
    >
      { axiosResponse.successMessge == "" ? null : <Alert variant="filled" severity="success">{axiosResponse.successMessge}</Alert>}
      { axiosResponse.errorMessage == "" ? null :<Alert variant="filled" severity="error">{axiosResponse.errorMessage}</Alert>}
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
            disabled={isDisabled || formToggleState.mode === "Loading" || axiosResponse.errorMessage != ""}
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
