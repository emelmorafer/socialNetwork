import React, {useState, useEffect, ChangeEvent, FormEvent} from 'react';
import { API_BASE_URL } from '../config/config';
import { useNavigate } from 'react-router-dom';
import { useUserSession } from '../context/UserSessionContext';
import { jwtDecode } from "jwt-decode";


function LoginForm(){

    const [usernameForm, setUsernameForm] = useState<string>('');
    const [passwordForm, setPasswordForm] = useState<string>('');
    const [error, setError] = useState<string>('');

    const { setUserSession } = useUserSession();
    const navigate = useNavigate();


    const handleSubmit = async (event: FormEvent) => {

        event.preventDefault();
        if (!usernameForm || !passwordForm) {
            setError('Todos los campos son requeridos');
            return;
        }

        try {
            const response = await fetch(API_BASE_URL + "/socialnetwork/usuario/login", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ username: usernameForm, password: passwordForm })
            });
        
            if (response.ok) {  
                const token = await response.text();
                localStorage.setItem('authToken', token);

                const decodedToken = jwtDecode(token)

                const responseUser = await fetch(API_BASE_URL + "/socialnetwork/usuario/username/" + decodedToken.sub, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                });
                const data = await responseUser.json()
                setUserSession(data);  

                navigate('/adminuser');
            } else {
                console.error('Error in request:', response);
                setError('Error in response from server.');
            }                      
        } catch (error) {
            console.error('Error fetching data:', error);
        } 
    }


    return(
        <div className="presentationBlockStyle">
            <div className="cartBlockStyle">
                <form className="creaEditForm" onSubmit={handleSubmit}>
                    <div style={{color: 'white', padding: '10px 20px', width: '70%'}}>
                        <input
                            id="username" className="inputform"
                            type="text"
                            placeholder="Nombre de Usuario"
                            value={usernameForm}
                            onChange={(e) => setUsernameForm(e.target.value)}
                        />
                        <br/>
                        <input
                            id="password" className="inputform"
                            type="text"
                            placeholder="Contraseña"
                            value={passwordForm}
                            onChange={(e) => setPasswordForm(e.target.value)}
                        />
                        {error && <p style={{ color: 'red' }}>{error}</p>}
                    </div>
                    <div style={{ padding: '10px 20px',width: '30%'}}>
                        <button type="submit" className="buttoncreaEditFormAction">Login</button> 
                    </div>
                </form > 
            </div>            
        </div>
    )
}

export default LoginForm;