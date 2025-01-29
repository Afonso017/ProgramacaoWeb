import Image from 'next/image';
import './Contato.css';

const Contato = () => {
    return (
        <div className='contato'>
            <h1>CONTATO</h1>
            <div className='contato-div'>
                <div className="info">
                    <p>WhatsApp</p>
                    <p>(84) 99953-0028</p>
                </div>
                <div className="info">
                    <p>Redes Sociais</p>
                    <div className='footer-social-b'>
                        <a href="#" target="_blank" rel="noopener noreferrer">
                            <Image src="/Instagram_b.png" alt="Instagram" width={30} height={30} />
                        </a>
                        <a href="#" target="_blank" rel="noopener noreferrer">
                            <Image src="/Facebook_b.png" alt="Facebook" width={30} height={30} />
                        </a>
                    </div>
                </div>
                <div className="info">
                    <p>Horários de Funcionamento</p>
                    <div className="horarios">
                        <div className="sub-info">
                            <p>Seg à Sexta</p>
                            <p>Sáb e Dom</p>
                        </div>
                        <div className="sub-info">
                            <p>18:00 às 21:00</p>
                            <p>18:00 às 23:00</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Contato;