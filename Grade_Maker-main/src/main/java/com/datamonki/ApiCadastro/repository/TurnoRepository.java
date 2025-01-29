package com.datamonki.ApiCadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datamonki.ApiCadastro.model.Turno;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer> {

}
