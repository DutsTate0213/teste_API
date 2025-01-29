package com.datamonki.ApiCadastro.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datamonki.ApiCadastro.exceptions.IdNotFoundException;
import com.datamonki.ApiCadastro.model.Turno;
import com.datamonki.ApiCadastro.repository.TurnoRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.exceptions.ValidationException;
import com.datamonki.ApiCadastro.dto.TurnoDto;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;
    
    private void verificar(TurnoDto turnoDto) {
        List<String> messages = new ArrayList<>();
        if (turnoDto.ativo() != 0 && turnoDto.ativo() != 1) {
            messages.add("O campo ativo deve ser 0 (desativado) ou 1 (ativado)");
        }
        if (!messages.isEmpty()) {
            throw new ValidationException(messages);
        }
    }

    private void verificarId(Integer id) {
        if (!turnoRepository.existsById(id)) {
            throw new IdNotFoundException("Não foi possivel encontrar turno com o Id '" + id + "', verifique e tente novamente"); 
        }
    }

    
    public ResponseEntity<ApiResponse> getAll(){

        List<Turno> turnos = turnoRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Lista de Turno cadastrados", turnos));
    }

    public ResponseEntity<ApiResponse> getById(Integer id_turno){
        verificarId(id_turno);

        Turno turno = turnoRepository.findById(id_turno).get();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Turno com o id '"+ id_turno +"' encontrado com sucesso", turno));
    }

    @Transactional
    public ResponseEntity<ApiResponse> updateStatus(Integer id, TurnoDto turnoDto) {
        verificar(turnoDto);
        verificarId(id);

        Turno turno = turnoRepository.findById(id).get();
        turno.setAtivo(turnoDto.ativo());
        turnoRepository.save(turno);
        
        String status = turnoDto.ativo() == 1 ? "ativado" : "desativado";
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponse("Turno com o id '" + id + "' foi " + status + " com sucesso", turno));
    }
}