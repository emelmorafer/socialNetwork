import {useState, useEffect} from 'react';
import Header from "../components/Header" 
import LeftBlock from "../components/LeftBlock"
import UserDetail from "../components/UserDetail"
import { useUserSession } from '../context/UserSessionContext';
import { API_BASE_URL } from '../config/config';

export default function UserPage(){ 
    
    const { userSession } = useUserSession();
    const [objectUser, setObjectUser] = useState({id:'', name:'', lastName:'',dateBirth:'', eMail:'', username:''});

    useEffect(() => {
        if(userSession.id!=0){
            const fetchData = async () => {
                try {                
                    const response = await fetch(API_BASE_URL + "/socialnetwork/usuario/" + userSession.id, {
                        method: 'GET',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                    });
                    const data = await response.json()
                    setObjectUser(data);   
                } catch (error) {
                    console.error('Error fetching data:', error);
                }
            };
            fetchData();
        }
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
