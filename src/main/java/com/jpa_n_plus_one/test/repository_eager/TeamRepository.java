package com.jpa_n_plus_one.test.repository_eager;

import com.jpa_n_plus_one.test.domain_eager.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
