package com.datamonki.ApiCadastro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datamonki.ApiCadastro.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    // Busca pelo nome
    Optional<Professor> findByNome(String nome);

    // Busca um professor pelo nome ignorando o case(nao diferencia maiusculo de minusculo)
    List<Professor> findByNomeContainingIgnoreCase(String nome);
}