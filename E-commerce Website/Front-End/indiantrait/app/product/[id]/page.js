import Navbar from "@/app/navbar/page";
import { useEffect } from "react";



export default function Product({id}){

    useEffect(()=>{
        product;
    },[])


    const product = async () =>{
        const response = await productService();
        if(response.status === 200){
            
        }else if(response.status === 500){
            
        }

    }

    return(<>
        <Navbar>
            <div className="product-body">
                <div className="product-image">
                    <img className="image"></img>
                    <img></img>
                </div>
                <div className="product-data">
                    <h3 className="product-title"></h3>
                </div>            
            </div>
        </Navbar>
    </>);
}