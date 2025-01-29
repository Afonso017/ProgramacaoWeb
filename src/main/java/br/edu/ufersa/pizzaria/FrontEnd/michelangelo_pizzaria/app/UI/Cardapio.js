"use client"
import { useState } from 'react';
import "./Cardapio.css"
import Image from 'next/image';

const Cardapio = () => {
    const pizzas = [
        {
            sabor: "2 Sabores",
            precoP: "19,20",
            precoM: "27,90",
            precoG: "35,00",
            img: "/pizzas/pizza-2-sabores.webp"
        },
        {
            sabor: "Calabresa",
            precoP: "19,20",
            precoM: "27,90",
            precoG: "35,00",
            img: "/pizzas/pizza-calabresa.jpg"
        },
        {
            sabor: "Frango",
            precoP: "19,20",
            precoM: "27,90",
            precoG: "35,00",
            img: "/pizzas/pizza-frango.jpg"
        },
        {
            sabor: "Carne",
            precoP: "19,20",
            precoM: "27,90",
            precoG: "35,00",
            img: "/pizzas/pizza-carne.jpg"
        },
        {
            sabor: "Chocolate",
            precoP: "19,20",
            precoM: "27,90",
            precoG: "35,00",
            img: "/pizzas/pizza-chocolate.webp"
        },
        {
            sabor: "Marguerita",
            precoP: "19,20",
            precoM: "27,90",
            precoG: "35,00",
            img: "/pizzas/pizza-marguerita.jpg"
        },
        {
            sabor: "Bacon",
            precoP: "19,20",
            precoM: "27,90",
            precoG: "35,00",
            img: "/pizzas/pizza-bacon.jpg"
        },
        {
            sabor: "Lombo com Catupiry",
            precoP: "19,20",
            precoM: "27,90",
            precoG: "35,00",
            img: "/pizzas/pizza-lombo-catupiry.jpg"
        },
        {
            sabor: "Camarão",
            precoP: "19,20",
            precoM: "27,90",
            precoG: "35,00",
            img: "/pizzas/pizza-camarao.webp"
        },
        {
            sabor: "Pepperoni",
            precoP: "19,20",
            precoM: "27,90",
            precoG: "35,00",
            img: "/pizzas/pizza-pepperoni.webp"
        },
    ];

    const bebidas = [
        {
            sabor: "Coca-Cola",
            preco500ml: "3,00",
            preco1L: "6,00",
            preco2L: "9,00",
            img: "/bebidas/coca-cola.png"
        },
        {
            sabor: "Guaraná",
            preco500ml: "3,00",
            preco1L: "6,00",
            preco2L: "9,00",
            img: "/bebidas/guarana.png"
        },
        {
            sabor: "Fanta Laranja",
            preco500ml: "3,00",
            preco1L: "6,00",
            preco2L: "9,00",
            img: "/bebidas/fanta-laranja.png"
        },
        {
            sabor: "Sprite",
            preco500ml: "3,00",
            preco1L: "6,00",
            preco2L: "9,00",
            img: "/bebidas/sprite.png"
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
                    return <button key={index} className="pizza">
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
                            <p>P R${pizza.precoP}</p>
                            <p>M R${pizza.precoM}</p>
                            <p>G R${pizza.precoG}</p>
                        </div>
                    </button>
                })}
            </div> :
            <div className="bebidas">
                {bebidas.map((bebida, index) => {
                    return <button key={index} className="bebida">
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
                            <p>500ml R${bebida.preco500ml}</p>
                            <p>1L R${bebida.preco1L}</p>
                            <p>2L R${bebida.preco2L}</p>
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