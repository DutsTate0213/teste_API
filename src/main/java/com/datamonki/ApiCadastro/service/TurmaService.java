package com.datamonki.ApiCadastro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.ApiCadastro.dto.TurmaDto;
import com.datamonki.ApiCadastro.exceptions.IdNotFoundException;
import com.datamonki.ApiCadastro.exceptions.ValidationException;
import com.datamonki.ApiCadastro.model.Turma;
import com.datamonki.ApiCadastro.repository.CursoRepository;
import com.datamonki.ApiCadastro.repository.TurmaRepository;
import com.datamonki.ApiCadastro.repository.TurnoRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TurnoRepository turnoRepository;

    private void verificar(TurmaDto turmaDto){
        List<String> messages = new ArrayList<>();
        
        if (turmaDto.nome().isBlank()) {
            messages.add("A coluna nome está vazia");
        } else if (turmaDto.nome().length() < 1 || turmaDto.nome().length() > 255) {
            messages.add("A coluna nome deve estar entre 1 a 255 caracteres");
        }

        if (turmaDto.semestre() < 1 || turmaDto.semestre() > 2) {
            messages.add("O semestre deve ser 1 ou 2");
        }

        if (turmaDto.ano() < 2010 || turmaDto.ano() > 2050) {
            messages.add("O ano deve estar entre 2010 e 2050");
        }

        if (!messages.isEmpty()) {
            throw new ValidationException(messages);
        }
    }

    private void verificarIdTurma(Integer id) {
        if (!turmaRepository.existsById(id)) {
            throw new IdNotFoundException("Não foi possivel encontrar turma com o Id '" + id + "', verifique e tente novamente"); 
        }
    }

    private void verificarIdCurso(Integer id) {
        if (!cursoRepository.existsById(id)) {
            throw new IdNotFoundException("Não foi possivel encontrar curso com o Id '" + id + "', verifique e tente novamente"); 
        }
    }

    private void verificarIdTurno(Integer id) {
        if (!turnoRepository.existsById(id)) {
            throw new IdNotFoundException("Não foi possivel encontrar turno com o Id '" + id + "', verifique e tente novamente"); 
        }
    }


    @Transactional
    public ResponseEntity<ApiResponse> create(TurmaDto turmaDto){
        verificarIdCurso(turmaDto.id_curso());
        verificarIdTurno(turmaDto.id_turno());
        verificar(turmaDto);

        Turma turma = new Turma();
        turma.setNome(turmaDto.nome());
        turma.setSemestre(turmaDto.semestre());
        turma.setAno(turmaDto.ano());
        turma.setCurso(cursoRepository.findById(turmaDto.id_curso()).get());
        turma.setTurno(turnoRepository.findById(turmaDto.id_turno()).get());
        
        turmaRepository.save(turma);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Turma cadastrada com sucesso", turma));
    }

    public ResponseEntity<ApiResponse> getAll(){
        List<Turma> turmas = turmaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Lista de turmas cadastradas", turmas));        
    }

    public ResponseEntity<ApiResponse> getById(Integer id_turma){
        verificarIdTurma(id_turma);

        Turma turma = turmaRepository.findById(id_turma).get();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Turma com o id '"+ id_turma +"' encontrada com sucesso", turma));        
    }

    @Transactional
    public ResponseEntity<ApiResponse> update(Integer id_turma, TurmaDto turmaDto){
        verificarIdCurso(turmaDto.id_curso());
        verificarIdTurno(turmaDto.id_turno());
        verificar(turmaDto);

        Turma turma = new Turma();
        turma.setId(id_turma);
        turma.setNome(turmaDto.nome());
        turma.setSemestre(turmaDto.semestre());
        turma.setAno(turmaDto.ano());
        turma.setCurso(cursoRepository.findById(turmaDto.id_curso()).get());
        turma.setTurno(turnoRepository.findById(turmaDto.id_turno()).get());
        
        turmaRepository.save(turma);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Turma com o id '" + id_turma + "' atualizada com sucesso", turma));
    }
    
    public ResponseEntity<ApiResponse> deleteById(Integer id_turma){
        verificarIdTurma(id_turma);
        
        Turma turma = turmaRepository.findById(id_turma).get();
        turmaRepository.deleteById(id_turma);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Turma com o id '"+ id_turma +"' deletada com sucesso", turma));        
    }
    
    public ResponseEntity<ApiResponse> deleteAll(){
        List<Turma> turmas = turmaRepository.findAll();
        turmaRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Todas as Turmas foram deletadas com sucesso", turmas));        
    }
}