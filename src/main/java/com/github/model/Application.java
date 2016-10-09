package com.github.model;

import com.github.algorithm.application.DiseaseEnum;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Copyright & Author
 * michal
 */
@Entity
public class Application implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Integer numberOfPlayers;                // liczba graczy

    @Enumerated(EnumType.STRING)
    private DiseaseEnum disease;                    // rodzaj choroby

    private String[][] firstMatrix;                 // macierz dla pierwszego gracza
    private String[][] secondMatrix;                // macierz dla drugiego gracza
    private String[][] thirdMatrix;                 // macierz dla trzeciego gracza

    private String firstStrategy;                   // nazwa pierwszej strategii
    private String secondStrategy;                  // nazwa drugiej strategii
    private String thirdStrategy;                   // nazwa trzeiej strategii

    @Column(name = "lines_number")
    private Integer lines;                          // ilość wierszy

    @Column(name = "columns_number")
    private Integer columns;                        // ilość kolumn

    @OneToOne
    private Equilibrium equilibrium;                // równowaga

    private String resultLabel;
    private String result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public DiseaseEnum getDisease() {
        return disease;
    }

    public void setDisease(DiseaseEnum disease) {
        this.disease = disease;
    }

    public Integer getLines() {
        return lines;
    }

    public void setLines(Integer lines) {
        this.lines = lines;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public Equilibrium getEquilibrium() {
        return equilibrium;
    }

    public void setEquilibrium(Equilibrium equilibrium) {
        this.equilibrium = equilibrium;
    }

    public String getFirstStrategy() {
        return firstStrategy;
    }

    public void setFirstStrategy(String firstStrategy) {
        this.firstStrategy = firstStrategy;
    }

    public String getSecondStrategy() {
        return secondStrategy;
    }

    public void setSecondStrategy(String secondStrategy) {
        this.secondStrategy = secondStrategy;
    }

    public String getThirdStrategy() {
        return thirdStrategy;
    }

    public void setThirdStrategy(String thirdStrategy) {
        this.thirdStrategy = thirdStrategy;
    }

    public String[][] getFirstMatrix() {
        return firstMatrix;
    }

    public void setFirstMatrix(String[][] firstMatrix) {
        this.firstMatrix = firstMatrix;
    }

    public String[][] getSecondMatrix() {
        return secondMatrix;
    }

    public void setSecondMatrix(String[][] secondMatrix) {
        this.secondMatrix = secondMatrix;
    }

    public String[][] getThirdMatrix() {
        return thirdMatrix;
    }

    public void setThirdMatrix(String[][] thirdMatrix) {
        this.thirdMatrix = thirdMatrix;
    }

    public String getResultLabel() {
        return resultLabel;
    }

    public void setResultLabel(String resultLabel) {
        this.resultLabel = resultLabel;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
