// src/components/User/Login.js
import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { loginUser } from '../../redux/User/loginState';
import '../../css/Login.css';


function Login({ setScreen }) {
  const dispatch = useDispatch();
  const { loading, error, isAuthenticated } = useSelector((state) => state.login);

  const [userName, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = (e) => {
    e.preventDefault();
    dispatch(loginUser({ userName, password }));
  };

  useEffect(() => {
    if (isAuthenticated) {
      setScreen('main');
    }
  }, [isAuthenticated, setScreen]);

  return (
    <body className='login'>
      <div className='box'>
        <h2>LOGIN</h2>
        <br/>
        
        <br/><br/>
       
          <input
          type="text"
          placeholder="ID"
          value={userName}
          onChange={(e) => setUsername(e.target.value)}
        />
        <br/><br/>
        <input
          type="password"
          placeholder="PASSWORD"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
          <button className='loginbtn' onClick={handleLogin} disabled={loading}>
            {loading ? '로그인 중...' : '로그인'}
          </button>
          <br/><br/>
          <div className='signupbtn'>
          <button onClick={() => setScreen('signup')}>회원가입</button>
          <button onClick={() => setScreen('findUsername')}>아이디 찾기</button>
        </div></div>
    </body>
  );
}

export default Login;

