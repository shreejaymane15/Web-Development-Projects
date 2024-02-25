"use client"
import Navbar from "@/app/navbar/page";
import { useEffect, useState } from "react";
import "./product.css";


export default function Product({id}){

    const [colors, setColors] = useState(["Red", "Blue", "White", "Black"]);
    const [sizes, setSizes] = useState(["S", "M", "L", "XL", "XXL"]);
    const [images, setImages] = useState(['/assets/images/Halloween Candy Bags Cute Gift Bag Pumpkin Witch Candy Boxes 1.jpg', '/assets/images/Halloween Candy Bags Cute Gift Bag Pumpkin Witch Candy Boxes 2.jpg', '/assets/images/Halloween Candy Bags Cute Gift Bag Pumpkin Witch Candy Boxes 3.jpg', '/assets/images/Halloween Candy Bags Cute Gift Bag Pumpkin Witch Candy Boxes 4.jpg']);
    const [descriptionVisible, setDescriptionVisible] = useState(true);
    const [colorVisible, setColorVisible] = useState(true);
    const [sizeVisible, setSizeVisible] = useState(true);
    const [favourite, setFavourite] = useState(true);
    const [selectedColor, setSelectedColor] = useState("");
    const [selectedSize, setSelectedSize] = useState("");
    const [mainImage, setMainImage] = useState(images[0]);

    useEffect(()=>{
        product;
    },[])


    const product = async () =>{
        const response = await productService();
        if(response.status === 200){    
            
        }else if(response.status === 500){
            
        }

    }

    const handleFavourite = () => {
        setFavourite(!favourite);
    }


    const hideDescriptionToggle = () =>{
        setDescriptionVisible(!descriptionVisible);
    }

    const hideColorToggle = () =>{
        setColorVisible(!colorVisible);
    }

    const hideSizeToggle = () =>{
        setSizeVisible(!sizeVisible);
    }


    const handleColorClick = (color) =>{
        console.log("Selected Color:", color);
        setSelectedColor(color);
    }

    const handleSizeClick = (size) =>{
        console.log("Selected Color:", size);
        setSelectedSize(size);
    }

    const handleMainImage = (e) => {
        const temp = mainImage;
        setMainImage(e.target.src);
        e.target.src = temp;
    }


    return(<>
        <Navbar/> 
        <div className="product-body">
            <div className="product-image">
                <div className="product-image-secondary">
                    {images.map((image, index) => (
                        index != 0 && (
                            <img  key={index} 
                            src={image} 
                            alt="Clothing"
                            onClick={(e) => {handleMainImage(e)}}
                            />
                        )
                    ))}
                </div>
                <img className="main-image" src={mainImage}></img>
            </div>
            <div className="product-data">
                <h1 className="product-title">Product Name</h1>
                <i className={`fav fa-heart ${favourite?'fav-solid fa-solid':'fa-regular'}`} onClick={handleFavourite}></i>
                <h3 className="product-description">Product description </h3>
                <h3 className="product-price">$650</h3>
                <hr></hr>
                <button className="btn-primary">ADD TO CART</button>
                <br></br><br></br>
                <button className="btn-primary">BUY NOW</button>
                <div className="product-filters">
                    <h4>Product Details</h4>
                    <i className={`fa-solid ${descriptionVisible ? "fa-plus" : "fa-minus"}`} 
                       onClick={hideDescriptionToggle}></i>
                    <hr></hr>
                    <p className={`${descriptionVisible ? 'hide':'show'}`}>
                        Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
                        Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
                        when an unknown printer took a galley of type and scrambled it to make a type specimen 
                        book. It has survived not only five centuries, but also the leap into electronic 
                        typesetting, remaining essentially unchanged. It was popularised in the 1960s 
                        with the release of Letraset sheets containing Lorem Ipsum passages, 
                        and more recently with desktop publishing software like Aldus PageMaker 
                        including versions of Lorem Ipsum
                    </p>
                </div>
                <div>
                    <h4>Color</h4>
                    <i className={`fa-solid ${colorVisible? 'fa-plus' : 'fa-minus'}`} onClick={hideColorToggle}></i>
                    <hr></hr>
                    <div className="colorContainer">
                        {colors.map((color,index) => (
                            <button key={index} 
                                    className={`color-btn ${colorVisible ? 'hide':'show'} ${selectedColor === color ? 'selected': ''}`} 
                                    style={{backgroundColor: color}}
                                    onClick={() => handleColorClick(color)}>        
                            </button>
                            ))
                        }
                    </div>
                </div>
                <div>
                    <h4>Size & Fit</h4>
                    <i className={`fa-solid ${sizeVisible ? 'fa-plus' : 'fa-minus'}`} onClick={hideSizeToggle}></i>
                    <hr></hr>
                    <div className="sizeContainer">
                        {sizes.map((size,index) => (
                            <button key={index} 
                                    className={`btn-secondary ${sizeVisible ?'hide':'show'} ${selectedSize === size ? 'selected': ''}`} 
                                    onClick={() => handleSizeClick(size)}
                                    value={size}>{size}        
                            </button>
                            ))
                        }
                    </div>
                </div>
            </div>            
        </div>
    </>);
}