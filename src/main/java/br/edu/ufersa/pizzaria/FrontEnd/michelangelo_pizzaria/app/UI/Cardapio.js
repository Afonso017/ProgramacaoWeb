"use client"
import "./Cardapio.css"
import Image from 'next/image';
import Produto from './Produto';
import { useState, useEffect } from 'react';
import axios from 'axios';

const Cardapio = () => {
    const pizzas = [
        {
            tipo: "pizza",
            sabor: "2 Sabores",
            precoP: 19.20,
            precoM: 27.90,
            precoG: 35.00,
            img: "/pizzas/pizza-2-sabores.webp"
        },
        {
            tipo: "pizza",
            sabor: "Calabresa",
            precoP: 19.20,
            precoM: 27.90,
            precoG: 35.00,
            img: "/pizzas/pizza-calabresa.jpg"
        },
        {
            tipo: "pizza",
            sabor: "Frango",
            precoP: 19.20,
            precoM: 27.90,
            precoG: 35.00,
            img: "/pizzas/pizza-frango.jpg"
        },
        {
            tipo: "pizza",
            sabor: "Carne",
            precoP: 19.20,
            precoM: 27.90,
            precoG: 35.00,
            img: "/pizzas/pizza-carne.jpg"
        },
        {
            tipo: "pizza",
            sabor: "Chocolate",
            precoP: 19.20,
            precoM: 27.90,
            precoG: 35.00,
            img: "/pizzas/pizza-chocolate.webp"
        },
        {
            tipo: "pizza",
            sabor: "Marguerita",
            precoP: 19.20,
            precoM: 27.90,
            precoG: 35.00,
            img: "/pizzas/pizza-marguerita.jpg"
        },
        {
            tipo: "pizza",
            sabor: "Bacon",
            precoP: 19.20,
            precoM: 27.90,
            precoG: 35.00,
            img: "/pizzas/pizza-bacon.jpg"
        },
        {
            tipo: "pizza",
            sabor: "Lombo com Catupiry",
            precoP: 19.20,
            precoM: 27.90,
            precoG: 35.00,
            img: "/pizzas/pizza-lombo-catupiry.jpg"
        },
        {
            tipo: "pizza",
            sabor: "Camarão",
            precoP: 19.20,
            precoM: 27.90,
            precoG: 35.00,
            img: "/pizzas/pizza-camarao.jpg"
        },
        {
            tipo: "pizza",
            sabor: "Pepperoni",
            precoP: 19.20,
            precoM: 27.90,
            precoG: 35.00,
            img: "/pizzas/pizza-pepperoni.webp"
        },
    ];

    useEffect(() => {
        axios.get("/api/v1/additional")
            .then((res) => console.log(res.data))
            .catch((err) => console.error("Erro ao buscar dados:", err));
    }, []);

    const bebidas = [
        {
            tipo: "bebida",
            sabor: "Coca-Cola",
            preco500ml: 3.00,
            preco1L: 6.00,
            preco2L: 9.00,
            img: "/bebidas/coca-cola.png"
        },
        {
            tipo: "bebida",
            sabor: "Guaraná",
            preco500ml: 3.00,
            preco1L: 6.00,
            preco2L: 9.00,
            img: "/bebidas/guarana.png"
        },
        {
            tipo: "bebida",
            sabor: "Fanta Laranja",
            preco500ml: 3.00,
            preco1L: 6.00,
            preco2L: 9.00,
            img: "/bebidas/fanta-laranja.png"
        },
        {
            tipo: "bebida",
            sabor: "Sprite",
            preco500ml: 3.00,
            preco1L: 6.00,
            preco2L: 9.00,
            img: "/bebidas/sprite.png"
        },
    ];

    const adicionais = [
        {
            nome: "Ovo",
            preco: 2.00
        },
        {
            nome: "Calabresa",
            preco: 2.50
        },
        {
            nome: "Banana",
            preco: 1.50
        },
        {
            nome: "Carne",
            preco: 5.50
        },
        {
            nome: "Queijo",
            preco: 5.00
        },
        {
            nome: "Bacon",
            preco: 3.00
        },
        {
            nome: "Chocolate",
            preco: 3.00
        },
        {
            nome: "Pepperoni",
            preco: 1.75
        },
        {
            nome: "MM's",
            preco: 2.00
        }
    ];

    const [selected, setSelected] = useState(0);
    const [isPopupVisible, setIsPopupVisible] = useState(false);
    const [selectedProduct, setSelectedProduct] = useState(null);

    const togglePopup = (produto) => {
        setIsPopupVisible((prev) => !prev);
        setSelectedProduct(produto);
    };

    const formatPreco = (preco) => preco.toFixed(2).replace('.', ',');

    return (
        <section id="cardapio">
            <div className="cardapio">
                <hr />
                <h1>CARDÁPIO</h1>
            </div>
            <div className="menu">
                <button className={selected === 0 ? 'selected' : ''} onClick={() => setSelected(0)}>Pizzas</button>
                <button className={selected === 1 ? 'selected' : ''} onClick={() => setSelected(1)}>Bebidas</button>
            </div>
            {(selected === 0 ? <div className="pizzas">
                {pizzas.map((pizza, index) => {
                    return <button key={index} className="pizza" onClick={() => togglePopup(pizza)}>
                        <div>
                            <p id="sabor">{pizza.sabor}</p>
                            <Image 
                            src={pizza.img} 
                            alt="Imagem da pizza" 
                            width={200} 
                            height={200} 
                            style={{objectFit: "contain"}}
                        />
                        </div>
                        <div className="precos">
                            <p>P R${formatPreco(pizza.precoP)}</p>
                            <p>M R${formatPreco(pizza.precoM)}</p>
                            <p>G R${formatPreco(pizza.precoG)}</p>
                        </div>
                    </button>
                })}
            </div> :
            <div className="bebidas">
                {bebidas.map((bebida, index) => {
                    return <button key={index} className="pizza" onClick={() => togglePopup(bebida)}>
                        <div>
                            <p id="sabor">{bebida.sabor}</p>
                            <Image 
                                src={bebida.img} 
                                alt="Imagem da bebida" 
                                width={200} 
                                height={200} 
                                style={{objectFit: "contain"}}
                            />
                        </div>
                        <div className="precos">
                            <p>500ml R${formatPreco(bebida.preco500ml)}</p>
                            <p>1L R${formatPreco(bebida.preco1L)}</p>
                            <p>2L R${formatPreco(bebida.preco2L)}</p>
                        </div>
                    </button>
                })}
            </div>)}
            <div className="adicionais-container">
                <h4>Tabela de Adicionais</h4>
                <div className="adicionais">
                    {adicionais.map((adicional, index) => {
                        return ( 
                            <div key={index} className="adicional">
                                <p>{adicional.nome}</p>
                                <p>R$ {formatPreco(adicional.preco)}</p>
                            </div>
                        )
                    })}
                </div>
            </div>
            {isPopupVisible && (
                <Produto togglePopup={togglePopup} produto={selectedProduct} adicionais={adicionais} pizzas={pizzas} />
            )}
        </section>
    );
};

export default Cardapio;