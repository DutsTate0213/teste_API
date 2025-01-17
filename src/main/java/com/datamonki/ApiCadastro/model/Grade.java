package com.datamonki.ApiCadastro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "grade")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer dom;

    @Column
    private Integer seg;

    @Column
    private Integer ter;

    @Column
    private Integer qua;

    @Column
    private Integer qui;

    @Column
    private Integer sex;

    @Column
    private Integer sab;

    @Column
    private Integer versao;

    @Column
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "id_turma", nullable = false)
    private Turma turma;

}