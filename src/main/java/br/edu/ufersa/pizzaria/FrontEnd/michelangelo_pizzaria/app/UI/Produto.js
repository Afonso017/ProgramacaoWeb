import { useState, useEffect } from 'react';
import './Produto.css';

export default function Produto({ togglePopup, produto, adicionais }) {
    const [selectedAdicionais, setSelectedAdicionais] = useState([]);
    const [selectedTamanho, setSelectedTamanho] = useState(produto.tipo === "bebida" ? "500ml" : "pequena");
    const [selectedBorda, setSelectedBorda] = useState("Sem borda");
    const [valor, setValor] = useState(produto.precoP || produto.preco500ml);

    const bordas = [
        { nome: "Sem borda", preco: 0.0 },
        { nome: "Catupiry", preco: 6.0 },
        { nome: "Cheddar", preco: 8.0 },
        { nome: "Chocolate", preco: 5.0 },
        { nome: "Requeijão", preco: 5.0 }
    ];

    const precosTamanhos = {
        pequena: produto.precoP || produto.preco500ml,
        media: produto.precoM || produto.preco1L,
        grande: produto.precoG || produto.preco2L
    };

    const handleTamanhoChange = (event) => {
        setSelectedTamanho(event.target.value);
    };

    const handleBordaChange = (bordaNome) => {
        setSelectedBorda(bordaNome);
    };

    const handleAdicionalChange = (adicionalNome) => {
        setSelectedAdicionais((prev) => {
            if (prev.includes(adicionalNome)) {
                return prev.filter((item) => item !== adicionalNome);
            } else if (prev.length < 3) {
                return [...prev, adicionalNome];
            }
            return prev;
        });
    };

    const formatPreco = (preco) => preco.toFixed(2).replace('.', ',');

    useEffect(() => {
        let precoTamanho = 0.0;
        let precoBorda = 0.0;
        let precoAdicionais = 0.0;
        
        if (produto.tipo === "pizza") {
            precoTamanho = precosTamanhos[selectedTamanho];
            precoBorda = Number(bordas.find((borda) => borda.nome === selectedBorda)?.preco) || 0.0;
        } else if (produto.tipo === "bebida") {
            precoTamanho = produto[`preco${selectedTamanho}`] || 0.0;
        }
    
        precoAdicionais = selectedAdicionais.reduce((total, adicionalNome) => {
            const adicional = adicionais.find((item) => item.nome === adicionalNome);
            return total + (adicional ? Number(adicional.preco) : 0.0);
        }, 0);
    
        setValor(precoTamanho + precoBorda + precoAdicionais);
    }, [selectedTamanho, selectedBorda, selectedAdicionais, produto]);

    const adicionarProduto = () => {
        alert("Produto adicionado ao carrinho!");
    };

    return (
        <div className="popup-overlay" onClick={togglePopup}>
            <div className="popup" onClick={(e) => e.stopPropagation()}>
                <h2>
                    {produto.tipo === "pizza"
                        ? produto.sabor === "2 Sabores"
                            ? "Pizza "
                            : "Pizza sabor "
                        : "Refrigerante "}
                    {produto.sabor}
                </h2>
                <div className="produto">
                    <h4 id="tamanho-h4">Escolha o tamanho:</h4>
                    <div className="produto-container">
                        {produto.tipo === "pizza" ? (
                            <div className="tamanhos">
                                {Object.keys(precosTamanhos).map((tam, index) => (
                                    <div key={index} className="tamanho">
                                        <input
                                            type="radio"
                                            name="tamanho"
                                            id={tam}
                                            value={tam}
                                            checked={selectedTamanho === tam}
                                            onChange={handleTamanhoChange}
                                        />
                                        <label htmlFor={tam}>
                                            {tam.charAt(0).toUpperCase() + tam.slice(1)}
                                        </label>
                                    </div>
                                ))}
                            </div>
                        ) : (
                            <div className="tamanhos">
                                {["500ml", "1L", "2L"].map((tam, index) => (
                                    <div key={index} className="tamanho">
                                        <input
                                            type="radio"
                                            name="tamanho"
                                            id={tam}
                                            value={tam}
                                            checked={selectedTamanho === tam}
                                            onChange={handleTamanhoChange}
                                        />
                                        <label htmlFor={tam}>{tam}</label>
                                    </div>
                                ))}
                            </div>
                        )}
                    </div>

                    {produto.tipo === "pizza" && (
                        <div>
                            <h4 id="tamanho-h4-borda">Escolha a borda:</h4>
                            <div className="bordas">
                                {bordas.map((borda, index) => (
                                    <div key={index} className="borda" onClick={() => handleBordaChange(borda.nome)}>
                                        <label htmlFor={`borda-${borda.nome}`}>
                                            {borda.nome} <br />R$ {formatPreco(borda.preco)}
                                        </label>
                                        <input
                                            type="radio"
                                            name="borda"
                                            id={`borda-${borda.nome}`}
                                            value={borda.nome}
                                            checked={selectedBorda === borda.nome}
                                            onChange={() => handleBordaChange(borda.nome)}
                                        />
                                    </div>
                                ))}
                            </div>
                            <div>
                                <h4 id="adicionais-h4">Adicionais</h4>
                                <p id="selecao-adicionais-p">Selecione até 3</p>
                                <div className="adicionais">
                                    {adicionais.map((adicional, index) => (
                                        <div key={index} className="borda">
                                            <label htmlFor={`adicional-${adicional.nome}`}>
                                                {adicional.nome} <br />R$ {formatPreco(adicional.preco)}
                                            </label>
                                            <input
                                                type="checkbox"
                                                name="adicional"
                                                id={`adicional-${adicional.nome}`}
                                                value={adicional.nome}
                                                checked={selectedAdicionais.includes(adicional.nome)}
                                                onChange={() => handleAdicionalChange(adicional.nome)}
                                            />
                                        </div>
                                    ))}
                                </div>
                            </div>
                        </div>
                    )}

                    <div className="divider">
                        <button id="cancelar-produto" onClick={togglePopup}>
                            Cancelar
                        </button>
                        <button id="adicionar-btn" onClick={adicionarProduto}>
                            Adicionar <span>R$ {formatPreco(valor)}</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}