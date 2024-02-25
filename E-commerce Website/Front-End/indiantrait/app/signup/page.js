"use client"
import { useState } from 'react';
import Navbar from '../navbar/page';
import './signup.css';
import { useRouter } from 'next/navigation';
import { signUpService } from '../../services/signupService';
import createUrl from '../constant';
import { toast } from 'react-toastify';
import "react-toastify/dist/ReactToastify.css";


export default function SignUp(){
   
    const router = useRouter();
    
   const [firstName, setFirstName] = useState("");
   const [lastName, setLastName] = useState("");
   const [mobile, setMobile] = useState("");
   const [email, setEmail] = useState("");
   const [password, setPassword] = useState("");
   const [rePassword, setRePassword] = useState("");


   const [firstNameError, setFirstNameError] = useState("");
   const [lastNameError, setLastNameError] = useState("");
   const [mobileError, setMobileError] = useState("");
   const [emailError, setEmailError] = useState("");
   const [passwordError, setPasswordError] = useState("");
   const [rePasswordError, setRePasswordError] = useState("");


  
    const validate = () => {

        setFirstNameError("");
        setLastNameError("");
        setMobileError("");
        setEmailError("");
        setPasswordError("");
        setRePasswordError("");

        let isValid = true;

        const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;


        if (firstName === "") {
            setFirstNameError("Field cannot be empty");
            isValid = false;
        }else if(firstName.length >= 20){
            setFirstNameError("Must be less than 20 characters.");
            isValid = false;
        }

        if (lastName === "") {
            setLastNameError("Field cannot be empty");
            isValid = false;
        }else if(lastName.length >= 20){
            setLastNameError("Must be less than 20 characters.");
            isValid = false;
        }

        if (mobile === "") {
            setMobileError("Field cannot be empty");
            isValid = false;
        }else if(mobile.length !== 10){
            setMobileError("Must be 10 digit mobile number.");
            isValid = false;
        }

        if (email === "") {
            setEmailError("Field cannot be empty");
            isValid = false;
        }else if(!emailRegex.test(email)) {
            setEmailError("Invalid Email");
            isValid = false;
        }

        if(password === ""){
            setPasswordError("Field cannot be empty");
            isValid = false;
        }else if (!passwordPattern.test(password)) {
          setPasswordError("Invalid Password");
          isValid = false;
        }

        if(rePassword === ""){
            setRePasswordError("Field cannot be empty");
            isValid = false;

        }else if(password !== rePassword){
            setRePasswordError("Password Should Be Same.");
            isValid = false;
        }

        return isValid;
    };






   const submitEvent = async () => {
        if(validate()){
            // debugger;
            let body = {
                "firstName" : firstName,
                "lastName" : lastName,
                "mobile" : mobile,
                "email" : email,
                "password" : password
            }

            const url = createUrl("/auth/register");
            const response = await signUpService(url, body);
            if(response.status == 200){
                toast.success(response.message);
                router.push('/signupcomplete');
            }else if( response.status === 500 || response.status === 501){
                console.log("error: "+response.message);
                toast.error(response.message);
            }else{
                toast.error(response.message);
            }                

        }
    }

    return(
        <>
        <Navbar></Navbar>
        <div className="signup-box">
            <div>
                <h1>SIGN UP</h1>
            </div>
            <div className='box-1'>
                <div className="first-name-box">
                    <label>First Name</label>
                    <input type="text" 
                           pattern="[A-Za-z]+" 
                           placeholder="First Name"
                           className={firstNameError?"is-invalid":""}
                           onChange={(e) => {
                            setFirstName(e.target.value);
                            setFirstNameError("");
                           }}></input>
                    <i className={firstNameError?"fa-solid fa-circle-exclamation":""}></i>
                    <div className='error-dialog'>{firstNameError}</div>
                </div>
                <div className="last-name-box">
                    <label>Last Name</label>
                    <input type="text" 
                           pattern="[A-Za-z]+" 
                           placeholder="Last Name"
                           className={lastNameError?"is-invalid":""}
                           onChange={(e) => {
                            setLastName(e.target.value);
                            setLastNameError("");
                           }}></input>
                    <i className={lastNameError?"fa-solid fa-circle-exclamation":""}></i>
                    <div className='error-dialog'>{lastNameError}</div>
                </div>
            </div>
            <div className='box-2'>
                <div className="mobile-box">
                    <label>Mobile</label>
                    <input type="number" 
                           placeholder="Mobile"
                           className={mobileError?"is-invalid":""}
                           pattern="[0-9]+" 
                           onChange={(e) => {
                            setMobile(e.target.value);
                            setMobileError("");
                           }}></input>
                    <i className={mobileError?"fa-solid fa-circle-exclamation":""}></i>
                    <div className='error-dialog'>{mobileError}</div>
                </div>
                <div className="email-box">
                    <label>Email</label>
                    <input type="email" 
                           placeholder="Email"
                           className={emailError?"is-invalid":""}
                           onChange={(e) => {
                            setEmail(e.target.value);
                            setEmailError("");
                           }}></input>
                    <i className={emailError?"fa-solid fa-circle-exclamation":""}></i>
                    <div className='error-dialog'>{emailError}</div>
                </div>
            </div>
            <div className='box-3'>
                <div className="password-box">
                    <label>Password</label>
                    <input type="password" 
                           placeholder="Password"
                           className={passwordError?"is-invalid":""}
                           onChange={(e) => {
                            setPassword(e.target.value);
                            setPasswordError("");
                           }}></input>
                    <i className={passwordError?"fa-solid fa-circle-exclamation":""}></i>
                    <div className='error-dialog'>{passwordError}</div>
                </div>
                <div className="re-password-box">
                    <label>Re-enter Password</label>
                    <input type="password" 
                           placeholder="Re-Enter Password"
                           className={rePasswordError?"is-invalid":""}
                           onChange={(e) => {
                            setRePassword(e.target.value);
                            setRePasswordError("");
                           }}></input>
                    <i className={rePasswordError?"fa-solid fa-circle-exclamation":""}></i>
                    <div className='error-dialog'>{rePasswordError}</div>
                </div>
            </div>
            <div className="button-box">
                <a onClick={submitEvent}>Sign Up</a>
            </div>
            <div>
                <span>Already Have An Account? <a href='./login'>Login Here</a></span>
            </div>
        </div>
        </>
    );
}