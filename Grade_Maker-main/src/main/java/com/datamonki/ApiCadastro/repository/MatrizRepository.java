package com.datamonki.ApiCadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.datamonki.ApiCadastro.model.Matriz;

@Repository
public interface MatrizRepository extends JpaRepository<Matriz, Integer> {
    
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
           "FROM matriz WHERE id_turma = :idTurma AND id_disciplina = :idDisciplina",
           nativeQuery = true)
    Boolean verifyRepeticao(@Param("idTurma") Integer idTurma,
                           @Param("idDisciplina") Integer idDisciplina);
}
