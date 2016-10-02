package com.github.algorithm.application;

import com.github.model.Application;
import org.springframework.stereotype.Component;

/**
 * Copyright & Author
 * michal
 */
@Component("applicationService")
public class DefaultApplicationService implements ApplicationService {

    @Override
    public void getStrategyList(Application application) {
        if (application.getDisease() != null) {
            switch (application.getDisease()) {
                case GRYPA:
                    application.setFirstStrategy("Leżenie w łóżku");
                    application.setSecondStrategy("Witamina C i rutyna");
                    application.setThirdStrategy("Amantadyna");
                    break;
                case OSPA:
                    application.setFirstStrategy("Leżenie w łóżku");
                    application.setSecondStrategy("Salicylany");
                    application.setThirdStrategy("Pyramidon");
                    break;
                case ROZYCZKA:
                    application.setFirstStrategy("Maści naskórne");
                    application.setSecondStrategy("Spożywać dużo wody");
                    application.setThirdStrategy("Tableki na gorączkę i środki nasercowe");
            }
        }
    }
}
