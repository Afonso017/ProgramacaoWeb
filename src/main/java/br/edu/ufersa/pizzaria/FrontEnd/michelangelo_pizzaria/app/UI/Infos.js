import Contato from './Contato.js';
import Local from './Local.js';
import './Infos.css'

const Infos = () => {
    return (
        <div className='infos'>
            <hr />
            <div className='infos-div'>
                <Contato />
                <hr className='vertical-line' />
                <Local />
            </div>
        </div>
    );
};

export default Infos;