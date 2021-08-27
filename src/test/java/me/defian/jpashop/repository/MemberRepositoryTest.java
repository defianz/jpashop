package me.defian.jpashop.repository;

import me.defian.jpashop.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {


    @Autowired
    MemberRepository memberRepository;

    @Test
    public void hello(){
        memberRepository.findAll();
    }

}