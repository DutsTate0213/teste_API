package com.datamonki.ApiCadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.ApiCadastro.exceptions.IdNotFoundException;
import com.datamonki.ApiCadastro.model.DiaSemana;
import com.datamonki.ApiCadastro.repository.DiaSemanaRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;

@Service
public class DiaSemanaService {

    @Autowired
    private DiaSemanaRepository diaSemanaRepository;
    

    private void verificarId(Integer id) {
        if (!diaSemanaRepository.existsById(id)) {
            throw new IdNotFoundException("Não foi possivel encontrar Dia da Semana com o Id '" + id + "', verifique e tente novamente"); 
        }
    }

    public ResponseEntity<ApiResponse> getAll() {
        List<DiaSemana> diasSemana = diaSemanaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Lista de Dias da Semana cadastrados", diasSemana));
    }

    public ResponseEntity<ApiResponse> getById(Integer id) {
        verificarId(id);
        
        DiaSemana diaSemana = diaSemanaRepository.findById(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Dia da Semana com o id '"+ id +"' encontrado com sucesso", diaSemana));
    }
}