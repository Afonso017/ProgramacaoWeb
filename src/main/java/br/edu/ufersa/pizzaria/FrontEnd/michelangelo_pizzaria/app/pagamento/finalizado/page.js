"use client";
import { useRef, useEffect, useState } from "react";
import Footer from "../../UI/Footer";
import "./page.css"  

const Finalizado = ({ orderId }) => {
  const [status, setStatus] = useState(null);

  const fetchStatus = async () => {
    try {
      const response = await fetch(`/status/${orderId}`);
      const data = await response.json();
      setStatus(data.status);
    } catch (error) {
      console.error("Erro ao obter status:", error);
    }
  };

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

  useEffect(() => {
    fetchStatus();

    const eventSource = new EventSource(`/status/stream/${orderId}`);

    eventSource.addEventListener("statusUpdate", (event) => {
      setStatus(event.data);
    });

    return () => {
      eventSource.close();
    };
  }, [orderId]);

  return (
    <div>
      <header className={`header ${isVisible ? '' : 'hidden'}`}>
        <div className="titles">
            <h1>MICHELANGELO</h1>
            <h2>Pizzaria</h2>
        </div>
      </header>
      <div className="statusP">
        <h3>Status do Pedido: {orderId}</h3>
        <p>{status ? `Status atual: ${status}` : "Carregando status..."}</p>
      </div>
      <div className="FootPag"><Footer/></div>
    </div>
  );
};

export default Finalizado;