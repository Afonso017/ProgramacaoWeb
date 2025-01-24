"use client"
import './Header.css';
import React, { useEffect, useState, useRef } from 'react';
import NavBar from './NavBar';

const Header = () => {
    const [isVisible, setIsVisible] = useState(true);
    const lastScrollY = useRef(0);

    useEffect(() => {
        const headerHeight = document.querySelector('.header').offsetHeight;
        document.body.style.paddingTop = `${headerHeight}px`;

        const handleScroll = () => {
            const currentScrollY = window.scrollY;

            if (currentScrollY > lastScrollY.current) {
                setIsVisible(false);
            } else {
                setIsVisible(true);
            }

            lastScrollY.current = currentScrollY;
        };

        window.addEventListener('scroll', handleScroll);
        return () => {
            document.body.style.paddingTop = '';
            window.removeEventListener('scroll', handleScroll);
        }
    }, []);

    return (
        <header className={`header ${isVisible ? '' : 'hidden'}`}>
            <div className="titles">
                <h1>MICHELANGELO</h1>
                <h2>Pizzaria</h2>
            </div>
            <NavBar />
        </header>
    );
};

export default Header;