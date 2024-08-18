import { BusinessCenter, Person3, Public, ShoppingCart } from "@mui/icons-material";
import { AppBar, BottomNavigation, BottomNavigationAction, Button, Container, IconButton, MenuItem, Toolbar, Typography } from "@mui/material";
import { useEffect, useState } from "react"
import { useNavigate, Outlet} from 'react-router-dom';

import './css/Homepage.css'

export default function HomePage() {
  const [state, setState] = useState(0);
  const [visibility, setVisibility] = useState(false);
  const [isDisabled, setIsDisabled] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    setTimeout(() => {
      setVisibility(true)
    }, 1000)
    setTimeout(() => {
      setIsDisabled(false)
    }, 11000) // visibility + transition time in 
  })

  const goToLogin = () => {
    navigate("/")
  }

  const goToBag = () => {
    navigate('/home/bag')
  }

  const goToBlank = () => {
    navigate('/home')
  }

  return(
    <Container style={{
      opacity: visibility ? 1 : 0, // Control visibility
      //transform: visibility ? 'translateY(0)' : 'translateY(20px)', // Move up slightly on show
      transition: 'opacity 3s ease-in', //transform 0.5s ease-in', // Smooth transition for both opacity and position
      display: "grid",
      placeItems: "center",
      height: '100%',
      width: '100%',
      gridTemplateRows: 'auto 1fr auto', // Creates three rows: top, middle, bottom,
      maxWidth: '100%',
      color: 'transparent',
      border: 'solid red'
   }}>
      {/* Top-aligned content */}
      <div style={{ alignSelf: 'start', justifySelf: 'center', width: '100%'}}>
        <AppBar position="static">
          <Toolbar>
            <IconButton
              onClick={goToBlank}
              size="large"
              edge="start"
              color="inherit" // This color is for the background of the IconButton, not the icon itself
              aria-label="menu"
              disabled={isDisabled}
              sx={{
                color: 'red', // Set the icon color here
                backgroundColor: 'black', // Set the background color of the IconButton
                '&:hover': {
                  backgroundColor: 'lightgray', // Change background on hover
                },
              }}
            >
              <Public sx={{ color: "white" }}/>
            </IconButton>
            <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
              News
            </Typography>
            <Button color="inherit" disabled={isDisabled} onClick={goToLogin}>Logout</Button>
          </Toolbar>
        </AppBar>
      </div>

      {/* Centered content */}
      <div style={{ alignSelf: 'center', justifySelf: 'center', width: '100%', height: "80%" }}>
        <Outlet />
      </div>
      
      {/* Bottom-aligned content */}
      <div style={{ alignSelf: 'end', justifySelf: 'center', width: '100%', border: 'solid green'}}>
        <BottomNavigation
          showLabels
          //value={value}
          //onChange={(event, newValue) => {
            //setValue(newValue);
          //}}
          style={{ backgroundColor: '#1976d2'}}
        >
          <BottomNavigationAction label="User" icon={<Person3 />} sx={{
              color: 'rgba(255, 255, 255, 0.5)', // Dim white color
              transition: 'color 0.3s ease-in-out',
              '&:hover': {
                color: 'rgba(255, 255, 255, 1)', // Full white on hover
              },
            }}
            onClick={() => {console.log("profile")}}
            disabled={isDisabled}
            /> 
          <BottomNavigationAction label="Bag" icon={<BusinessCenter />} sx={{
              color: 'rgba(255, 255, 255, 0.5)', // Dim white color
              transition: 'color 0.3s ease-in-out',
              '&:hover': {
                color: 'rgba(255, 255, 255, 1)', // Full white on hover
              },
            }}
            disabled={isDisabled}
            onClick={goToBag}
            />
          <BottomNavigationAction label="Shop" icon={<ShoppingCart />} sx={{
              color: 'rgba(255, 255, 255, 0.5)', // Dim white color
              transition: 'color 0.3s ease-in-out',
              '&:hover': {
                color: 'rgba(255, 255, 255, 1)', // Full white on hover
              },
            }}
            disabled={isDisabled}
            />

        </BottomNavigation>
      </div>
    </Container>
  )
}