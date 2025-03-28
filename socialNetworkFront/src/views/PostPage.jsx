import {useState, useEffect} from 'react';
import Header from "../components/Header"
import LeftBlock from "../components/LeftBlock"
import ListPosts from "../components/ListPosts"
import { useUserSession } from '../context/UserSessionContext';
import { API_BASE_URL } from '../config/config';

export default function PostPage(){
    
    const { userSession } = useUserSession();
    const [listPosts, setListPosts] = useState([]);
    const [updateListPosts, setUpdateListPosts] = useState(true);

    const token = localStorage.getItem("authToken");

    useEffect(() => {
        if(updateListPosts){
            const fetchData = async () => {
                try {
                    const response = await fetch(API_BASE_URL + "/socialnetwork/publicacion/listbynotuser/" + userSession.id, {
                        method: 'GET',
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${token}`
                        },
                    });
                    const data = await response.json();
                    setListPosts(data);
                } catch (error) {
                    console.error('Error fetching data:', error);
                }           
            };
            fetchData();
        }
        setUpdateListPosts(false)
    }, [updateListPosts]);



    const [postIdLiked, setPostIdLiked] = useState(0);
    const [clickedLikeButton, setClickedLikeButton] = useState(false);
    const [objectPost, setObjectPost] = useState({id:'', content:'', likeNumber:0, publicationDate:'', authorId:0, authorUsername:''});


    useEffect(() => {
        if(clickedLikeButton){
            const fetchData = async () => {
                try {
                    const responseGet = await fetch(API_BASE_URL + "/socialnetwork/publicacion/" + postIdLiked, {
                        method: 'GET',
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${token}`
                        },
                    });
                    const data = await responseGet.json()                   
                    setObjectPost(data);  

                    const params = new URLSearchParams({
                        id: objectPost.id,
                        content: objectPost.content,
                        likeNumber: objectPost.likeNumber + 1,
                    });

                    const url = `${API_BASE_URL}/socialnetwork/publicacion?${params.toString()}`;
                    
                    const responsePut = await fetch(url, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${token}`
                        },
                    });

                    if (responsePut.ok) {      
                        setUpdateListPosts(true)
                    } else {
                        console.error('Error in request:', response);
                        setError('Error in response from server.');
                    }


                } catch (error) {
                    console.error('Error fetching data:', error);
                }          
            };
            fetchData();
        }
        setClickedLikeButton(false)
    }, [clickedLikeButton]);


    return (
        <>
            <Header textHeaderProp={"Lista de Comentarios"}/>
            <div class="container"> 
                <LeftBlock />

                <ListPosts 
                setPostIdLikedProp={setPostIdLiked} 
                setClickedLikeButtonProp={setClickedLikeButton} 
                listPostsProp={listPosts}/>
            </div>
        </>
    ) 
}