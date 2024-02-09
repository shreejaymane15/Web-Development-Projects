"use client"

import { useRouter } from "next/navigation";
import Navbar from "../navbar/page";
import "./login.css";
import { useContext, useState } from "react";
import createUrl from "../constant";
import { useDispatch, useSelector } from "react-redux";
import { login } from "@/lib/features/auth-slice";
import { loginService } from "../../services/loginService";
import { UserContext } from "@/context/context";


export default function Login(){

    const auth = useSelector((state) => state.auth);
    const dispatch = useDispatch();
    const { user, setUser } = useContext(UserContext);

    const router = useRouter();
    
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
  
    const [emailError, setEmailError] = useState("");
    const [passwordError, setPasswordError] = useState("");


    const validate = () => {

        setEmailError("");
        setPasswordError("");

        let isValid = true;

        if (email === "") {
            setEmailError("Field cannot be empty");
            isValid = false;
        }

        if(password === ""){
            setPasswordError("Field cannot be empty");
            isValid = false;
        }

        return isValid;
    };




    
   const submitEvent = async () => {
        if(validate()){
            // debugger;
            let body = {
                "email" : email,
                "password" : password
            }
            let url = createUrl("/auth/login");
            // let url = "http://localhost:9000/";
            var response = await loginService(url, body);
            if(response.status == 200){
                debugger;
                dispatch(login({ userId: response.userId, token: response.token, isAuthenticated: true}));
                setUser({
                    ...user,
                    id:  response.userId,
                    token: response.token,
                    isAuthenticated:  true
                });
                console.log(user);
                router.push('/home');
            }else if(response.status == 500 || response.status == 501){
                console.log("success: " + response.data.message);
            }
        }
    }

    return(
        <>
        <Navbar></Navbar>
        <div className="login-box">
            <div>
                <h1>LOGIN</h1>  
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
            <div className="button-box">
                <a onClick={submitEvent}>Log In</a>
            </div>
            <div>
                <span>New To IndianTrait?</span> <a href="./signup">Create An Account</a>
            </div>
        </div>
        </>
    );
}