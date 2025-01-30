import { useState, useEffect, useMemo } from 'react';
import Cookies from "js-cookie";
import './Produto.css';

export default function Produto({ togglePopup, produto, adicionais, pizzas }) {
    const [selectedAdicionais, setSelectedAdicionais] = useState([]);
    const [selectedTamanho, setSelectedTamanho] = useState(produto.tipo === "bebida" ? "500ml" : "pequena");
    const [selectedBorda, setSelectedBorda] = useState("Sem borda");
    const [selectedSabores, setSelectedSabores] = useState([]);
    const [valor, setValor] = useState(produto.precoP || produto.preco500ml);

    const bordas = useMemo(() => [
        { nome: "Sem borda", preco: 0.0 },
        { nome: "Catupiry", preco: 6.0 },
        { nome: "Cheddar", preco: 8.0 },
        { nome: "Chocolate", preco: 5.0 },
        { nome: "Requeijão", preco: 5.0 }
    ], []);

    const precosTamanhos = useMemo(() => ({
        pequena: produto.precoP || produto.preco500ml,
        media: produto.precoM || produto.preco1L,
        grande: produto.precoG || produto.preco2L
    }), [produto]);

    const handleTamanhoChange = (event) => {
        setSelectedTamanho(event.target.value);
    };

    const handleSaboresChange = (saborNome) => {
        setSelectedSabores((prev) => {
            if (prev.includes(saborNome)) {
                return prev.filter((sabor) => sabor !== saborNome);
            } else if (prev.length < 2) {
                return [...prev, saborNome];
            }
            return prev;
        });
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
    }, [selectedTamanho, selectedBorda, selectedAdicionais, produto, adicionais, bordas, precosTamanhos]);    

    const adicionarProduto = () => {
        if (produto.sabor === "2 Sabores" && selectedSabores.length === 0) {
            alert("Selecione pelo menos um sabor!");
            return;
        }

        const pedido = {
            id: Date.now(),
            nome: produto.tipo,
            preco: valor,
            sabor1: selectedSabores[0] || produto.sabor,
            sabor2: selectedSabores[1] || null,
            borda: selectedBorda !== "Sem borda" ? selectedBorda : null,
            adicionais: selectedAdicionais,
            tamanho: selectedTamanho,
        };
    
        // Obtém o carrinho existente dos cookies (ou cria um novo array)
        const carrinhoAtual = JSON.parse(Cookies.get("carrinho") || "[]");
    
        // Adiciona o novo pedido ao carrinho
        carrinhoAtual.push(pedido);
    
        // Salva o carrinho atualizado nos cookies
        Cookies.set("carrinho", JSON.stringify(carrinhoAtual), { expires: 7 }); // Expira em 7 dias

        togglePopup();
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

                {/* Tamanho */}
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
                            {/* Se for pizza de 2 sabores */}
                            {produto.sabor === "2 Sabores" && (
                                <div className="sabores">
                                    <h4 id="tamanho-h4-borda">Escolha os sabores:</h4>
                                    {pizzas.filter((pizza) => pizza.sabor !== "2 Sabores").map((pizza, index) => (
                                        <div key={index} className="borda">
                                            <label htmlFor={`sabor-${pizza.sabor}`}>
                                                {pizza.sabor}
                                            </label>
                                            <input
                                                type="checkbox"
                                                name="sabor"
                                                id={`sabor-${pizza.sabor}`}
                                                value={pizza.sabor}
                                                checked={selectedSabores.includes(pizza.sabor)}
                                                onChange={() => handleSaboresChange(pizza.sabor)}
                                            />
                                        </div>
                                    ))}
                                </div>
                            )}

                            {/* Borda */}
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
                            </div>

                            {/* Adicionais */}
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
                        <button id="adicionar-btn" onClick={() =>{adicionarProduto(); togglePopup();}}>
                            Adicionar <span>R$ {formatPreco(valor)}</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}