package com.datamonki.ApiCadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.datamonki.ApiCadastro.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {
    
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
           "FROM turma WHERE nome = :nome AND semestre = :semestre " +
           "AND ano = :ano AND id_curso = :idCurso AND id_turno = :idTurno", 
           nativeQuery = true)
    Boolean verifyRepeticao(@Param("nome") String nome,
                           @Param("semestre") Integer semestre,
                           @Param("ano") Integer ano,
                           @Param("idCurso") Integer idCurso,
                           @Param("idTurno") Integer idTurno);
}
