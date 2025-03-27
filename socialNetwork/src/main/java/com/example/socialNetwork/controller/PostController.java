package com.example.socialNetwork.controller;

import com.example.socialNetwork.dto.PostDto;
import com.example.socialNetwork.dto.UserDto;
import com.example.socialNetwork.service.PostService;
import com.example.socialNetwork.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("socialnetwork")
@Tag(name = "Publicaciones", description = "Operaciones relacionadas con Publicaciones")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PostMapping(value = "/publicacion")
    @Operation(
            summary = "Crear una nueva publicacion",
            description = "Este endpoint permite registrar una nueva publicacion en el sistema."
    )
    public ResponseEntity<?> createPost(
            @RequestParam(value = "content") String content,
            @RequestParam(value = "publicationDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date publicationDate,
            @RequestParam(value = "idAuthor") Long idAuthor) {

        UserDto userDto = userService.getUserById(idAuthor);

        if(userDto!=null){
            PostDto postDto = new PostDto(null,content,0L,publicationDate,idAuthor,"");
            return ResponseEntity.ok(postService.savePost(postDto));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El id del Autor ingresado no existe.");
        }


    }


    @PutMapping(value = "/publicacion")
    @Operation(
            summary = "Editar una publicacion",
            description = "Este endpoint editar una publicacion ya creada en el sistema."
    )
    public ResponseEntity<?> updateUser(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "likeNumber") Long likeNumber) {

        PostDto postDto = postService.getPostById(id);

        if(postDto!=null){
            if (content != null) {
                postDto.setContent(content);
            }
            if (likeNumber != null) {
                postDto.setLikeNumber(likeNumber);
            }
            return ResponseEntity.ok(postService.savePost(postDto));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El id de usuario ingresado no existe.");
        }
    }


    @GetMapping(value = "/publicacion/{id}")
    @Operation(
            summary = "Obtener una publicacion por id",
            description = "Este endpoint permite obtener una publicacion por su id."
    )
    public ResponseEntity<?> getPostByID(@PathVariable(value="id") long id) {

        PostDto postDto = postService.getPostById(id);
        if(postDto!=null){
            return ResponseEntity.ok(postDto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El id de usuario ingresado no existe.");
        }
    }


    @GetMapping(value = "/publicacion/list")
    @Operation(
            summary = "Obtener lista de publicaciones",
            description = "Este endpoint permite obtener una lista de todas las publicaciones creadas en el sistema."
    )
    public List<PostDto> getListPost() {
        return postService.getListPost();
    }


    @GetMapping(value = "/publicacion/listbyuser/{idUser}")
    @Operation(
            summary = "Obtener lista de publicaciones por ususario",
            description = "Este endpoint permite obtener una lista de todas las publicaciones pertenecientes a un usuario."
    )
    public List<PostDto> getListPostById(@PathVariable(value="idUser") long idUser) {
        return postService.getListPostById(idUser);
    }


    @GetMapping(value = "/publicacion/listbynotuser/{idUser}")
    @Operation(
            summary = "Obtener lista de publicaciones que no corrsponden a mi ususario",
            description = "Este endpoint permite obtener una lista de todas las publicaciones que no son pertenecientes a mi usuario."
    )
    public List<PostDto> getListPostByNotId(@PathVariable(value="idUser") long idUser) {
        return postService.getListPostByNotId(idUser);
    }


    @DeleteMapping(value = "/publicacion/{id}")
    @Operation(
            summary = "Eliminar una publicacion por id",
            description = "Este endpoint permite eliminar una publicacion por su id."
    )
    public ResponseEntity<?> deletePostById(@PathVariable(value="id") long id) {

        PostDto postDto = postService.getPostById(id);

        if(postDto!=null){
            if(postService.deletePostById(id)){
                return ResponseEntity.ok("La publicacion fue eliminada exitosamente");
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("La publicacion no pudo ser eliminada.");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La publicacion que intenta eliminar no existe.");
        }
    }
}
