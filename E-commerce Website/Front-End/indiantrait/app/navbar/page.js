"use client"
import { useRouter } from "next/navigation";
import { useCookies } from "next-client-cookies";
import "./navbar.css";
import { useContext, useEffect, useState } from "react";
import { UserContext } from "@/context/context";


export default function Navbar(){

    const router = useRouter();
    const cookies = useCookies();
    const [token, setToken] = useState("");
    const {user, setUser} = useContext(UserContext);

    useEffect(() => {
        if(cookies.get("token")){
            setToken(cookies.get("token"));
        }
    }, [])

    const home = () => {
        router.push("http://localhost:3000/home");
    }

    const collection = () => {
        router.push("http://localhost:3000/collection");
    }

    const offer = () => {
        router.push("http://localhost:3000/offer");
    }

    const aboutUs = () => {
        router.push("http://localhost:3000/about");
    }

    const contactUs = () => {
        router.push("http://localhost:3000/contact");
    }

    const wishlist = () => {
        router.push("http://localhost:3000/wishlist");
    }

    const cart = () => {
        router.push("http://localhost:3000/cart");
    }
    
    const login = () => {
        router.push("http://localhost:3000/login");
    }

    const signUp = () => {
        router.push("http://localhost:3000/signup");
    }

    const logout = () => {
        cookies.remove("token");
        setToken("");
        setUser({
            ...user,
            id: 0,
            token: "",
            isAuthenticated: false
        })
        router.push("http://localhost:3000/login");
    }


    return(
        <>
    <nav>
        <div className="nav-bar">
            <div className="nav-title">
                <a onClick={home}>IndianTrait</a>
            </div>
            <div>
                <ul className="nav-list">
                    <li onClick={home}>Home</li>
                    <li onClick={collection}>Collection</li>
                    <li onClick={offer}>Offers</li>
                    <li onClick={aboutUs}>About Us</li>
                    <li onClick={contactUs}>Contact Us</li>
                </ul>
            </div>
            <div className="nav-buttons">
                <div className="nav-buttons-icons">
                    <i className="fa-solid fa-heart" onClick={wishlist}></i> 
                    <i className="fa-solid fa-cart-shopping" onClick={cart}></i>
                </div>
                <a className={token ? "hide" : "show"} onClick={login}>Log In</a>
                <a className={token ? "hide" : "show"}onClick={signUp}>Sign Up</a>
                <a className={token ? "show" : "hide"}onClick={logout}>Log Out</a>
            </div>
        </div>
    </nav>
    </>
    );
}