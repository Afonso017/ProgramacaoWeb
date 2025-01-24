import Header from "./UI/Header.js";
import Footer from "./UI/Footer.js";
import PizzaImage from "./UI/PizzaImage.js";
import Cardapio from "./UI/Cardapio.js"
import Infos from "./UI/Infos.js"

export default function Home() {
    return (
        <div>
            <Header />
            <PizzaImage />
            <Cardapio />
            <Infos />
            <Footer /> 
        </div>
    );
};