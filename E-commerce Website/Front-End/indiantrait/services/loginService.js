"use server";

import axios from "axios";
import { cookies } from "next/headers";

export async function loginService(url, body){
    try{
        const response = await axios.post(url, body)
        if(response != null){
            console.log(response.data);
            cookies().set('token', response.data.token,{secure:true});
            return response.data;
        }else{
            console.log(response.data);
            return "Internal Server Error";
        }
    }catch{
        return 'Network Error';
    }
}