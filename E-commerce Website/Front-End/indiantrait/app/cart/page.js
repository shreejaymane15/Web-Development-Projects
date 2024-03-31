'use client'

import { useEffect, useState } from "react";
import Navbar from "../navbar/page";
import "./cart.css";

export default function Cart(){

    const [products, setProducts] = useState([
    {
        productImage: "/assets/images/Halloween Candy Bags Cute Gift Bag Pumpkin Witch Candy Boxes 1.jpg",
        productName:  "Halloween Candy Bags Cute Gift Bag Pumpkin Witch Candy Boxes", 
        productDescription: "Halloween Candy Bags Cute Gift Bag Pumpkin Witch Candy Boxes with a variety of candy treats.",
        quantity: 1,
        price: 100,
        discount: 50,
        discountedPrice: 50
    },
    {
        productImage: "/assets/images/Halloween Candy Bags Cute Gift Bag Pumpkin Witch Candy Boxes 2.jpg",
        productName:  "Halloween Candy Bags Cute Gift Bag Pumpkin Witch Candy Boxes", 
        productDescription: "Halloween Candy Bags Cute Gift Bag Pumpkin Witch Candy Boxes with a variety of candy boxes",
        quantity: 1,
        price: 200,
        discount: 50,
        discountedPrice: 100
    },
    {
        productImage: "/assets/images/Halloween Candy Bags Cute Gift Bag Pumpkin Witch Candy Boxes 3.jpg",
        productName:  "Halloween Candy Bags Cute Gift Bag Pumpkin Witch Candy Boxes", 
        productDescription: "Halloween Candy Bags Cute Gift Bag Pumpkin Witch Candy Boxes with a variety of candy boxes",
        quantity: 1,
        price: 300,
        discount: 50,
        discountedPrice: 150
    }
    ]);


    const [showModal, setShowModal] = useState(false);
    const [itemToRemove, setItemToRemove] = useState(null);

    useEffect( () =>{
        cart
    } , [])


    useEffect( () =>{
        
    } , [products])


    const cart = async () =>{
        const response = await cartService();
        if(response.status === 200){    
            
        }else if(response.status === 500){
            
        }

    }    
    
    
    // Function to open the modal
    const openModal = (index) => {
        setItemToRemove(index);
        setShowModal(true);
    }
    
    // Function to close the modal
    const closeModal = () => {
        setShowModal(false);
    }

    // Function to remove item from cart
    const removeItem = () => {
        setProducts(prevProducts => {
            const newProducts = [...prevProducts];
            newProducts.splice(itemToRemove, 1);
            return newProducts;
        });
        setShowModal(false); // Close modal after removing item
    }


    const increment = (index) =>{
        setProducts(prevProducts => {
            const newProducts = [...prevProducts];
            newProducts[index] = {...newProducts[index], quantity: newProducts[index].quantity + 1};
            return newProducts;
        });
    }

    const decrement = (index) =>{
        setProducts(prevProducts => {
            const newProducts = [...prevProducts];
            if (newProducts[index].quantity > 1) {
                newProducts[index] = {...newProducts[index], quantity: newProducts[index].quantity - 1};
            }else{
                openModal(index);
            }
            return newProducts;
        });
    }


    const calculateProductTotal = () => {
        return products.reduce((total, product) => total + (product.quantity * product.price), 0);
    }

    const calculateDiscount = () => {
        return products.reduce((total, product) => 
                    total + ((product.quantity * product.price)-(product.quantity * product.discountedPrice)), 0);
    }

    const calculateCartTotal = () => {
        return products.reduce((total, product) => total + (product.quantity * product.discountedPrice), 0);
    }


    const placeOrder = () => {

    }


    return(
        <>
        <div>
            <Navbar></Navbar>
            <div className="container">
                <h1 className="title">MY CART</h1>
                <div className="cart-products">
                    <div>
                        {products.map((product, index) => (
                            <div key={index} className="cart-product">
                                <div className="cart-product-image">
                                    <img src={product.productImage}></img>
                                </div>
                                <div className="cart-product-data">
                                    <h4>{product.productName}</h4>
                                    <h6>{product.productDescription}</h6>
                                    <div className="product-data-quantity">
                                        <button onClick={()=>decrement(index)}>-</button>
                                        <p>{product.quantity}</p>
                                        <button onClick={()=>increment(index)}>+</button>
                                    </div>
                                    <div className="product-data-price">
                                        <h4>Total</h4>
                                        <p>₹{product.discountedPrice * product.quantity}</p>
                                        <p className="original-price">₹{product.price * product.quantity}</p>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                    <div className="cart-bill">
                        <div className="cart-product-price-details">
                            <div>
                                <h3>Product Price ({products.length} items)</h3>
                                <p>₹{calculateProductTotal()}</p>                            
                            </div>
                            <div>
                                <h3>Discount</h3>
                                <p style={{color:"green"}}>- ₹{calculateDiscount()}</p>                            
                            </div>
                            <div>
                                <h3>Delivery Charges</h3>
                                <p>₹</p>
                                <p>Free</p>                            
                            </div>
                            <hr/>
                        </div>
                        <div className="cart-total">
                            <h3>Cart Total</h3>
                            <p>{calculateCartTotal()}</p>
                        </div>
                        <div>
                            <button onClick={placeOrder()}>Place Order</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        {showModal && (
                <div className="modal">
                    <div className="modal-content">
                        <p>Are you sure you want to remove this item from the cart?</p>
                        <div className="modal-buttons">
                            <button className="success" onClick={removeItem}>Yes</button>
                            <button className="failure" onClick={closeModal}>No</button>
                        </div>
                    </div>
                </div>
        )}
        </>
    );
}