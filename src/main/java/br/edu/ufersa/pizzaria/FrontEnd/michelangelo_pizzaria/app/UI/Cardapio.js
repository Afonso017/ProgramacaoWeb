"use client"
import { useState } from 'react';
import "./Cardapio.css"

const Cardapio = () => {
    const pizzas = [
        {
            sabor: "2 Sabores",
            precoP: "19,20",
            precoM: "27,90",
            precoG: "35,00",
            img: ""
        },
        {
            sabor: "Calabresa",
            precoP: "19,20",
            precoM: "27,90",
            precoG: "35,00",
            img: ""
        },
        {
            sabor: "Frango",
            precoP: "19,20",
            precoM: "27,90",
            precoG: "35,00",
            img: ""
        },
        {
            sabor: "Carne",
            precoP: "19,20",
            precoM: "27,90",
            precoG: "35,00",
            img: ""
        },
    ];

    const adicionais = [
        {
            nome: "Ovo",
            preco: "2,00"
        },
        {
            nome: "Calabresa",
            preco: "2,50"
        },
        {
            nome: "Banana",
            preco: "1,50"
        },
        {
            nome: "Carne",
            preco: "5,50"
        },
        {
            nome: "Queijo",
            preco: "5,00"
        },
        {
            nome: "Bacon",
            preco: "3,00"
        },
        {
            nome: "Chocolate",
            preco: "3,00"
        },
        {
            nome: "Pepperoni",
            preco: "1,75"
        },
        {
            nome: "MM's",
            preco: "2,00"
        }
    ];

    const [selected, setSelected] = useState(0);

    return (
        <section>
            <div className="cardapio">
                <hr />
                <h1>CARDÁPIO</h1>
            </div>
            <div className="menu">
                <button className={selected === 0 ? 'selected' : ''} onClick={() => setSelected(0)}>Pizzas</button>
                <button className={selected === 1 ? 'selected' : ''} onClick={() => setSelected(1)}>Bebidas</button>
            </div>
            <div className="pizzas">
                {pizzas.map((pizza, index) => {
                    return <button key={index} className="pizza">
                        <div>
                            <p>{pizza.sabor}</p>
                            <p>imagem</p>
                        </div>
                        <div className="precos">
                            <p>P R${pizza.precoP}</p>
                            <p>M R${pizza.precoM}</p>
                            <p>G R${pizza.precoG}</p>
                        </div>
                    </button>
                })}
            </div>
            <div className="adicionais-container">
                <h4>Tabela de Adicionais</h4>
                <div className="adicionais">
                    {adicionais.map((adicional, index) => {
                        return ( 
                            <div key={index} className="adicional">
                                <p>{adicional.nome}</p>
                                <p>R${adicional.preco}</p>
                            </div>
                        )
                    })}
                </div>
            </div>
        </section>
    );
};

export default Cardapio;