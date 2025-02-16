import { useRouteError } from "react-router-dom";
import StarfieldCanvas from "../pages/animations/starfieldCanvas";

export default function ErrorPage() {
  const error: any = useRouteError();
  console.error(error);

  return (
    <>
      <StarfieldCanvas/>
      <div id="error-page" style={{color: "white"}}>
        <h1>Oops!</h1>
        <p>Sorry, an unexpected error has occurred.</p>
        <p>
          <i>{error.statusText || error.message}</i>
        </p>
      </div>
    </>
  );
}