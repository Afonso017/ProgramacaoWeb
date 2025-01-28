import './NavBar.css';
import Image from 'next/image';

const NavBar = () => {
    const popupPedido = () => {
        alert("Abrir janela de popup dos pedidos");
    };

    return (
        <nav>
            <ul>
                <li><a>CARDÁPIO/PEDIDO</a></li>
                <li><a>CONTATO/LOCAL</a></li>
            </ul>
            <div className="carrinho">
                <Image src="/ShoppingCart.png" alt="Carrinho de compras" width={35} height={35} onClick={popupPedido} />
            </div>
        </nav>
    );
};

export default NavBar;