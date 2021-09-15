package com.curso.demo.controllers;

import com.curso.demo.dao.usuarioDao;
import com.curso.demo.models.Usuario;
import com.curso.demo.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController

public class UsuarioController {

    @Autowired
    private usuarioDao UsuarioDao;

    @Autowired
    private JWTUtil jwtUtil;


    @RequestMapping(value = "api/usuarios/{id}",method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Matias");
        usuario.setApellido("Campos");
        usuario.setEmail("camposdaniel962@hotmail.com");
        usuario.setTelefono("2213185495");
        usuario.setPassword("1M2a3t4i5a6s");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader (value="Authorization")String token){
        if(!validarToken(token)){return null;}

        return UsuarioDao.getUsuarios();
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){

        Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash= argon2.hash(1,1024,1, usuario.getPassword());
        usuario.setPassword(hash);


        UsuarioDao.registrar(usuario);
    }

    private boolean validarToken(String token){

        String usuarioId= jwtUtil.getKey(token);
        return usuarioId!= null;

    }


    @RequestMapping(value = "usuario1")
    public Usuario editar(@PathVariable Long id){
        Usuario usuario= new Usuario();
        usuario.setId(id);
        usuario.setNombre("Matias");
        usuario.setApellido("Campos");
        usuario.setEmail("camposdaniel962@hotmail.com");
        usuario.setTelefono("2213185495");
        usuario.setPassword("1M2a3t4i5a6s");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader (value="Authorization")String token, @PathVariable Long id){
        if(!validarToken(token)){return;}

        UsuarioDao.eliminar(id);
    }



}
