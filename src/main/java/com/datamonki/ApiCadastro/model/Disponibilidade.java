package com.datamonki.ApiCadastro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "disponibilidade")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Disponibilidade {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    @Min(1)
    @Max(2)
    private Integer semestre;
    
    @Column
    private Integer ano;

    @ManyToOne
    @JoinColumn(name = "id_dia_semana")
    private DiaSemana diaSemana;

    @ManyToOne
    @JoinColumn(name="id_turno")
    private Turno turno;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;

}