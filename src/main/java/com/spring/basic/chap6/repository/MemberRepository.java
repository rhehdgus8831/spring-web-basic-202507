package com.spring.basic.chap6.repository;

import com.spring.basic.chap3_2.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
// 데이터 베이스를 관리 CRUD
public class MemberRepository {

    // 데이터 베이스 정의
    private Map<String , Member> memberStore = new HashMap<>();

    public MemberRepository() {
        Member m1 = Member.builder()
                .account("abc1234")
                .password("12345678")
                .nickname("호돌이")
                .build();

        Member m2 = Member.builder()
                .account("def9876")
                .password("12345678")
                .nickname("핑돌이")
                .build();

        memberStore.put(m1.getAccount(), m1);
        memberStore.put(m2.getAccount(), m2);
    }

    // 개별 조회하는 기능
    public Member findByAccount(String account){
        log.info("서비스로부터 회원 개별 조회를 위임받음");
        return memberStore.get(account);
    }

    // 데이터를 저장하는 기능
    public Member save(Member member){
        memberStore.put(member.getAccount(), member);
        return findByAccount(member.getAccount());
    }

}
