package com.datamonki.ApiCadastro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.datamonki.ApiCadastro.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {
       
    // buscar pelo curso
    List<Turma> findByCursoId(Integer id_curso);

    // deletar turma pelo idCurso
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM turma WHERE id_curso = :id_curso", nativeQuery = true)
    void deleteByCursoId(Integer id_curso);
    
}
