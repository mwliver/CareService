package com.github.algorithm.lemke_howson;

import com.github.algorithm.application.DiseaseEnum;
import com.github.model.Application;
import com.github.model.Tuple;
import org.springframework.stereotype.Component;

/**
 * Copyright & Author
 * michal
 */
@Component("matrixService")
public class DefaultMatrixService implements MatrixService {

    /**
     * Zwraca macierz z domyślnymi wartościami dla podanej choroby
     */
    @Override
    public Double[][] getDefaultMatrix(Application application) {
        if (application.getDisease() != null) {
            return generateDefaultMatrix(application.getDisease());
        }
        return new Double[0][0];
    }

    private Double[][] generateDefaultMatrix(DiseaseEnum disease) {
        switch (disease) {
            case GRYPA:
                return new Double[][] {{5.00, 5.00, 10.00}, {1.0, 8., 3.00}};
            case OSPA:
                return new Double[][] {{2.00, 8.00, 2.00}, {3.00, 2.00, 7.00}};
            case ROZYCZKA:
                return new Double[][] {{0.00, 1.00, 2.83}, {0.11, 0.23, 0.11}};
        }
        return new Double[0][0];
    }

    /**
     * Łączy dwie macierz w jedną odwracając jedną z danych wejściowych
     */
    @Override
    public Double[][] mergeMatrix(Double[][] firstMatrix, Double[][] secondMatrix) {
        Double[][] matrix = new Double[5][5];       // 6 wierszy 7 kolumn

        // inicjalizuj zerami
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = 0.00;
            }
        }

        // wiersze
        for (int i = 0; i < 2; i++) {
            // kolumny
            for (int j = 0, t = 2; j < 3; j++, t++) {
                // przesuń wiersze w prawo w nowej macierzy
                matrix[i][t] = -(firstMatrix[i][j]);
            }
        }

        // wiersze
        for (int i = 0; i < 2; i++) {
            // kolumny
            for (int j = 0, t = 2; j < 3; j++, t++) {
                // odwóć i przesuń na dół
                matrix[t][i] = -(secondMatrix[i][j]);
            }
        }

        // todo: wypełnić z0 ... z4 i r0 ... r4 i 1 z prawej strony pomiędzy matrix i r

        return matrix;
    }
    /**
     * Przekształca dane w macierzy według algorytmu sympleks*/
    @Override
    public void calculateMatrix(Double min, Double[][] matrix, Integer line, Integer column) {

    }

    /**
     * Zwraca indexy tych danych które będą zamienione*/
    @Override
    public Tuple<Integer, Integer> getIndexes(Integer line, Integer column, Double[][] matrix) {
        return null;
    }
}
