package com.datamonki.ApiCadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datamonki.ApiCadastro.model.DisciplinaCurso;

@Repository
public interface DisciplinaCursoRepository extends JpaRepository<DisciplinaCurso, Integer> {

}
