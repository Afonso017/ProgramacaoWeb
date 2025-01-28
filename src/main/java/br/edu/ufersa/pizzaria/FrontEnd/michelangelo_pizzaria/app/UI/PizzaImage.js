import Image from "next/image";
import "./PizzaImage.css";

export default function PizzaImage() {
    return (
        <div className="container">
            <Image
                src="/Pizzas.png"
                alt="Michelangelo Pizzaria - Pizzas"
                width={1664}
                height={720}
            />
        </div>
    );
}