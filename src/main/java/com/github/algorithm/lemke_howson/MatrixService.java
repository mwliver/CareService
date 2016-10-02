package com.github.algorithm.lemke_howson;

import com.github.model.Application;
import com.github.model.Tuple;

/**
 * Copyright & Author
 * michal
 */
public interface MatrixService {

    String[][] getDefaultMatrix(Application application);

    String[][] generateMatrix(String[][] firstMatrix, String[][] secondMatrix);

    String[][] mergeMatrix(Application application, String[][] firstMatrix, String[][] secondMatrix);

    void calculateMatrix(Application application, String min, String[][] matrix, Integer line, Integer column);

    Tuple<Integer, Integer> getIndexes(Application application, Integer line, Integer column, String[][] matrix);
}
