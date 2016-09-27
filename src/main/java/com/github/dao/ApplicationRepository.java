package com.github.dao;

import com.github.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Copyright & Author
 * michal
 */
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
