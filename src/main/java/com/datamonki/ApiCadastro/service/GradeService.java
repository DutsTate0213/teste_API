package com.datamonki.ApiCadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.ApiCadastro.dto.GradeDto;
import com.datamonki.ApiCadastro.exceptions.IdNotFoundException;
import com.datamonki.ApiCadastro.model.Grade;
import com.datamonki.ApiCadastro.repository.GradeRepository;
import com.datamonki.ApiCadastro.repository.TurmaRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    private void verificarIdGrade(Integer id) {
        if (!gradeRepository.existsById(id)) {
            throw new IdNotFoundException("Não foi possivel encontrar grade com o Id '" + id + "', verifique e tente novamente");
        }
    }

    private void verificarIdTurma(Integer id) {
        if (!turmaRepository.existsById(id)) {
            throw new IdNotFoundException("Não foi possivel encontrar turma com o Id '" + id + "', verifique e tente novamente");
        }
    }


    @Transactional
    public ResponseEntity<ApiResponse> create(GradeDto gradeDto) {
        verificarIdTurma(gradeDto.id_turma());

        Grade grade = new Grade();
        grade.setDom(gradeDto.dom());
        grade.setSeg(gradeDto.seg());
        grade.setTer(gradeDto.ter());
        grade.setQua(gradeDto.qua());
        grade.setQui(gradeDto.qui());
        grade.setSex(gradeDto.sex());
        grade.setSab(gradeDto.sab());
        grade.setVersao(gradeDto.versao());
        grade.setStatus(gradeDto.status());
        grade.setTurma(turmaRepository.findById(gradeDto.id_turma()).get());

        gradeRepository.save(grade);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Grade cadastrada com sucesso", grade));
    }

    public ResponseEntity<ApiResponse> getAll() {
        List<Grade> grades = gradeRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Lista de grades cadastradas", grades));
    }

    public ResponseEntity<ApiResponse> getById(Integer id) {
        verificarIdGrade(id);
        Grade grade = gradeRepository.findById(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Grade com o id '" + id + "' encontrada com sucesso", grade));
    }

    @Transactional
    public ResponseEntity<ApiResponse> update(Integer id, GradeDto gradeDto) {
        verificarIdGrade(id);
        verificarIdTurma(gradeDto.id_turma());

        Grade grade = new Grade();
        grade.setId(id);
        grade.setDom(gradeDto.dom());
        grade.setSeg(gradeDto.seg());
        grade.setTer(gradeDto.ter());
        grade.setQua(gradeDto.qua());
        grade.setQui(gradeDto.qui());
        grade.setSex(gradeDto.sex());
        grade.setSab(gradeDto.sab());
        grade.setVersao(gradeDto.versao());
        grade.setStatus(gradeDto.status());
        grade.setTurma(turmaRepository.findById(gradeDto.id_turma()).get());

        gradeRepository.save(grade);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Grade com o id '" + id + "' atualizada com sucesso", grade));
    }

    public ResponseEntity<ApiResponse> deleteById(Integer id) {
        verificarIdGrade(id);
        Grade grade = gradeRepository.findById(id).get();
        gradeRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Grade com o id '" + id + "' deletada com sucesso", grade));
    }

    public ResponseEntity<ApiResponse> deleteAll() {
        List<Grade> grades = gradeRepository.findAll();
        gradeRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Todas as Grades foram deletadas com sucesso", grades));
    }
}