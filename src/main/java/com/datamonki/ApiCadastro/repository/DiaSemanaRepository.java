package com.datamonki.ApiCadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datamonki.ApiCadastro.model.DiaSemana;

@Repository
public interface DiaSemanaRepository extends JpaRepository<DiaSemana, Integer> {

}
