package com.leonardobanco.leonardoprojectbanco.repositorio;

import com.leonardobanco.leonardoprojectbanco.modelo.Estudante;

import java.util.List;

public interface EstudanteRepositorio {

    public void porid(int id);

    public List<Estudante> buscarTodos();

    public void inserir(Estudante estudante);

    public void apagar(int id);


}
