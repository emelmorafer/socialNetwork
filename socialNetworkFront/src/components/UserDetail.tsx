import React,{useState, useEffect} from 'react';

interface Useer {
    id: number;
    name: string;
    lastName: string;
    dateBirth: string; 
    eMail: string;
    username: string;
}

interface UserDetailProps {
    objectUserProp: Useer;
}

function UserDetail({objectUserProp} : UserDetailProps){

    const {id,name,lastName,dateBirth,eMail,username} = objectUserProp

    const date = new Date(dateBirth);

    return(
        <div className="presentationBlockStyle">
            <div className="cartBlockStyle">

                <div style={{color: 'white', padding: '10px 20px', width: '50%'}}>
                    <a className='cartTittleText'>
                        Nombre completo: {name} {lastName}
                    </a> 
                    <br/><br/>
                    <a className='cartTittleText'>
                        Username: {username}
                    </a>                                         
                </div> 

                <div style={{color: 'white', padding: '10px 20px', width: '50%'}}>     
                    <p className='descripText'>Fecha Nacimiento: {dateBirth}</p>
                    <p className='descripText'>Correo Electronico: {eMail}</p>
                </div>    

            </div>            
        </div>
    )
}

export default UserDetail;