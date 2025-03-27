import React, {useState, useEffect, ChangeEvent, FormEvent} from 'react';

import PostRow from "./PostRow"

interface Post {
    id: number;
    content: string;
    likeNumber: number;
    publicationDate: string;
    authorId: number;
    authorUsername: string;
}

interface ListPostsProps {
    setPostIdLikedProp: React.Dispatch<React.SetStateAction<number>>;
    setClickedLikeButtonProp: React.Dispatch<React.SetStateAction<boolean>>;
    listPostsProp: Post[];
}    

function ListPosts({setPostIdLikedProp,setClickedLikeButtonProp,listPostsProp} : ListPostsProps){

    const handleClickLikePost = (idPost: number) => {
        setPostIdLikedProp(idPost)
        setClickedLikeButtonProp(true)
    }

    return(
        <div className="presentationBlockStyle">
            {listPostsProp.map((post) => (
                <PostRow key={post.id} postProp={post}
                handleClickLikePostProp={handleClickLikePost}/>
            ))}
            
        </div>
    )
}

export default ListPosts;