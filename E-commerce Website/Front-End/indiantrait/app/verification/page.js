"use client";
import { useEffect, useState } from "react";
import Navbar from "../navbar/page";
import './verification.css';
import createUrl from "../constant";
import { useRouter } from "next/navigation";
import { verificationService } from "../../services/verificationService";

export default function Verification({searchParams}) {

    const router = useRouter();
    const [verificationStatus, setVerificationStatus] = useState("");

    useEffect(() => {
        verification(searchParams);
      }, []);
    

    useEffect(() => {
        console.log("Updated verificationStatus:", verificationStatus);
    }, [verificationStatus]);


    const verification = async (params) => {
      debugger;
      if (params && params.token) {
        debugger;
        const token = params.token;    
        let url = createUrl(`/auth/emailVerifiaction?token=${token}`);
        const response = await verificationService(url);
        console.log(response);
        if (response.status === 200) {
          setVerificationStatus(response.message);
        }else if(response.status === 501){
          setVerificationStatus(response.message);  
        }else if(response.status === 500){
          setVerificationStatus(response.message);  
        }
      }else{
        console.log("Token not found in the URL.");
      }
    }

    const login = () => {
        router.push("./login");
    }

      
    return(<>
        <Navbar></Navbar>
        <div className="verification-main">
            <div className="verification-title">
                <h1>Email Verification</h1>
            </div>
            <div className="verification-body">
                <div id="verified" 
                        className={(verificationStatus === "Email Verified")?"verified show":"hide"}>
                            Your email is verified. 
                            Please Log In <a href="./login">here</a></div>
                <div id="verified" 
                        className={(verificationStatus === "Already Verified")?"verified show":"hide"}>
                            Your email is Already verified. 
                            Please Log In <a href="./login">here</a></div>
                <div id="invalid" 
                        className={(verificationStatus === "Invalid Token")?"invalid show":"hide"}>
                            Email verification link is invalid. 
                            Please contact our support.</div>
                <div id="expired" 
                        className={(verificationStatus === "Token Expired")?"expired show":"hide"}>
                            Email verification link is expired. 
                            To  get a new one, <a href="">click here</a></div>
            </div>
        </div>
    </>);
}