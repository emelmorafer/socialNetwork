package com.example.socialNetwork.controller;

import com.example.socialNetwork.dto.UserDto;
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
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping(value = "/usuario/login")
    @Operation(
            summary = "Realizar el Login del ususario",
            description = "Este endpoint permite Realizar el Login del ususario."
    )
    public ResponseEntity<?> loginUser(@RequestBody UserDto loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        UserDto userDto = userService.getUserByUsername(username);
        if (userDto != null) {
            if (password.equals(userDto.getPassword())) {
                return ResponseEntity.ok(userDto);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }
    }


    @PostMapping(value = "/usuario")
    @Operation(
            summary = "Crear un nuevo usuario",
            description = "Este endpoint permite registrar un nuevo usuario en el sistema."
    )
    public ResponseEntity<?> createUser(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "lastname") String lastname,
            @RequestParam(value = "dateBirth") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dateBirth,
            @RequestParam(value = "eMail") String eMail,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {

        UserDto userDto = new UserDto(null,name,lastname,dateBirth,eMail,username,password);
        return ResponseEntity.ok(userService.saveUser(userDto));
    }


    @PutMapping(value = "/usuario")
    @Operation(
            summary = "Editar un usuario",
            description = "Este endpoint editar un usuario ya creado en el sistema."
    )
    public ResponseEntity<?> updateUser(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "lastname") String lastname,
            @RequestParam(value = "dateBirth") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dateBirth,
            @RequestParam(value = "eMail") String eMail,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {

        UserDto userDto = userService.getUserById(id);

        if(userDto!=null){
            if (name != null) {
                userDto.setName(name);
            }
            if (lastname != null) {
                userDto.setLastName(lastname);
            }
            if (dateBirth != null) {
                userDto.setDateBirth(dateBirth);
            }
            if (eMail != null) {
                userDto.seteMail(eMail);
            }
            if (username != null) {
                userDto.setUsername(username);
            }
            if (password != null) {
                userDto.setPassword(password);
            }
            return ResponseEntity.ok(userService.saveUser(userDto));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El id de usuario ingresado no existe.");
        }
    }


    @GetMapping(value = "/usuario/{id}")
    @Operation(
            summary = "Obtener un usuario por id",
            description = "Este endpoint permite obtener un ususario por su id."
    )
    public ResponseEntity<?> getUserByID(@PathVariable(value="id") long id) {

        UserDto userDto = userService.getUserById(id);
        if(userDto!=null){
            return ResponseEntity.ok(userDto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El id de usuario ingresado no existe.");
        }
    }

    @GetMapping(value = "/usuario/username/{username}")
    @Operation(
            summary = "Obtener un usuario por username",
            description = "Este endpoint permite obtener un ususario por su username."
    )
    public ResponseEntity<?> getUserByUsername(@PathVariable(value="username") String username) {

        UserDto userDto = userService.getUserByUsername(username);
        if(userDto!=null){
            return ResponseEntity.ok(userDto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El username ingresado no existe.");
        }
    }


    @GetMapping(value = "/usuario/list")
    @Operation(
            summary = "Obtener lista de usuarios",
            description = "Este endpoint permite ontener una lista de todos los usuarios creados en el sistema."
    )
    public List<UserDto> getListUser() {
        return userService.getListUser();
    }


    @DeleteMapping(value = "/usuario/{id}")
    @Operation(
            summary = "Eliminar un usuario por id",
            description = "Este endpoint permite eliminar un ususario por su id."
    )
    public ResponseEntity<?> deleteUserById(@PathVariable(value="id") long id) {

        UserDto userDto = userService.getUserById(id);

        if(userDto!=null){
            if(userService.deleteUserById(id)){
                return ResponseEntity.ok("El usuario fue eliminado exitosamente");
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("El usuarioo no pudo ser eliminado.");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El usuario que intenta eliminar no existe.");
        }
    }
}
