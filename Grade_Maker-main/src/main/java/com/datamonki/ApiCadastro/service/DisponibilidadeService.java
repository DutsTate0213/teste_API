package com.datamonki.ApiCadastro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.ApiCadastro.dto.DisponibilidadeDto;
import com.datamonki.ApiCadastro.exceptions.IdNotFoundException;
import com.datamonki.ApiCadastro.exceptions.ValidationException;
import com.datamonki.ApiCadastro.model.Disponibilidade;
import com.datamonki.ApiCadastro.repository.DiaSemanaRepository;
import com.datamonki.ApiCadastro.repository.DisciplinaRepository;
import com.datamonki.ApiCadastro.repository.DisponibilidadeRepository;
import com.datamonki.ApiCadastro.repository.ProfessorRepository;
import com.datamonki.ApiCadastro.repository.TurnoRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class DisponibilidadeService {
	
	@Autowired
	private DisponibilidadeRepository disponibilidadeRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	@Autowired
	private TurnoRepository turnoRepository;

	@Autowired
	private DiaSemanaRepository diaSemanaRepository;
	
	private void verificarProfessorId(Integer id) {
		if(!professorRepository.existsById(id)) {
			throw new IdNotFoundException("Não há professor registrado com o id: "+ id + " verifique e tente novamente");
		}
	}
	
	//Verificar se os atributos passados estão corretos e se os ids de fato existem no banco
	private void verificar(DisponibilidadeDto disponibilidadeDto) {
		List<String> messages = new ArrayList<String>();

		if(!professorRepository.existsById(disponibilidadeDto.idProfessor())){
			messages.add("Não há professor registrado com o id:"+ disponibilidadeDto.idProfessor() + " verifique e tente novamente");
		}
		else if(!disciplinaRepository.existsById(disponibilidadeDto.idDisciplina())) {
			messages.add("Não há disciplina registrada com o id:"+ disponibilidadeDto.idDisciplina() + " verifique e tente novamente");
		}
		else if(!turnoRepository.existsById(disponibilidadeDto.idTurno())) {
			messages.add("Não há turno registrado com o id:"+ disponibilidadeDto.idTurno() + " verifique e tente novamente");
		}
		else if(!diaSemanaRepository.existsById(disponibilidadeDto.idDiaSemana())) {
			messages.add("Não há dia da semana registrado com o id:"+ disponibilidadeDto.idDiaSemana() + " verifique e tente novamente");
		}
		else if(disponibilidadeRepository.verifyRepeticao(disponibilidadeDto.idProfessor(), disponibilidadeDto.idDiaSemana(), 
				disponibilidadeDto.idTurno(), disponibilidadeDto.semestre(), disponibilidadeDto.ano(), disponibilidadeDto.idDisciplina())) {
			messages.add("Disponibilidade já registrada");
		}
		else if(disponibilidadeDto.semestre() < 1 || disponibilidadeDto.semestre() > 2) {
			messages.add("Só são permitidos primeiro e segundo semestre.");
		}
		
		if(!messages.isEmpty()) {
			throw new ValidationException(messages);
		}
	}


	@Transactional
	public ResponseEntity<ApiResponse> save(DisponibilidadeDto disponibilidadeDto){
		verificar(disponibilidadeDto);
		
		Disponibilidade disponibilidade = new Disponibilidade();
		disponibilidade.setProfessor(professorRepository.findById(disponibilidadeDto.idProfessor()).get());
		disponibilidade.setDiaSemana(diaSemanaRepository.findById(disponibilidadeDto.idDiaSemana()).get());
		disponibilidade.setTurno(turnoRepository.findById(disponibilidadeDto.idTurno()).get());
		disponibilidade.setSemestre(disponibilidadeDto.semestre());
		disponibilidade.setAno(disponibilidadeDto.ano());
		disponibilidade.setDisciplina(disciplinaRepository.findById(disponibilidadeDto.idDisciplina()).get());
		disponibilidadeRepository.save(disponibilidade);
	
		return ResponseEntity.ok(new ApiResponse("Disponibilidade cadastrada com sucesso", disponibilidade));
	}

	@Transactional
	public ResponseEntity<ApiResponse> saveAll(List<DisponibilidadeDto> disponibilidadesDto){
		List<Disponibilidade> disponibilidades = new ArrayList<Disponibilidade>();
		
		for (DisponibilidadeDto dto: disponibilidadesDto) {
			verificar(dto);
			Disponibilidade disponibilidade = new Disponibilidade();
			disponibilidade.setProfessor(professorRepository.findById(dto.idProfessor()).get());
			disponibilidade.setDiaSemana(diaSemanaRepository.findById(dto.idDiaSemana()).get());
			disponibilidade.setTurno(turnoRepository.findById(dto.idTurno()).get());
			disponibilidade.setSemestre(dto.semestre());
			disponibilidade.setAno(dto.ano());
			disponibilidade.setDisciplina(disciplinaRepository.findById(dto.idDisciplina()).get());
			disponibilidades.add(disponibilidade);
		}
		disponibilidadeRepository.saveAll(disponibilidades);
		
		return ResponseEntity.ok(new ApiResponse("Disponibilidade cadastrada com sucesso", disponibilidades));
	}
	
	
	public ResponseEntity<ApiResponse> getAll(){
		List<Disponibilidade> disponibilidades = disponibilidadeRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Sucesso! Todas as disponibilidades retornadas.", disponibilidades));
	}
	
	public ResponseEntity<ApiResponse> getByIdProfessor(Integer idProfessor){
		List<Disponibilidade> disponibilidades = disponibilidadeRepository.findByIdProfessor(idProfessor);
		return ResponseEntity.ok(new ApiResponse("Sucesso! Todas as disponibilidades retornadas.", disponibilidades));
	}
	
	public ResponseEntity<ApiResponse> deleteByIdProfessor(Integer idProfessor){
		verificarProfessorId(idProfessor);
		if (disponibilidadeRepository.verifyDisponibilidadeProfessor(idProfessor)){
			disponibilidadeRepository.deleteByIdProfessor(idProfessor);

			return ResponseEntity.ok(new ApiResponse("Disponibilidades deletadas com sucesso", null));
		}

		return ResponseEntity.ok(new ApiResponse("Sem disponibilidades cadastradas", null));
	}
	
	public ResponseEntity<ApiResponse> deleteByIdProfessorAnoSemestre(Integer idProfessor, Integer semestre, Integer ano){
		verificarProfessorId(idProfessor);
		
		if (disponibilidadeRepository.verifyDisponibilidadeProfessor(idProfessor)){
			disponibilidadeRepository.deleteByIdProfessorAnoSem(idProfessor, ano, semestre);

			return ResponseEntity.ok(new ApiResponse("Disponibilidades deletadas com sucesso", null));
		}

		return ResponseEntity.ok(new ApiResponse("Sem disponibilidades cadastradas", null));
	}
	
	
	
}