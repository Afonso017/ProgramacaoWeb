"use client";
import './Local.css';
import dynamic from 'next/dynamic';
import "leaflet/dist/leaflet.css";

const MapContainer = dynamic(() => import('react-leaflet').then(mod => mod.MapContainer), { ssr: false });
const TileLayer = dynamic(() => import('react-leaflet').then(mod => mod.TileLayer), { ssr: false });
const Marker = dynamic(() => import('react-leaflet').then(mod => mod.Marker), { ssr: false });
const Popup = dynamic(() => import('react-leaflet').then(mod => mod.Popup), { ssr: false });

const Local = () => {
    const position = [-5.1696907, -37.3561109];

    return (
        <div className='localContainer'>
            <h1>LOCAL</h1>
            <p>Av. Abel Coelho, 906 - Abolição III, Mossoró - RN, 59612-300</p>
            <MapContainer center={[-5.1696907, -37.3561109]} zoom={13} scrollWheelZoom={false}>
                <TileLayer
                    attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />
                <Marker position={[-5.1696907, -37.3561109]}>
                    <Popup>
                        Av. Abel Coelho, 906 - Abolição III, Mossoró - RN, 59612-300
                    </Popup>
                </Marker>
            </MapContainer>
        </div>
    );
};

export default Local;