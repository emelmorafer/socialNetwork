import {useState, useEffect} from 'react';
import Header from "../components/Header" 
import LeftBlock from "../components/LeftBlock"
import UserDetail from "../components/UserDetail"
import { useUserSession } from '../context/UserSessionContext';
import { API_BASE_URL } from '../config/config';

export default function UserPage(){ 
    
    const { userSession } = useUserSession();
    const [objectUser, setObjectUser] = useState({id:'', name:'', lastName:'',dateBirth:'', eMail:'', username:''});

    const token = localStorage.getItem("authToken");

    useEffect(() => {
        setObjectUser(userSession);
    }, [userSession.id]);


    return (
        <>
            <Header textHeaderProp={"Detalle de Usuario"}/>
            <div class="container">
                <LeftBlock />

                <UserDetail objectUserProp={objectUser} />
            </div>
        </>
    ) 
}
