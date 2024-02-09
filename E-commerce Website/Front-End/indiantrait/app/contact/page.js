"use client";

import { useState } from "react";
import Navbar from "../navbar/page";
import "./contact.css";

export default function Contact(){

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [message, setMessage] = useState("");

    const [firstNameError, setFirstNameError] = useState("");
    const [lastNameError, setLastNameError] = useState("");
    const [emailError, setEmailError] = useState("");
    const [messageError, setMessageError] = useState("");



    const validate = () => {

        setFirstNameError("");
        setLastNameError("");
        setEmailError("");
        setMessageError("");

        let isValid = true;

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

        if (message === "") {
            setMessageError("Field cannot be empty");
            isValid = false;
        }

        if (email === "") {
            setEmailError("Field cannot be empty");
            isValid = false;
        }else if(!emailRegex.test(email)) {
            setEmailError("Invalid Email");
            isValid = false;
        }
    }

    const submitEvent = () => {
        if(validate()){
            router.push('/login');
        }else{

        }
   }


    return(<>
        <Navbar></Navbar>
        <div className="contact-main">
            <div className="contact-div-1">
                <h1>Contact Us</h1>
            </div>
            <div className="contact-div-2">
                <div className="modal">
                    <div className="firstname"> 
                        <label>First Name</label>
                        <input type="text" 
                               placeholder="First Name"
                               className={firstNameError?"is-invalid":""}
                               onChange={(e) => {
                                setFirstName(e.target.value);
                                setFirstNameError("");
                               }}></input>
                        <i className={firstNameError?"fa-solid fa-circle-exclamation":""}></i>
                        <div className='error-dialog'>{firstNameError}</div>
                    </div>
                    <div className="lastname">
                        <label>Last Name</label>
                        <input type="text" 
                               placeholder="Last Name"
                               className={firstNameError?"is-invalid":""}
                               onChange={(e) => {
                                setLastName(e.target.value);
                                setLastNameError("");
                               }}></input>
                        <i className={lastNameError?"fa-solid fa-circle-exclamation":""}></i>
                        <div className='error-dialog'>{lastNameError}</div>
                    </div>
                    <div className="email"> 
                        <label>Email</label>
                        <input type="email" 
                               placeholder="Email"
                               className={firstNameError?"is-invalid":""}
                               onChange={(e) => {
                                setEmail(e.target.value);
                                setEmailError("");
                               }}></input>
                        <i className={emailError?"fa-solid fa-circle-exclamation":""}></i>
                        <div className='error-dialog'>{emailError}</div>
                    </div>
                    <div className="message">
                        <label>Message:</label>
                        <textarea placeholder="Type Your Message"
                                  className={messageError?"is-invalid":""}
                                  onChange={(e) => {
                                  setMessage(e.target.value);
                                  setMessageError("");
                                  }}></textarea>
                        <i className={messageError?"fa-solid fa-circle-exclamation":""}></i>
                        <div className='error-dialog'>{messageError}</div>
                    </div>
                    <div className="button-box">
                        <a onClick={submitEvent}>Submit</a>
                    </div>
                </div>
            </div>
        </div>
    </>);
}