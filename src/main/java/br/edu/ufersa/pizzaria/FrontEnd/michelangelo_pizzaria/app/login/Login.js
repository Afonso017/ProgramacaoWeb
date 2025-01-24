import React from 'react';
import './Login.css';

const Login = () => {
  return (
    <div className="login-container">
      <label htmlFor="email" className="login-label">Email</label>
      <input type="email" id="email" className="login-input" placeholder="Value" />

      <label htmlFor="password" className="login-label">Senha</label>
      <input type="password" id="password" className="login-input" placeholder="Value" />

      <button id="login-btn" className="login-button">Entrar</button>

      <a href="#" className="forgot-password">Esqueceu a senha?</a>
    </div>
  );
};

export default Login;