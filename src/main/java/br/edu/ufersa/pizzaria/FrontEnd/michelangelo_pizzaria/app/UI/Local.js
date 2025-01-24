import Image from 'next/image'

const Local = () => {
    return (
        <div className="local">
            <h1>LOCAL</h1>
            <p>Rua Firmino de Souza - 550
            Laffayete, RS</p>
            <Image src="/local_gps.png" width={539} height={434} alt="Imagem de gps do local da pizzaria" />
        </div>
    );
};

export default Local;