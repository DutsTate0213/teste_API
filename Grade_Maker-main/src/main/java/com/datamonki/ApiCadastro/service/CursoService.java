package com.datamonki.ApiCadastro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.ApiCadastro.dto.CursoDto;
import com.datamonki.ApiCadastro.exceptions.IdNotFoundException;
import com.datamonki.ApiCadastro.exceptions.ValidationException;
import com.datamonki.ApiCadastro.model.Curso;
import com.datamonki.ApiCadastro.repository.CursoRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    private void verificar(CursoDto cursoDto){
		List<String> messages = new ArrayList<>();
		if (cursoDto.nome().isBlank()) {
			messages.add("A coluna nome está vazia");
		} else if (cursoDto.nome().length() < 3 || cursoDto.nome().length() > 255) {
			messages.add("A coluna nome deve estar entre 3 a 255 caracteres");
		}
		if (!messages.isEmpty()) {
			throw new ValidationException(messages);
		}
    }

	private void verificarId(Integer id) {
		if (!cursoRepository.existsById(id)) {
			throw new IdNotFoundException("Não foi possivel encontrar curso com o Id '" + id + "', verifique e tente novamente"); 
		}
	}

	private void verificarNome(String nome) {
		if (cursoRepository.findByNomeContainingIgnoreCase(nome).isEmpty()) {
			throw new IdNotFoundException("Não foi possivel encontrar o curso com o nome de '" + nome + "', verifique e tente novamente"); 
		}
	}

    
    @Transactional
    public ResponseEntity<ApiResponse> create(CursoDto cursoDto){
        verificar(cursoDto);

        Curso curso = new Curso();
        curso.setNome(cursoDto.nome());

        cursoRepository.save(curso);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Curso cadastrado com sucesso", cursoDto));
    }

    public ResponseEntity<ApiResponse> getAll(){
        List<Curso> cursos = cursoRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Lista de cursos cadastrados", cursos));
    }

    public ResponseEntity<ApiResponse> getById(Integer id_curso){
        verificarId(id_curso);

        Curso curso = cursoRepository.findById(id_curso).get();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Curso com o id '"+ id_curso +"' encontrado com sucesso", curso));
    }

    public ResponseEntity<ApiResponse> getByNome(String nome){
        verificarNome(nome);

        List<Curso> curso = cursoRepository.findByNomeContainingIgnoreCase(nome);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Curso com o nome '"+ nome +"' encontrado com sucesso", curso));
    }

    public ResponseEntity<ApiResponse> getByOrderNome(){
        List<Curso> curso = cursoRepository.findByOrderByNomeAsc();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Lista de cursos cadastrados ordenados por nome", curso));
    }

    @Transactional
    public ResponseEntity<ApiResponse> update(Integer id_curso, CursoDto cursoDto){
        verificar(cursoDto);
        verificarId(id_curso);

        Curso curso = new Curso();
        curso.setId(id_curso);
        curso.setNome(cursoDto.nome());
        cursoRepository.save(curso);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Curso com o id '" + id_curso + "' atualizado com sucesso", cursoDto));
    }

    public ResponseEntity<ApiResponse> deleteById(Integer id_curso){
        verificarId(id_curso);

        Curso curso = cursoRepository.findById(id_curso).get();
        CursoDto cursoDto = new CursoDto(curso.getNome());
        cursoRepository.deleteById(id_curso);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Curso com o id '"+ id_curso +"' foi deletado com sucesso", cursoDto));
    }

    public ResponseEntity<ApiResponse> deleteAll(){
        List<Curso> cursos = cursoRepository.findAll();
        cursoRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Todos os cursos foram deletados com sucesso", cursos));
    }
    

    
}