"use client";
import "./Carrinho.css";
import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import Image from "next/image";
import Cookies from "js-cookie";

const Carrinho = ({ togglePopup }) => {
  const [itens, setItens] = useState([]);
  const router = useRouter();

  useEffect(() => {
      const carrinhoSalvo = JSON.parse(Cookies.get("carrinho") || "[]");
      setItens(carrinhoSalvo);
  }, []);

  const [itemSelecionado, setItemSelecionado] = useState(null);

  const taxaEntrega = 5.0; // Simulação de taxa de entrega fixa
  const totalPedido = itens.reduce((acc, item) => acc + item.preco, 0) + taxaEntrega;

  const removerItem = (id) => {
    const novoCarrinho = itens.filter((item) => item.id !== id);
    setItens(novoCarrinho);
    Cookies.set("carrinho", JSON.stringify(novoCarrinho), { expires: 7 });
  };


  const selecionarItem = (item) => {
    setItemSelecionado(item);
  };

  const continuarPedido = () => {
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
                  <strong>{item.sabor1}{item.sabor2 && " e " + item.sabor2}</strong> ({item.tamanho}) - R$ {item.preco.toFixed(2)}
                </p>
                <button className="remove-btn" onClick={(e) => { e.stopPropagation(); removerItem(item.id); }}>
                  <Image src="/ExcluirItem.png" alt="Remover item" width={40} height={40} />
                </button>
              </div>
            ))}

            {itemSelecionado && (
              <div className="item-detalhes">
                <p><strong>Item:</strong> {itemSelecionado.nome}</p>
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
          <button className="finalizar" onClick={(event) => {
            event.stopPropagation();
            router.push("/pagamento");
            togglePopup();
          }}>
            Finalizar Pedido
          </button>
          
          <button className="continue-button" disabled={itens.length === 0} onClick={(event) => {
              event.stopPropagation(); 
              continuarPedido();}}>
            Continuar Pedindo
          </button>
        </div>
      </div>
    </div>
  );
};

export default Carrinho;