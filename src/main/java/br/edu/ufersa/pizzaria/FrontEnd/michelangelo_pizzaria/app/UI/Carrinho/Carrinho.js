"use client";

import "./Carrinho.css";
import { useState } from "react";
import Image from "next/image";

const Carrinho = ({ togglePopup }) => {
  const [itens, setItens] = useState([
    {
      id: 1,
      nome: "Pizza Margherita",
      preco: 30.0,
      sabor1: "Margherita",
      sabor2: null,
      borda: "Cheddar",
      adicionais: ["Bacon", "Catupiry"],
      tamanho: "Grande",
    },
    {
      id: 2,
      nome: "Pizza Portuguesa",
      preco: 35.0,
      sabor1: "Portuguesa",
      sabor2: null,
      borda: null,
      adicionais: [],
      tamanho: "Média",
    },
  ]);
  const [itemSelecionado, setItemSelecionado] = useState(null);

  const AdicionarItem = (item) => {
    setItens([...itens, item]);
  }

  const taxaEntrega = 5.0; // Simulação de taxa de entrega fixa
  const totalPedido = itens.reduce((acc, item) => acc + item.preco, 0) + taxaEntrega;

  const removerItem = (id) => {
    setItens(itens.filter((item) => item.id !== id));
  };

  const selecionarItem = (item) => {
    setItemSelecionado(item);
  };

  const continuarPedido = () => {
    console.log("Continuando o pedido...");
    togglePopup();
  };

  return (
    <div className="popup-overlay" onClick={togglePopup}>
      <div className="popup" onClick={(e) => e.stopPropagation()}>
        <h3>Carrinho de Compras</h3>

        {itens.length === 0 ? (
          <p>Seu carrinho está vazio no momento!</p>
        ) : (
          <div>
            {itens.map((item) => (
              <div
                key={item.id}
                className="item-carrinho"
                onClick={() => selecionarItem(item)}
              >
                <p>
                  <strong>{item.sabor1}</strong> ({item.tamanho}) - R$ {item.preco.toFixed(2)}
                </p>
                <button className="remove-btn" onClick={(e) => { e.stopPropagation(); removerItem(item.id); }}>
                  <Image src="/ExcluirItem.png" alt="Remover item" width={40} height={40} />
                </button>
              </div>
            ))}

            {itemSelecionado && (
              <div className="item-detalhes">
                <p><strong>Nome:</strong> {itemSelecionado.nome}</p>
                <p><strong>Sabor:</strong> {itemSelecionado.sabor1} {itemSelecionado.sabor2 && `e ${itemSelecionado.sabor2}`}</p>
                <p><strong>Tamanho:</strong> {itemSelecionado.tamanho}</p>
                {itemSelecionado.borda && <p><strong>Borda:</strong> {itemSelecionado.borda}</p>}
                {itemSelecionado.adicionais.length > 0 && <p><strong>Adicionais:</strong> {itemSelecionado.adicionais.join(", ")}</p>}
              </div>
            )}

            <div className="info-pedido">
              <p><strong>Taxa de entrega:</strong> R$ {taxaEntrega.toFixed(2)}</p>
              <p><strong>Total:</strong> R$ {totalPedido.toFixed(2)}</p>
            </div>
          </div>
        )}

        <div className="divider">
          <button className="finalizar" onClick={() => {togglePopup();}}>Finalizar Pedido</button>
          <button className="continue-button" disabled={itens.length === 0} onClick={continuarPedido}>
            Continuar Pedindo
          </button>
        </div>
      </div>
    </div>
  );
};

export default Carrinho;