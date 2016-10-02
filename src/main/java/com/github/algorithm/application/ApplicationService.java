package com.github.algorithm.application;

import com.github.model.Application;

/**
 * Copyright & Author
 * michal
 */
public interface ApplicationService {

    /**
     * Ustawia w application dostępne strategie w zależności od wybranej choroby
     */
    void getStrategyList(Application application);
}
