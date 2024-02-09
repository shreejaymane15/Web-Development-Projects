import Navbar from "../navbar/page";
import "./about.css";


export default function About(){
    return(<>
    <div>
        <Navbar></Navbar>
        <div className="about-header">
            <h1>Who We Are</h1>
        </div>
        <div className="about-body">
            <div className="about-owner">
                <img src='/assets/images/Akshay.jpg'  alt="Akshay's Photo"/>
                <label>Akshay Khetre</label>
            </div>
            <div className="about-owner">
                <img src=""></img>
                <label>Shreejay Mane</label>
            </div>
            <div className="about-owner">
                <img src=""></img>
                <label>Prathmesh Kolekar</label>
            </div>  
        </div>
    </div>
    </>);
}