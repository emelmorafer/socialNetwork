package com.example.socialNetwork.service;

import com.example.socialNetwork.dto.PostDto;

import java.util.List;

public interface PostService {

    public PostDto savePost(PostDto postDto);

    public PostDto getPostById(long id);

    public List<PostDto> getListPost();

    public List<PostDto> getListPostById(long idUser);

    public List<PostDto> getListPostByNotId(long idUser);

    public boolean deletePostById(long id);
}
