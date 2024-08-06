import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, AbstractControl, ValidationErrors, FormBuilder} from '@angular/forms';
import { Commander } from 'src/shared/models/commander';
import { CommanderLoginService } from './commander-login.service'
import { Router } from '@angular/router';
import { state } from '@angular/animations';

@Component({
  selector: 'app-commander-login',
  templateUrl: './commander-login.component.html',
  styleUrls: ['./commander-login.component.css']
})
export class CommanderLoginComponent implements OnInit {

  //variables
  commander: Commander;
  validUser: boolean = false;
  btnText: string = "Update" // flip between Register and Login
  formCardIndex: number = 0;
  options: any; // bad practice (any)

  loginForm: FormGroup /*= new FormGroup({
    userName: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });*/

  updateForm: FormGroup /*= new FormGroup({
    userName: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z]{6,}')]),
    password: new FormControl("", [Validators.required, Validators.pattern('[a-zA-Z]{6,}')]),
    rePassword: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z]{6,}'), this.matching])
  })*/

  
  registerForm: FormGroup;

  constructor(private fb: FormBuilder, private commanderLoginService: CommanderLoginService, private router: Router) { }

  ngOnInit(): void {

    this.loginForm = this.fb.group({
      userName: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
  });

  this.updateForm = this.fb.group({
    userName: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z]{6,}')]),
    password: new FormControl("", [Validators.required, Validators.pattern('[a-zA-Z]{6,}')]),
    newPassword: new FormControl("", [Validators.required, Validators.pattern('[a-zA-Z]{6,}')]),
    reNewPassword: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z]{6,}'), this.matchingUpdate])
  })

  this.registerForm = this.fb.group({
    commanderName: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z]{6,}')]),
    commanderPassword: new FormControl("", [Validators.required, Validators.pattern('[a-zA-Z]{6,}')]),
    reCommanderPassword: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z]{6,}'), this.matchingRegister])
  })

  this.options = [ {form: this.loginForm, btnText: "Update", formName: "Login"}, {form: this.updateForm, btnText:"Register", formName: "Update"}, {form: this.registerForm, btnText:"Login", formName: "Register"}];

  }

  // methods
  login() {
    this.commander = this.loginForm.value as Commander;
    //let tempCommander = new Commander();
    this.commander = new Commander();
    this.commander.commanderName = this.loginForm.controls['userName'].value
    this.commander.commanderPassword = this.loginForm.controls['password'].value
    console.log(this.commander);

    //this.loginService.login() //return // call service API call

    this.commanderLoginService.login(this.commander).subscribe(
      (response) => {
          
          console.log("Response: " + JSON.stringify(response))
          this.commander = response;
          this.router.navigate(['/', 'home'], {state: {data: this.commander}})

          //sessionStorage.setItem("customer", JSON.stringify(this.customer));
          //sessionStorage.setItem("userType", JSON.stringify("Customer"));
          //sessionStorage.setItem("cart",JSON.stringify(this.customer.customerCarts));
         
          //this.tryToLogin = false;
          //this.router.navigate(['/home']);
      },
      (error) => {
          //this.tryToLogin = false;
          //this.errorMessage = <any>error;
          //this.router.navigate(['/', 'home'], {state: {data: "hello World"}})
      }
  )

    console.log("Returned Response " + JSON.stringify(this.commander))

  }

  updatePassword() {
    // passed validation by this
    const commanderUpdate= new Commander();
    commanderUpdate.commanderName = this.updateForm.controls['userName'].value;
    commanderUpdate.commanderPassword = this.updateForm.controls['password'].value;
    commanderUpdate.commanderNewPassword = this.updateForm.controls['newPassword'].value;

    this.commanderLoginService.updatePassword(commanderUpdate).subscribe(
      (response) => {
        console.log("Response: " + JSON.stringify(response));
        this.commander = response;
      },
      (error => {
        // DEAL WITH ERROR HERE
      })
    )
  }

  registerCommander() { // virtual duplicate (Use react way of using one func)
    // passed validation by this
    const commanderUpdate= new Commander();
    commanderUpdate.commanderName = this.registerForm.controls['commanderName'].value;
    commanderUpdate.commanderPassword = this.registerForm.controls['commanderPassword'].value;

    this.commanderLoginService.registerCommander(commanderUpdate).subscribe(
      (response) => {
        console.log("Response: " + JSON.stringify(response));
        this.commander = response;
      },
      (error => {
        // DEAL WITH ERROR HERE
      })
    )
  }

  toggleUpdateAndLogin() {

    this.formCardIndex = (this.formCardIndex + 1) % this.options.length;
    this.btnText = this.options[this.formCardIndex].btnText;
    this.options[this.formCardIndex].form.reset();
    /*
    if (this.btnText == "Update")
    {
      this.btnText = "Login";
      //reset update fields
    }
    else
    {
      this.btnText = "Update";
      //reset login fields
    }*/
  }

  // custom Validators
  matchingUpdate(control: AbstractControl): ValidationErrors | null {
    console.log(control)
    console.log("control: " + control.value + " ?= " + "other control: " + control.parent?.value.newPassword + " (true/false): " + (control.value != control.parent?.value.newPassword))
    return (control.value != control.parent?.value.newPassword) ? {MismatchPassword: {value: true}} : null;
  }

  matchingRegister(control: AbstractControl): ValidationErrors | null {
    
    return (control.value != control.parent?.value.commanderPassword) ? {MismatchPassword: {value: true}} : null;
  }

}

