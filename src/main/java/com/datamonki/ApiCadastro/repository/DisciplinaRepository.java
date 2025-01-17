package com.datamonki.ApiCadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datamonki.ApiCadastro.model.Disciplina;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
    // Busca pelo nome
    Optional<Disciplina> findByNome(String nome);

    // Busca uma disciplina pelo nome ignorando o case
    List<Disciplina> findByNomeContainingIgnoreCase(String nome);
}