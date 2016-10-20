package com.github.algorithm.lemke_howson;

import com.github.model.Application;
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
    public Equilibrium lemkeHowson(Application application) {

        String[][] matrix = matrixService.mergeMatrix(application, application.getFirstMatrix(), application.getSecondMatrix());

        Integer firstColumn = 0;
        Integer column = firstColumn;

        while (true) {

            String min = null;
            Integer line = null;

            for (int i = 1; i < application.getLines(); i++) {
                if (i == 1) {
                    min = matrix[i][column];
                    line = i;
                }

                if (MatrixUtil.valueOf(matrix[i][column]) < 0 && MatrixUtil.valueOf(min) > MatrixUtil.valueOf(matrix[i][column])) {
                    min = matrix[i][column];
                    line = i;
                }
            }

            Tuple<Integer, Integer> tuple = matrixService.getIndexes(application, line, column, matrix);

            matrixService.calculateMatrix(application, min, matrix, line, column);

            Integer columnToChange = tuple.getLeft();

            if (firstColumn.equals(columnToChange)) {
                return equilibriumService.createEquilibrium(matrix, application);
            } else {
                column = columnToChange;
            }
        }
    }
}
