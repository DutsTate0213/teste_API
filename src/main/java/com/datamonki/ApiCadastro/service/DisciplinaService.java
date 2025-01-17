package com.datamonki.ApiCadastro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.ApiCadastro.dto.DisciplinaDto;
import com.datamonki.ApiCadastro.exceptions.IdNotFoundException;
import com.datamonki.ApiCadastro.exceptions.ValidationException;
import com.datamonki.ApiCadastro.model.Disciplina;
import com.datamonki.ApiCadastro.repository.DisciplinaRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    private void verificar(DisciplinaDto disciplinaDto){
        List<String> messages = new ArrayList<>();
        if (disciplinaDto.nome().isBlank()) {
            messages.add("A coluna nome está vazia");
        } else if (disciplinaDto.nome().length() < 3 || disciplinaDto.nome().length() > 255) {
            messages.add("A coluna nome deve estar entre 3 a 255 caracteres");
        }
        if (!messages.isEmpty()) {
            throw new ValidationException(messages);
        }
    }

    private void verificarId(Integer id) {
        if (!disciplinaRepository.existsById(id)) {
            throw new IdNotFoundException("Não foi possivel encontrar disciplina com o Id '" + id + "', verifique e tente novamente"); 
        }
    }

    private void verificarNome(String nome) {
        if (disciplinaRepository.findByNomeContainingIgnoreCase(nome).isEmpty()) {
            throw new IdNotFoundException("Não foi possivel encontrar a disciplina com o nome de '" + nome + "', verifique e tente novamente"); 
        }
    }

    
    @Transactional
    public ResponseEntity<ApiResponse> create(DisciplinaDto disciplinaDto){
        verificar(disciplinaDto);

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(disciplinaDto.nome());

        disciplinaRepository.save(disciplina);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Disciplina cadastrada com sucesso", disciplina));
    }

    public ResponseEntity<ApiResponse> getAll(){
        List<Disciplina> disciplinas = disciplinaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Lista de disciplinas cadastradas", disciplinas));
    }

    public ResponseEntity<ApiResponse> getById(Integer id_disciplina){
        verificarId(id_disciplina);

        Disciplina disciplina = disciplinaRepository.findById(id_disciplina).get();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Disciplina com o id '"+ id_disciplina +"' encontrada com sucesso", disciplina));
    }

    public ResponseEntity<ApiResponse> getByNome(String nome){
        verificarNome(nome);

        List<Disciplina> disciplina = disciplinaRepository.findByNomeContainingIgnoreCase(nome);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Disciplina com o nome '"+ nome +"' encontrada com sucesso", disciplina));
    }

    @Transactional
    public ResponseEntity<ApiResponse> update(Integer id_disciplina, DisciplinaDto disciplinaDto){
        verificar(disciplinaDto);
        verificarId(id_disciplina);

        Disciplina disciplina = new Disciplina();
        disciplina.setId(id_disciplina);
        disciplina.setNome(disciplinaDto.nome());
        disciplinaRepository.save(disciplina);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Disciplina com o id '" + id_disciplina + "' atualizada com sucesso", disciplina));
    }

    public ResponseEntity<ApiResponse> deleteById(Integer id_disciplina){
        verificarId(id_disciplina);

        Disciplina disciplina = disciplinaRepository.findById(id_disciplina).get();
        disciplinaRepository.deleteById(id_disciplina);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Disciplina com o id '"+ id_disciplina +"' foi deletada com sucesso", disciplina));
    }

    public ResponseEntity<ApiResponse> deleteAll(){
        List<Disciplina> disciplinas = disciplinaRepository.findAll();
        disciplinaRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Todas as disciplinas foram deletadas com sucesso", disciplinas));
    }
}