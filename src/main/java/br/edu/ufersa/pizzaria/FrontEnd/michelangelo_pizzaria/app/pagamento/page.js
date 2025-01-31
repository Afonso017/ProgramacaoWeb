"use client";
import { useRef, useEffect, useState } from "react";
import { useRouter } from "next/navigation"
import Footer from "../UI/Footer";
import axios from "axios";
import "./page.css";

const Pagamento = () => {
  const [opcaoEntrega, setOpcaoEntrega] = useState("retirada");
  const [opcaoPagamento, setOpcaoPagamento] = useState("Pix");
  const [erro, setErro] = useState(false);
  const router = useRouter();
  const [cep, setCep] = useState(""); // Para armazenar o CEP digitado
  const [endereco, setEndereco] = useState(null); // Para armazenar os dados do endereço
  const [numeroCasa, setNumeroCasa] = useState(""); // Para armazenar o número da casa
  const [buscando, setBuscando] = useState(false); // Para controlar se a busca está em andamento
  const [erroCep, setErroCep] = useState(null); // Para armazenar erro ao buscar o CEP
  const [randomNumber, setRandomNumber] = useState(null);

  // Função para buscar o endereço pelo CEP
  const buscarEndereco = async () => {
    if (cep.length === 8) {
      // Verificar se o CEP tem 8 caracteres (formato válido)
      setBuscando(true);
      setErroCep(null);
      try {
        const response = await axios.get(
          `https://viacep.com.br/ws/${cep}/json/`
        );
        if (response.data && !response.data.erro) {
          setEndereco(response.data); // Armazenar o endereço
        } else {
          setEndereco(null); // Caso o CEP não exista
          setErroCep("CEP não encontrado. Tente novamente.");
        }
      } catch (error) {
        console.error("Erro ao buscar o endereço", error);
        setErroCep("Erro ao buscar o CEP. Tente novamente.");
        setEndereco(null); // Caso haja algum erro na requisição
      } finally {
        setBuscando(false);
      }
    } else {
      setErroCep("Digite um CEP válido.");
    }
  };

  const [isVisible, setIsVisible] = useState(true);
  const lastScrollY = useRef(0);
  
  useEffect(() => {
      const headerHeight = document.querySelector('.header').offsetHeight;
      document.body.style.paddingTop = `${headerHeight}px`;

      const handleScroll = () => {
          const currentScrollY = window.scrollY;

          if (currentScrollY > lastScrollY.current) {
              setIsVisible(false);
          } else {
              setIsVisible(true);
          }

          lastScrollY.current = currentScrollY;
      };

      window.addEventListener('scroll', handleScroll);
      return () => {
          document.body.style.paddingTop = '';
          window.removeEventListener('scroll', handleScroll);
      }
  }, []);

  //função de número aleatório de 3 digitos
  useEffect(() => {
    setRandomNumber(Math.floor(Math.random() * 900) + 100);
  }, []);

  // Fução para checar se o cliente passou os dados necessários de acordo com a opção de entrega
  const checarDados = () => {
    if (opcaoEntrega === "delivery") {
      if (!endereco) {
        alert("Informe o CEP para entrega!");
        setErro(true);
      } else if (!numeroCasa) {
        alert("Informe o número da casa para entrega!");
        setErro(true);
      } else {
        setErro(false);
      }
    }
  }

  return (
    <div>
      <header className={`header ${isVisible ? '' : 'hidden'}`}>
            <div className="titles">
                <h1>MICHELANGELO</h1>
                <h2>Pizzaria</h2>
            </div>
      </header>
      <div className="resumo-pedido">
      <h2>Resumo do Pedido</h2>
      <div className="pedido-box">
        <div className="item-pedido">
          <span>1x</span>
          <span>CALZONE 4 QUEIJOS</span>
          <span>R$ 35,00</span>
          <button className="delete">🗑️</button>
        </div>
        <div className="subtotal">
          <span>Subtotal</span>
          <span>R$ 35,00</span>
        </div>
        <div className="total">
          <span>
            <strong>Total</strong>
          </span>
          <span>
            <strong>R$ 35,00</strong>
          </span>
        </div>
        <div className="opcao-entrega">
          <label>Opção de Entrega:</label>
          <select
            value={opcaoEntrega}
            onChange={(e) => setOpcaoEntrega(e.target.value)}
          >
            <option value="retirada">Retirada</option>
            <option value="delivery">Delivery</option>
            <option value="local">Consumo no local</option>
          </select>
        </div>
        {opcaoEntrega === "retirada" && (
          <div className="retirada-info">
            <p>
              <strong>Código do Pedido: {randomNumber}</strong>
            </p>
            <p><strong>Local de Retirada</strong></p>
            <p>Porreta Pizza</p>
            <p>Avenida Abel Coelho, 1600 - Vizinho ao sindicato dos bancários</p>
          </div>
        )}

        {opcaoEntrega === "delivery" && (
          <div className="delivery-info">
            <p>Informe o seu CEP:</p>
            <div className="cep-input">
              <input
                type="text"
                placeholder="Digite seu CEP"
                value={cep}
                onChange={(e) => setCep(e.target.value)}
              />
              <button
                onClick={buscarEndereco}
                disabled={buscando}>
                {buscando ? "Buscando..." : "Buscar"}
              </button>
            </div>
            {erroCep && <p className="erro">{erroCep}</p>}
            {endereco && !erroCep && (
              <div className="endereco-info">
                <p>
                  <strong>Rua:</strong> {endereco.logradouro}
                </p>
                <p>
                  <strong>Bairro:</strong> {endereco.bairro}
                </p>
                <p>
                  <strong>Número da Casa:</strong>
                  <input
                    type="text"
                    value={numeroCasa}
                    onChange={(e) => setNumeroCasa(e.target.value)}
                    placeholder="Digite o número da casa"
                  />
                </p>
              </div>
            )}
          </div>
        )}

        {opcaoEntrega === "local" && (
          <div className="local-info">
            <p>Você pode consumir sua refeição no nosso espaço!</p>
          </div>
        )}

        <div className="opcao-entrega">
          <label>Opção de Pagamento:</label>
          <select
            value={opcaoPagamento}
            onChange={(e) => setOpcaoPagamento(e.target.value)}
          >
            <option value="Pix">Pix</option>
            <option value="Cartão de Crédito">Cartão de Crédito</option>
            <option value="Cartão de Débito">Cartão de Débito</option>
          </select>
        </div>
        <button className="finalizar" onClick={() => {checarDados(); 
          if (!erro) {router.push("/pagamento/finalizado")}}}>
          Finalizar Pedido
        </button>
        </div>
      </div>
      <Footer/>
    </div>
  );
};

export default Pagamento;
