package com.datamonki.ApiCadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datamonki.ApiCadastro.model.Curso;

import java.util.List;
import java.util.Optional;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

    // Busca pelo nome
    Optional<Curso> findByNome(String nome);

    // Busca um curso pelo nome ignorando o case(nao diferencia maiusculo de minusculo) 
    List<Curso> findByNomeContainingIgnoreCase(String nome);

    // Buscar curso ordenado por nome
    List<Curso> findByOrderByNomeAsc();
    
}