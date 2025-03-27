import {useState, useEffect} from 'react';
import {Link, NavLink} from 'react-router-dom'


function LeftBlock(){  
    
    return(
        <div className="leftBlockStyle">
            <h1 style={{color: 'white', marginLeft: '10px', fontSize: '28px'}}>Opciones</h1>
            
            <Link to="/adminuser"          className="redirectionButton">Detalles de Usuario</Link>
            <br/>
            <Link to="/adminpost" className="redirectionButton">Publicaciones</Link>
            <br/>
            <Link to="/" className="redirectionButton">Cerrar Sesion</Link>
    
        </div>
    )
}

export default LeftBlock;