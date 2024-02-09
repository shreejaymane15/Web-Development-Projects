"use server";
import axios from "axios";



export async function signUpService(url, body){
    debugger;
    const response =  await axios.post(url, body);
    if(response != null){
        return response.data;
    }else{
        return "Internal Server Error";
    }
}




            // let url = "http://localhost:9000/indiantrait/auth/verifyRegistration";
            // const xhr = new XMLHttpRequest();
            // xhr.onreadystatechange = function() {
            //     debugger;
            //     if (this.readyState === 4 && this.status === 200) {
            //         debugger;
            //         var response = JSON.parse(this.responseText);
            //         if(response.status === 200){
            //             console.log("success: " + response.message);
            //             setTimeout(function() {
            //                 router.push('/login');
            //             }, 3000);
            //         }else if( response.status === 500 || response.status === 501){
            //             console.log("error: "+response.message);
            //         }
            //     }else if(this.readyState === 4 && this.status === 0){
            //         debugger;
            //         console.log("error: " + "Failed To Register. <br> Please Try After Sometime");
            //     }
            // };
            // xhr.open('POST', url);
            // xhr.setRequestHeader("Content-Type", "application/json");
            // xhr.send(body);