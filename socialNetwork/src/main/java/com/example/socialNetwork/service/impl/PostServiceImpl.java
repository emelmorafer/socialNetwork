package com.example.socialNetwork.service.impl;


import com.example.socialNetwork.dto.PostDto;
import com.example.socialNetwork.model.Post;
import com.example.socialNetwork.repository.PostRepository;
import com.example.socialNetwork.repository.UserRepository;
import com.example.socialNetwork.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    public PostDto savePost(PostDto postDto) {

        if (!postDto.getContent().equals("")) {
            Post post = new Post(
                    postDto.getId(),
                    postDto.getContent(),
                    postDto.getLikeNumber(),
                    postDto.getPublicationDate(),
                    userRepository.findById(postDto.authorId).orElse(null)
            );
            return domainToDto(postRepository.save(post));
        } else {
            return new PostDto();
        }
    }

    public PostDto getPostById(long id) {
        Optional<Post> postOpt = postRepository.findById(id);
        return postOpt.map(this::domainToDto).orElse(null);
    }

    public List<PostDto> getListPost(){
        return postRepository.findAll()
                .stream()
                .map(this::domainToDto)
                .collect(Collectors.toList());
    }

    public List<PostDto> getListPostById(long idUser){
        return postRepository.findAll()
                .stream()
                .filter(p -> p.getUser().getId()==idUser)
                .map(this::domainToDto)
                .collect(Collectors.toList());
    }

    public List<PostDto> getListPostByNotId(long idUser){
        return postRepository.findAll()
                .stream()
                .filter(p -> p.getUser().getId()!=idUser)
                .map(this::domainToDto)
                .collect(Collectors.toList());
    }

    public boolean deletePostById(long id) {
        try {
            postRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public PostDto domainToDto(Post post) {
        return new PostDto(
                post.id,
                post.content,
                post.likeNumber,
                post.publicationDate,
                post.getUser().getId(),
                post.getUser().getUsername());
    }

}
