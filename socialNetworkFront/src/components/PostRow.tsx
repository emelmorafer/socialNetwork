import React, {useState, useEffect} from 'react';

interface Post {
    id: number;
    content: string;
    likeNumber: number;
    publicationDate: string;
    authorId: number;
    authorUsername: string;
}

interface PostRowProps {
    postProp: Post;
    handleClickLikePostProp: (id: number) => void;
}

export default function PostRow({postProp,handleClickLikePostProp} : PostRowProps){

    const {id,content,likeNumber,publicationDate,authorId,authorUsername} = postProp

    const date = new Date(publicationDate);

    return(
        <>
            <div className="cartBlockStyle">

                <div style={{color: 'white', padding: '10px 20px', width: '30%'}}>
                    <a className='cartTittleText'>
                        Contenido:
                    </a> 
                    <br/>
                    <a className='descripText'>
                        {content}
                    </a>                                         
                </div> 

                <div style={{color: 'white', padding: '10px 20px', width: '50%'}}>     
                    <p className='descripText'>Fecha Publicacion: {date.toISOString().split("T")[0]}</p>
                    <p className='descripText'>Usuario: {authorUsername}</p>
                </div> 

                <div style={{color: 'white', padding: '10px 20px',width: '20%'}}>
                    <button className='editUserButton' onClick={() => handleClickLikePostProp(id)}>
                        <img src="/images/like.png" style={{width: '20px', height: '20px', marginRight: '10px'}}/>Like
                    </button>
                    <p className='descripText'>Numero Likes: {likeNumber}</p>
                </div>    

            </div>
        </>        
    )
}
