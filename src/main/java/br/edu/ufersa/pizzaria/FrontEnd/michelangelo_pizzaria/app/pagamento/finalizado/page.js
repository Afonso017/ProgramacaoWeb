"use client";
import { useEffect, useState } from "react";
import "./page.css";

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
      <h3>Status do Pedido: {orderId}</h3>
      <p>{status ? `Status atual: ${status}` : "Carregando status..."}</p>
    </div>
  );
};

export default Finalizado;