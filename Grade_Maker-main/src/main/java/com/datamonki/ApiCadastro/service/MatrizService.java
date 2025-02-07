package com.datamonki.ApiCadastro.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.ApiCadastro.dto.MatrizDto;
import com.datamonki.ApiCadastro.exceptions.IdNotFoundException;
import com.datamonki.ApiCadastro.model.Matriz;
import com.datamonki.ApiCadastro.repository.TurmaRepository;
import com.datamonki.ApiCadastro.repository.MatrizRepository;
import com.datamonki.ApiCadastro.repository.DisciplinaRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class MatrizService {

    @Autowired
    private MatrizRepository matrizRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    private void verificarIdMatriz(Integer id) {
        if (!matrizRepository.existsById(id)) {
            throw new IdNotFoundException("Não foi possivel encontrar matriz com o Id '" + id + "', verifique e tente novamente"); 
        }
    }

    private void verificarIdDisciplina(Integer id) {
        if (!disciplinaRepository.existsById(id)) {
            throw new IdNotFoundException("Não foi possivel encontrar disciplina com o Id '" + id + "', verifique e tente novamente"); 
        }
    }

    private void verificarIdTurma(Integer id) {
        if (!turmaRepository.existsById(id)) {
            throw new IdNotFoundException("Não foi possivel encontrar turma com o Id '" + id + "', verifique e tente novamente"); 
        }
    }

    
    @Transactional
    public ResponseEntity<ApiResponse> create(MatrizDto matrizDto) {
        verificarIdDisciplina(matrizDto.id_disciplina());
        verificarIdTurma(matrizDto.id_turma());

        Matriz matriz = new Matriz();
        matriz.setDisciplina(disciplinaRepository.findById(matrizDto.id_disciplina()).get());
        matriz.setTurma(turmaRepository.findById(matrizDto.id_turma()).get());

        matrizRepository.save(matriz);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Conexão entre Disciplina e Turma feita com sucesso", matriz));
    }

    public ResponseEntity<ApiResponse> getAll() {
        List<Matriz> matrizes = matrizRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Lista de conexões entre Disciplina e Turma", matrizes));
    }

    public ResponseEntity<ApiResponse> getById(Integer id_matriz) {
        verificarIdMatriz(id_matriz);
        Matriz matriz = matrizRepository.findById(id_matriz).get();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Conexão entre Disciplina e Turma com o id '"+ id_matriz +"' encontrada com sucesso", matriz));
    }

    public ResponseEntity<ApiResponse> getByIdTurma(Integer id_turma) {
        verificarIdTurma(id_turma);
        List<Matriz> matrizes = matrizRepository.findByTurmaId(id_turma);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Lista de conexões entre Disciplina e Turma com o id '"+ id_turma +"'", matrizes));
    }

    public ResponseEntity<ApiResponse> getByIdDisciplina(Integer id_disciplina) {
        verificarIdDisciplina(id_disciplina);
        List<Matriz> matrizes = matrizRepository.findByDisciplinaId(id_disciplina);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Lista de conexões entre Disciplina e Turma com o id '"+ id_disciplina +"'", matrizes));
    }
    
    @Transactional
    public ResponseEntity<ApiResponse> update(Integer id_matriz, MatrizDto matrizDto) {
        verificarIdMatriz(id_matriz);
        verificarIdDisciplina(matrizDto.id_disciplina());
        verificarIdTurma(matrizDto.id_turma());

        Matriz matriz = new Matriz();
        matriz.setId(id_matriz);
        matriz.setDisciplina(disciplinaRepository.findById(matrizDto.id_disciplina()).get());
        matriz.setTurma(turmaRepository.findById(matrizDto.id_turma()).get());

        matrizRepository.save(matriz);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Conexão entre Disciplina e Turma com o id '"+ id_matriz +"' atualizada com sucesso", matriz));
    }

    public ResponseEntity<ApiResponse> deleteAll() {
        List<Matriz> matrizes = matrizRepository.findAll();
        matrizRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Todas as conexões entre Disciplina e Turma foram deletadas com sucesso", matrizes));
    }

    public ResponseEntity<ApiResponse> deleteById(Integer id_matriz) {
        verificarIdMatriz(id_matriz);
        Matriz matriz = matrizRepository.findById(id_matriz).get();
        matrizRepository.deleteById(id_matriz);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Conexão entre Disciplina e Turma com o id '"+ id_matriz +"' foi deletada com sucesso", matriz));
    }

    public ResponseEntity<ApiResponse> deleteByIdTurma(Integer id_turma) {
        verificarIdTurma(id_turma);
        List<Matriz> matrizes = matrizRepository.findByTurmaId(id_turma);
        matrizRepository.deleteByTurmaId(id_turma);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Todas as conexões entre Disciplina e Turma com o id '"+ id_turma +"' foram deletadas com sucesso", matrizes));
    }

    public ResponseEntity<ApiResponse> deleteByIdDisciplina(Integer id_disciplina) {
        verificarIdDisciplina(id_disciplina);
        List<Matriz> matrizes = matrizRepository.findByDisciplinaId(id_disciplina);
        matrizRepository.deleteByDisciplinaId(id_disciplina);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Todas as conexões entre Disciplina e Turma com o id '"+ id_disciplina +"' foram deletadas com sucesso", matrizes));
    }   
}
