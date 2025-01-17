package com.datamonki.ApiCadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datamonki.ApiCadastro.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {

}
