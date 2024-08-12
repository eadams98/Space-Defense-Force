// Define the possible values for the button state
export type BoardBtnState = {
  action: "Loading" | "Idle";
  isDisabled: boolean;
};

// Define the possible values for the form state
export type FormState = {
  tab: "Launch" | "Onboard" | "Reset";
  mode: "Idle" | "Loading";
};

// Interface for the OnboardingScreen component props
export interface OnboardingScreenProps {
  formState: FormState;
  setFormState: React.Dispatch<React.SetStateAction<FormState>>;
}

// Interface for the LaunchScreen component props
export interface LaunchScreenProps {
  formState: FormState;
  setFormState: React.Dispatch<React.SetStateAction<FormState>>;
}
