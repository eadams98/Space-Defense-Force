import { RocketLaunch, Album } from "@mui/icons-material";
import { useEffect, useState } from "react";
import './css/animation.css';

export default function Animation() {

  const [animationStage, setAnimationStage] = useState(0)
  const [meteorStage, setMeteorStage] = useState({
    first: 0,
    second: 0
  })

  useEffect(() => {

    setAnimationStage(1)
    
    setTimeout(() => {
      setAnimationStage(2)
      setMeteorStage({
        "first":1,
        "second":0
      })
    }, 5000)

    setTimeout(() => {
      setAnimationStage(3)
      setMeteorStage({
        "first":1,
        "second":2
      })
    }, 7500)

    setTimeout(() => {
      setAnimationStage(4)
    }, 10000)

    setTimeout(() => {
      setAnimationStage(5)
    }, 12500)
    
  }, [])

  return(
    <div style={{ width: "100vw", height: "100vh", display: "grid", placeItems: "center" }} >
      <div className={`icon stage-${animationStage}`}>
        <RocketLaunch style={{ fontSize: 'inherit', color: "red"}}/>
      </div>
      <div className={`meteor-icon meteor-${meteorStage.first}`}>
        <Album style={{ fontSize: 'inherit', color: 'brown'}}/>
      </div>
      <div className={`meteor-icon-2 meteor-${meteorStage.second}`}>
        <Album style={{ fontSize: 'inherit', color: 'brown'}}/>
      </div>
    </div>
  )

}