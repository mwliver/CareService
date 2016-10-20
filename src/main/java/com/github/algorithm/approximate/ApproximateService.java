package com.github.algorithm.approximate;

import com.github.model.Application;

/**
 * Copyright & Author
 * michal
 */
public interface ApproximateService {

    /**
     * Konwertuj dane do macierzy
     */
    String[][] convertToMatrix(String[][] firstMatrix, String[][] secondMatrix, String[][] thirdMatrix);

    /**
     * Strategie mieszane
     */
    String[] calculateMixed(String[][] values, String[] probability);

    /**
     * Strategie czyste
     */
    String[][] calculatePure(String[][] values, String[] probability);

    /**
     * Losuje częstotliwość
     */
    String[] randomValues();

    /**
     * Wczytuje epsilon
     */
    String approximate(Application application);
}
