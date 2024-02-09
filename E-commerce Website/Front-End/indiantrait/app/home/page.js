"use client";
import Navbar from "../navbar/page";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import HomeService from "../../services/homeService";
import ProtectedRoute from "../protectedroute/page";


export default function Home(){
    const auth = useSelector((state) => state);

    useEffect( () => {
        getData();
    },[])
    
    const getData = async () =>{
        debugger;
        const response = await HomeService();
        if(response.status == 200){
            console.log(response.message);
        }else{
            console.log(response);
        }
    }
    
    return(
        <>
        <Navbar></Navbar>
        <div className="main-box">
            <div className="first-div">
                <h4>{auth.userId}</h4>
                <h4>{auth.token}</h4>
                <h4>{auth.isAuthenticated?'true':'false'}</h4>
            </div>
            <div className="second-div">
                <h4></h4>
            </div>
            <div className="third-div">
                <h4></h4>
            </div>
        </div>
        </>
    );
}