package com.jpa_n_plus_one.test.repository_eager;

import com.jpa_n_plus_one.test.domain_eager.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
