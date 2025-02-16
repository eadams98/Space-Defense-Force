import React from 'react';
import '../css/LoadingEllipse.css'; // Import the CSS file for styling

const LoadingEllipse: React.FC = () => {
  return (
    <div className="loading-ellipse">
      <div></div>
      <div></div>
      <div></div>
    </div>
  );
};

export default LoadingEllipse;
