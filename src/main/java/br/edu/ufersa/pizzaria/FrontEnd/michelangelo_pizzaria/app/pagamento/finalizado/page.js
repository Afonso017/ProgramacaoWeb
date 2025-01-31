"use client";
import { useEffect, useState } from "react";
import axios from "axios";
import "./page.css";

interface OrderStatusProps {
  orderId: number;
}

const Finalizado = ({ orderId }: OrderStatusProps) => {
  const [status, setStatus] = useState<string | null>(null);

  // Função para buscar o status inicial do pedido
  const fetchStatus = async () => {
    try {
      // Faz a requisição para obter o status inicial do pedido
      const response = await fetch(`/status/${orderId}`);
      const data = await response.json();
      setStatus(data.status); // Atualiza o estado com o status recebido
    } catch (error) {
      console.error("Erro ao obter status:", error);
    }
  };

  useEffect(() => {
    // Chama fetchStatus para obter o status inicial quando o componente for montado
    fetchStatus();

    // Estabelecendo a conexão SSE para atualizações em tempo real
    const eventSource = new EventSource(`/status/stream/${orderId}`);

    eventSource.addEventListener("statusUpdate", (event) => {
      setStatus(event.data); // Atualiza o status com o dado recebido via SSE
    });

    // Limpeza da conexão SSE quando o componente for desmontado
    return () => {
      eventSource.close();
    };
  }, [orderId]); // Re-executa quando o orderId mudar

  return (
    <div>
      <h3>Status do Pedido: {orderId}</h3>
      <p>{status ? `Status atual: ${status}` : "Carregando status..."}</p>
    </div>
  );
};

export default Finalizado;