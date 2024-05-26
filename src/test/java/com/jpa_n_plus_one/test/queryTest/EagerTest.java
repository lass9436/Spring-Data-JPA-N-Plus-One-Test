package com.jpa_n_plus_one.test.queryTest;

import com.jpa_n_plus_one.test.domain_eager.Member;
import com.jpa_n_plus_one.test.domain_eager.Team;
import com.jpa_n_plus_one.test.repository_eager.MemberRepository;
import com.jpa_n_plus_one.test.repository_eager.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class EagerTest {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setUp(){
        Team team1 = new Team("team1");
        Team team2 = new Team("team2");
        Team team3 = new Team("team3");

        Member member11 = new Member("member1", team1);
        Member member12 = new Member("member2", team1);
        Member member13 = new Member("member3", team1);

        Member member21 = new Member("member1", team2);
        Member member22 = new Member("member2", team2);
        Member member23 = new Member("member3", team2);

        Member member31 = new Member("member1", team3);
        Member member32 = new Member("member2", team3);
        Member member33 = new Member("member3", team3);

        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);

        memberRepository.save(member11);
        memberRepository.save(member12);
        memberRepository.save(member13);

        memberRepository.save(member21);
        memberRepository.save(member22);
        memberRepository.save(member23);

        memberRepository.save(member31);
        memberRepository.save(member32);
        memberRepository.save(member33);
    }

    @Test
    @DisplayName("FetchType.EAGER 일 때, N+1 쿼리 테스트")
    void fetchTypeEagerNPlusOne(){
        log.info("레포지토리에서 팀 조회 (EAGER)");
        List<Team> teams = teamRepository.findAll();
        assertThat(teams).hasSize(3);
    }
}
