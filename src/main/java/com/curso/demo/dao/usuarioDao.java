package com.curso.demo.dao;

import com.curso.demo.models.Usuario;

import java.util.List;

public interface usuarioDao {

    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario usuarioCredenciales(Usuario usuario);
}
