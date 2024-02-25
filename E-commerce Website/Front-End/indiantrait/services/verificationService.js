"use server";
import axios from "axios";


export async function verificationService(url){
    debugger;
    const response = await axios.get(url);
    
    if(response != null){
        return response.data;
    }else{
        return "Internal Server Error";
    }
}




