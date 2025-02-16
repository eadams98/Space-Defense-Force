import React from 'react';
import logo from './logo.svg';
import './App.css';
import LandingPage from './pages/login/LandingPage';
import { BrowserRouter as Router, Route, createBrowserRouter, RouterProvider, } from 'react-router-dom';
import ErrorPage from './utility/RouterError';
import HomePage from './pages/home/Homepage';
 import Animation from './pages/animations/Animation';
import LandingContainer from './pages/login/LandingContainer';
import { Card } from '@mui/material';
import Bag from './pages/home/bag/Bag';


const router = createBrowserRouter([
  {
    path: "/",
    element: <LandingPage/>,
    errorElement: <ErrorPage/>,
    children: [
      {
        path: "/",
        element: <LandingContainer />,
      },
      {
        path: "/welcome",
        element: <Animation />,
      },
      {
        path: "/home",
        element: <HomePage />,
        children: [
          {
            path: "bag", // Nested route for /home/bag
            element: <Bag/>
          },
        ]
      },
  ],
}

]);

function App() {
  /*return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );*/
  return (
    <div className='App'>
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
