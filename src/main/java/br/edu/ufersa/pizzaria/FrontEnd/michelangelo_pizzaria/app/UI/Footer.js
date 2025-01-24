import React from 'react';
import Image from 'next/image';
import './Footer.css';

const Footer = () => {
    return (
        <footer className="footer-container">
            <div className="footer-social">
                <a href="#" target="_blank" rel="noopener noreferrer">
                    <Image src="/Instagram.png" alt="Instagram" width={30} height={30} />
                </a>
                <a href="#" target="_blank" rel="noopener noreferrer">
                    <Image src="/Facebook.png" alt="Facebook" width={30} height={30} />
                </a>
            </div>
            <div className="footer-info">
                Michelangelo Pizzaria, Rua Firmino Souza 550, Lafayette, RS (84) 99953-0028
            </div>
            <div className="footer-copyright">
                Copyright © 2024 | Michelangelo Pizzaria
            </div>
        </footer>
    );
};

export default Footer;