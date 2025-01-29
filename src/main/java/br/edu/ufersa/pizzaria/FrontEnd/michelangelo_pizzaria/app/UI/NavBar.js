"use client";
import './NavBar.css';
import Image from 'next/image';
import { useState } from 'react';
import Carrinho from './Carrinho/Carrinho';

const NavBar = () => {
    const [isPopupVisible, setIsPopupVisible] = useState(false);

    const togglePopup = () => {
        setIsPopupVisible((prev) => !prev);
    };

    return (
         <nav>
            <ul>
                <li><a>CARDÁPIO/PEDIDO</a></li>
                <li><a>CONTATO/LOCAL</a></li>
            </ul>
            <div className="carrinho">
                <Image 
                    src="/ShoppingCart.png" 
                    alt="Carrinho de compras" 
                    width={35} 
                    height={35} 
                    onClick={togglePopup} 
                />
            </div>
            {/* Popup */}
            {isPopupVisible && (
                <Carrinho togglePopup={togglePopup} />
            )}
        </nav>
    );
};

export default NavBar;