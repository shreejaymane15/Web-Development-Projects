'use server'
import axios from "axios";
import createUrl from "../app/constant";
import { cookies } from "next/headers";

export default async function HomeService() { 
  debugger;
  const token = cookies().get('token');
  const url = createUrl("/auth/hello");
  try{
      if(token){
          const response =  await axios.get(url,{
              headers: {
                  Authorization: 'Bearer ' + token.value  //the token is a variable which holds the token
                }});
                if(response != null){
                    return response.data;
                }else{
                    return "Internal Server Error";
                }
        }else{
            return "Please Login First!";
        }
  }catch{
    return "Network Error";
  }
}
