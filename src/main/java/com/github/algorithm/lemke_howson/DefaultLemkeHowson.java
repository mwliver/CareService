package com.github.algorithm.lemke_howson;

import com.github.model.Equilibrium;
import com.github.model.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Copyright & Author
 * michal
 */
@Component("lemkeHowson")
public class DefaultLemkeHowson implements LemkeHowson {

    @Autowired
    MatrixService matrixService;

    @Autowired
    EquilibriumService equilibriumService;

    @Override
    public Equilibrium lemkeHowson(Double[][] firstMatrix, Double[][] secondMatrix) {

        Double[][] matrix = matrixService.mergeMatrix(firstMatrix, secondMatrix);

        Integer firstColumn = 0;
        Integer column = firstColumn;

        while (true) {

            Integer nextColumn = column + 1;

            Double min = null;
            Integer line = null;

            for (int i = 0; i < 4; i++) {
                min = matrix[i][column];
                line = i;

                if (matrix[i][column] < 0 && matrix[i][nextColumn]/-(matrix[i][column]) < min) {
                    min = matrix[i][column];
                    line = i;
                }
            }

            Tuple<Integer, Integer> tuple = matrixService.getIndexes(line, column, matrix);

            matrixService.calculateMatrix(min, matrix, line, column);

            Integer columnToChange = tuple.getRight();

            if (firstColumn.equals(columnToChange)) {
                return equilibriumService.createEquilibrium(matrix);
            } else {
                column = columnToChange;
            }
        }
    }
}
