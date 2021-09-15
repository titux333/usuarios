package com.curso.demo.controllers;

import com.curso.demo.dao.usuarioDao;
import com.curso.demo.models.Usuario;
import com.curso.demo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private usuarioDao UsuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/iniciarSesion", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){

        Usuario usuarioOK=UsuarioDao.usuarioCredenciales(usuario);
        if(usuarioOK != null){

            String token= jwtUtil.create(String.valueOf(usuarioOK.getId()),usuarioOK.getEmail());

            return token;
        }else{
            return "FAIL";
        }
    }
}
