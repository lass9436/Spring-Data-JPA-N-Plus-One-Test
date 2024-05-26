package com.jpa_n_plus_one.test.repository_lazy;

import com.jpa_n_plus_one.test.domain_lazy.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
