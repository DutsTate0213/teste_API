package com.datamonki.ApiCadastro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.datamonki.ApiCadastro.model.Matriz;

@Repository
public interface MatrizRepository extends JpaRepository<Matriz, Integer> {

    // Buscar matriz pelo idTurma
    List<Matriz> findByTurmaId(Integer id_turma);

    // Buscar matriz pelo idDisciplina
    List<Matriz> findByDisciplinaId(Integer id_disciplina);

    // deletar matriz pelo idTurma
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM matriz WHERE id_turma = :id_turma", nativeQuery = true)
    void deleteByTurmaId(Integer id_turma);

    // deletar matriz pelo idDisciplina
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM matriz WHERE id_disciplina = :id_disciplina", nativeQuery = true)
    void deleteByDisciplinaId(Integer id_disciplina);

}
