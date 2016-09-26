package com.github.algorithm.lemke_howson;

import com.github.model.Application;
import com.github.model.Tuple;

/**
 * Copyright & Author
 * michal
 */
public interface MatrixService {

    Double[][] getDefaultMatrix(Application application);

    Double[][] mergeMatrix(Double[][] firstMatrix, Double[][] secondMatrix);

    void calculateMatrix(Double min, Double[][] matrix, Integer line, Integer column);

    Tuple<Integer, Integer> getIndexes(Integer line, Integer column, Double[][] matrix);
}
