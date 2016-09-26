package com.github.algorithm.application;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright & Author
 * michal
 */
@Component("applicationService")
public class DefaultApplicationService implements ApplicationService {

    @Override
    public List<DiseaseEnum> getDiseaseList() {
        return Arrays.asList(DiseaseEnum.values());
    }
}
