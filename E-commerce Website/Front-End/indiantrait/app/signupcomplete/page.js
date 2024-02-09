"use client";
import { useEffect, useState } from "react";
import Navbar from "../navbar/page";
import "./signupcomplete.css";

export default function SignUpComplete() {
      
    return(<>
        <Navbar></Navbar>
        <div className="signupcomplete-main">
            <div className="signupcomplete-title">
                <h1>Registration Complete</h1>
            </div>
            <div className="signupcomplete-body">
              <h4>Kindly verify your email address.</h4>
              <p>A verification link has been sent to your registered email address.</p>
           </div>
        </div>
    </>);
}