/*

        public int makeBound(StateValue initialState, String finalState, ArrayList<Integer> writerIndices) { // not the best bound. to make it better I would need to go both left and right simulatanously and see which gives the best answer
            
            char[] initialStateCharArray = initialState.state.toCharArray(); // going to mutate
            int bound = 0;
            int modBound = finalState.length();
            
            // convert to all 0's
            while(findOnes(String.valueOf(initialStateCharArray)).size() != 0) {
                ArrayList<Integer> indexOfOnesInitial = findOnes(String.valueOf(initialStateCharArray));
                //System.out.println("here: " + indexOfOnesInitial + writerIndices);
                for(Integer idxOfOne : indexOfOnesInitial) {
                    if (writerIndices.contains(idxOfOne)) {
                        initialStateCharArray[idxOfOne] = '0';
                        bound += 2;
                        //System.out.println("matcing write: " + indexOfOnesInitial + " idx: " + idxOfOne);
                    }
                }

                if (findOnes(String.valueOf(initialStateCharArray)).size() == 0){
                    //System.out.println("final: " + String.valueOf(initialStateCharArray) + " bound: " + bound);
                    break; //return bound;
                }
                
                char[] newCharState = new char[initialStateCharArray.length];
                for(int bitIdx = 0; bitIdx < finalState.length(); bitIdx++) {
                    char bitClockwise = initialStateCharArray[bitIdx]; // right rotation
                    newCharState[ Math.floorMod(bitIdx + 1, modBound) ] = bitClockwise;
                } bound += 2;
                System.out.println(String.valueOf(newCharState));
                initialStateCharArray = newCharState;
            }
            
            int lookingAtFirstWriter = writerIndices.get(0);
            char[] finalStateCharArray = finalState.toCharArray();
            for(int bitIdx = 0; bitIdx < finalState.length(); bitIdx++) {
                System.out.println("init: " + initialStateCharArray[lookingAtFirstWriter] + " final: " + finalStateCharArray[bitIdx]);
                if(initialStateCharArray[lookingAtFirstWriter] != finalStateCharArray[bitIdx]) {
                    initialStateCharArray[lookingAtFirstWriter] = finalStateCharArray[bitIdx];
                    bound += 3;
                }
                char[] newCharState = new char[initialStateCharArray.length];
                for(bitIdx = 0; bitIdx < finalState.length(); bitIdx++) {
                    char bitClockwise = initialStateCharArray[bitIdx]; // right rotation
                    newCharState[ Math.floorMod(bitIdx + 1, modBound) ] = bitClockwise;
                } bound += 2;
                initialStateCharArray = newCharState;
            }
            
            while( !String.valueOf(initialStateCharArray).equals(finalState) ) {
                char[] newCharState = new char[initialStateCharArray.length];
                for(int bitIdx = 0; bitIdx < finalState.length(); bitIdx++) {
                    char bitClockwise = initialStateCharArray[bitIdx]; // right rotation
                    newCharState[ Math.floorMod(bitIdx + 1, modBound) ] = bitClockwise;
                } bound += 2;
                initialStateCharArray = newCharState;
            }
            
            return bound;  
        }

*/