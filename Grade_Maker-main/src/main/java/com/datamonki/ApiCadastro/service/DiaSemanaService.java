package com.datamonki.ApiCadastro.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datamonki.ApiCadastro.exceptions.IdNotFoundException;
import com.datamonki.ApiCadastro.model.DiaSemana;
import com.datamonki.ApiCadastro.repository.DiaSemanaRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.dto.DiaSemanaDto;
import com.datamonki.ApiCadastro.exceptions.ValidationException;

@Service
public class DiaSemanaService {

    @Autowired
    private DiaSemanaRepository diaSemanaRepository;
    

    private void verificarId(Integer id) {
        if (!diaSemanaRepository.existsById(id)) {
            throw new IdNotFoundException("Não foi possivel encontrar Dia da Semana com o Id '" + id + "', verifique e tente novamente"); 
        }
    }

    private void verificar(DiaSemanaDto diaSemanaDto) {
        List<String> messages = new ArrayList<>();
        if (diaSemanaDto.ativo() != 0 && diaSemanaDto.ativo() != 1) {
            messages.add("O campo ativo deve ser 0 (desativado) ou 1 (ativado)");
        }
        if (!messages.isEmpty()) {
            throw new ValidationException(messages);
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

    @Transactional
    public ResponseEntity<ApiResponse> updateStatus(Integer id, DiaSemanaDto diaSemanaDto) {
        verificar(diaSemanaDto);
        verificarId(id);

        DiaSemana diaSemana = diaSemanaRepository.findById(id).get();
        diaSemana.setAtivo(diaSemanaDto.ativo());
        diaSemanaRepository.save(diaSemana);
        
        String status = diaSemanaDto.ativo() == 1 ? "ativado" : "desativado";
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponse("Dia da Semana com o id '" + id + "' foi " + status + " com sucesso", diaSemana));
    }
}