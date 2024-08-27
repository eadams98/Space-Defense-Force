import React, { useState } from 'react';
import '../../../css/Dice.css';

const Dice: React.FC = () => {
  const [number, setNumber] = useState<number>(1);
  const [rolling, setRolling] = useState<boolean>(false);
  const [rotation, setRotation] = useState<string>('rotateX(0deg) rotateY(0deg)');

  const rollDice = () => {
    setRolling(true);
    const randomNumber = Math.floor(Math.random() * 6) + 1;
    setNumber(randomNumber);

    // Pre-calculate the final rotation based on the random number
    const finalRotation = getRotationForNumber(randomNumber);

    // Simulate a smooth roll with random rotations plus final rotation
    const randomRotation = `rotateX(${360 * Math.floor(Math.random() * 4)}deg) rotateY(${360 * Math.floor(Math.random() * 4)}deg)`;

    // Combine random and final rotation to create the animation
    setRotation(randomRotation + ' ' + finalRotation);

    setTimeout(() => {
      setRolling(false);
    }, 1000); // Roll duration
  };

  const getRotationForNumber = (num: number) => {
    switch (num) {
      case 1:
        return 'rotateX(0deg) rotateY(0deg)';
      case 2:
        return 'rotateX(-90deg) rotateY(0deg)';
      case 3:
        return 'rotateX(0deg) rotateY(90deg)';
      case 4:
        return 'rotateX(0deg) rotateY(-90deg)';
      case 5:
        return 'rotateX(90deg) rotateY(0deg)';
      case 6:
        return 'rotateX(180deg) rotateY(0deg)';
      default:
        return 'rotateX(0deg) rotateY(0deg)';
    }
  };

  return (
    <div className="dice-container" style={{justifyContent: "center", display: "grid"}}>
      <div
        className={`dice ${rolling ? 'rolling' : ''}`}
        style={{
          transform: rotation,
          transition: 'transform 1s ease',
          paddingBottom: "20px"
        }}
      >
        <div className="face face-1">
          <div className="dot dot-center"></div>
        </div>
        <div className="face face-2">
          <div className="dot dot-top-left"></div>
          <div className="dot dot-bottom-right"></div>
        </div>
        <div className="face face-3">
          <div className="dot dot-top-left"></div>
          <div className="dot dot-center"></div>
          <div className="dot dot-bottom-right"></div>
        </div>
        <div className="face face-4">
          <div className="dot dot-top-left"></div>
          <div className="dot dot-top-right"></div>
          <div className="dot dot-bottom-left"></div>
          <div className="dot dot-bottom-right"></div>
        </div>
        <div className="face face-5">
          <div className="dot dot-top-left"></div>
          <div className="dot dot-top-right"></div>
          <div className="dot dot-center"></div>
          <div className="dot dot-bottom-left"></div>
          <div className="dot dot-bottom-right"></div>
        </div>
        <div className="face face-6">
          <div className="dot dot-top-left"></div>
          <div className="dot dot-top-right"></div>
          <div className="dot dot-middle-left"></div>
          <div className="dot dot-middle-right"></div>
          <div className="dot dot-bottom-left"></div>
          <div className="dot dot-bottom-right"></div>
        </div>
      </div>
      
      <button onClick={rollDice} className="roll-button" disabled={rolling}>
        {rolling ? 'Rolling...' : 'Roll Dice'}
      </button>
    </div>
  );
};

export default Dice;
