package com.datamonki.ApiCadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.ApiCadastro.exceptions.IdNotFoundException;
import com.datamonki.ApiCadastro.model.Turno;
import com.datamonki.ApiCadastro.repository.TurnoRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;
    
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
}