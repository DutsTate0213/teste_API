package com.datamonki.ApiCadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.ApiCadastro.dto.DisciplinaCursoDto;
import com.datamonki.ApiCadastro.exceptions.IdNotFoundException;
import com.datamonki.ApiCadastro.model.DisciplinaCurso;
import com.datamonki.ApiCadastro.repository.CursoRepository;
import com.datamonki.ApiCadastro.repository.DisciplinaCursoRepository;
import com.datamonki.ApiCadastro.repository.DisciplinaRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class DisciplinaCursoService {

    @Autowired
    private DisciplinaCursoRepository disciplinaCursoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    private void verificarIdDisciplinaCurso(Integer id) {
        if (!disciplinaCursoRepository.existsById(id)) {
            throw new IdNotFoundException("Não foi possivel encontrar disciplina-curso com o Id '" + id + "', verifique e tente novamente"); 
        }
    }

    private void verificarIdDisciplina(Integer id) {
        if (!disciplinaRepository.existsById(id)) {
            throw new IdNotFoundException("Não foi possivel encontrar disciplina com o Id '" + id + "', verifique e tente novamente"); 
        }
    }

    private void verificarIdCurso(Integer id) {
        if (!cursoRepository.existsById(id)) {
            throw new IdNotFoundException("Não foi possivel encontrar curso com o Id '" + id + "', verifique e tente novamente"); 
        }
    }

    
    @Transactional
    public ResponseEntity<ApiResponse> create(DisciplinaCursoDto DisciplinaCursoDto){
        verificarIdDisciplina(DisciplinaCursoDto.id_disciplina());
        verificarIdCurso(DisciplinaCursoDto.id_curso());

        DisciplinaCurso disciplinaCurso = new DisciplinaCurso();
        disciplinaCurso.setDisciplina(disciplinaRepository.findById(DisciplinaCursoDto.id_disciplina()).get());
        disciplinaCurso.setCurso(cursoRepository.findById(DisciplinaCursoDto.id_curso()).get());

        disciplinaCursoRepository.save(disciplinaCurso);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Conexão entre Disciplina e Curso feita com sucesso", disciplinaCurso));
    }

    public ResponseEntity<ApiResponse> getAll(){

        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Lista de conexões entre Disciplina e Curso", disciplinaCursos));
    }

    public ResponseEntity<ApiResponse> getById(Integer id_disciplinaCurso){
        verificarIdDisciplinaCurso(id_disciplinaCurso);

        DisciplinaCurso disciplinaCursos = disciplinaCursoRepository.findById(id_disciplinaCurso).get();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Conexão entre Disciplina e Curso com o id '"+ id_disciplinaCurso +"' encontrado com sucesso\"", disciplinaCursos));
    }

    public ResponseEntity<ApiResponse> findByIdDisciplina(Integer id_disciplina) {
        verificarIdDisciplina(id_disciplina);

        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findByDisciplinaId(id_disciplina);
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponse("Conexão entre Disciplina e Curso com o id '"+ id_disciplina +"' encontrado com sucesso", disciplinaCursos));
    }

    public ResponseEntity<ApiResponse> findByIdCurso(Integer id_curso) {
        verificarIdCurso(id_curso);

        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findByCursoId(id_curso);
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponse("Conexão entre Disciplina e Curso com o id '"+ id_curso +"' encontrado com sucesso", disciplinaCursos));
    }

    @Transactional
    public ResponseEntity<ApiResponse> update(Integer id_disciplinaCurso,DisciplinaCursoDto DisciplinaCursoDto){
        verificarIdDisciplinaCurso(id_disciplinaCurso);
        verificarIdDisciplina(DisciplinaCursoDto.id_disciplina());
        verificarIdCurso(DisciplinaCursoDto.id_curso());

        DisciplinaCurso disciplinaCurso = new DisciplinaCurso();
        disciplinaCurso.setId(id_disciplinaCurso);
        disciplinaCurso.setDisciplina(disciplinaRepository.findById(DisciplinaCursoDto.id_disciplina()).get());
        disciplinaCurso.setCurso(cursoRepository.findById(DisciplinaCursoDto.id_curso()).get());

        disciplinaCursoRepository.save(disciplinaCurso);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Conexão entre Disciplina e Curso com o id '"+ id_disciplinaCurso +"' atualizada com sucesso", disciplinaCurso));
    }

    public ResponseEntity<ApiResponse> deleteAll(){

        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findAll();
        disciplinaCursoRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Todas as conexões entre Disciplina e Curso foram deletadas com sucesso", disciplinaCursos));
    }

    public ResponseEntity<ApiResponse> deleteById(Integer id_disciplinaCurso){
        verificarIdDisciplinaCurso(id_disciplinaCurso);

        DisciplinaCurso disciplinaCursos = disciplinaCursoRepository.findById(id_disciplinaCurso).get();
        disciplinaCursoRepository.deleteById(id_disciplinaCurso);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Conexão entre Disciplina e Curso com o id '"+ id_disciplinaCurso +"' foi deletado com sucesso\"", disciplinaCursos));
    }

    @Transactional
    public ResponseEntity<ApiResponse> deleteByCursoId(Integer id_curso) {
        verificarIdCurso(id_curso);
        
        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findByCursoId(id_curso);
        disciplinaCursoRepository.deleteByCursoId(id_curso);
        
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponse("Todas as conexões do curso com id '" + id_curso + "' foram deletadas com sucesso", disciplinaCursos));
    }

    @Transactional
    public ResponseEntity<ApiResponse> deleteByDisciplinaId(Integer id_disciplina) {
        verificarIdDisciplina(id_disciplina);
        
        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findByDisciplinaId(id_disciplina);
        disciplinaCursoRepository.deleteByDisciplinaId(id_disciplina);
        
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponse("Todas as conexões da disciplina com id '" + id_disciplina + "' foram deletadas com sucesso", disciplinaCursos));
    }    
}