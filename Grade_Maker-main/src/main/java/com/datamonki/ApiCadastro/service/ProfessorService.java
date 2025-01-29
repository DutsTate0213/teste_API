package com.datamonki.ApiCadastro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.ApiCadastro.dto.ProfessorDto;
import com.datamonki.ApiCadastro.exceptions.IdNotFoundException;
import com.datamonki.ApiCadastro.exceptions.ValidationException;
import com.datamonki.ApiCadastro.model.Professor;
import com.datamonki.ApiCadastro.repository.ProfessorRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    private void verificar(ProfessorDto professorDto){
        List<String> messages = new ArrayList<>();
        if (professorDto.nome().isBlank()) {
            messages.add("A coluna nome está vazia");
        } else if (professorDto.nome().length() < 3 || professorDto.nome().length() > 255) {
            messages.add("A coluna nome deve estar entre 3 a 255 caracteres");
        }
        if (!messages.isEmpty()) {
            throw new ValidationException(messages);
        }
    }

    private void verificarId(Integer id) {
        if (!professorRepository.existsById(id)) {
            throw new IdNotFoundException("Não foi possivel encontrar professor com o Id '" + id + "', verifique e tente novamente"); 
        }
    }

    private void verificarNome(String nome) {
        if (professorRepository.findByNomeContainingIgnoreCase(nome).isEmpty()) {
            throw new IdNotFoundException("Não foi possivel encontrar o professor com o nome de '" + nome + "', verifique e tente novamente"); 
        }
    }
    
    
    @Transactional
    public ResponseEntity<ApiResponse> create(ProfessorDto professorDto){
        verificar(professorDto);

        Professor professor = new Professor();
        professor.setNome(professorDto.nome());

        professorRepository.save(professor);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Professor cadastrado com sucesso", professor));
    }

    public ResponseEntity<ApiResponse> getAll(){
        List<Professor> professores = professorRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Lista de professores cadastrados", professores));
    }

    public ResponseEntity<ApiResponse> getById(Integer id_professor){
        verificarId(id_professor);

        Professor professor = professorRepository.findById(id_professor).get();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Professor com o id '"+ id_professor +"' encontrado com sucesso", professor));
    }

    public ResponseEntity<ApiResponse> getByNome(String nome){
        verificarNome(nome);

        List<Professor> professor = professorRepository.findByNomeContainingIgnoreCase(nome);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Professor com o nome '"+ nome +"' encontrado com sucesso", professor));
    }

    @Transactional
    public ResponseEntity<ApiResponse> update(Integer id_professor, ProfessorDto professorDto){
        verificar(professorDto);
        verificarId(id_professor);

        Professor professor = new Professor();
        professor.setId(id_professor);
        professor.setNome(professorDto.nome());
        professorRepository.save(professor);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Professor com o id '" + id_professor + "' atualizado com sucesso", professor));
    }

    public ResponseEntity<ApiResponse> deleteById(Integer id_professor){
        verificarId(id_professor);

        Professor professor = professorRepository.findById(id_professor).get();
        professorRepository.deleteById(id_professor);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Professor com o id '"+ id_professor +"' foi deletado com sucesso", professor));
    }

    public ResponseEntity<ApiResponse> deleteAll(){
        List<Professor> professores = professorRepository.findAll();
        professorRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Todos os professores foram deletados com sucesso", professores));
    }
}