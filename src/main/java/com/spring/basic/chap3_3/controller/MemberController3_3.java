package com.spring.basic.chap3_3.controller;

import com.spring.basic.chap3_2.entity.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v3-3/members")
public class MemberController3_3 {

    private Map<String, Member> memberStore = new HashMap<>();

    public MemberController3_3() {

        Member member1 = Member.builder()
                .account("abc123")
                .password("9999")
                .nickname("뽀롱이")
                .build();

        Member member2 = Member.builder()
                .account("def9876")
                .password("4444")
                .nickname("핑순이")
                .build();

        memberStore.put(member1.getUid(), member1);
        memberStore.put(member2.getUid(), member2);

    }

    // 회원 생성
    @PostMapping
    public ResponseEntity<String> join(@RequestBody Member member) {

        // 계정이 비어있는지 확인
        if(member.getAccount().isBlank()){
            return ResponseEntity
                    .badRequest()
                    .body("계정은 필수 값입니다.");
        }

        member.setUid(java.util.UUID.randomUUID().toString());
        memberStore.put(member.getUid(), member);
        return ResponseEntity
                .ok()
                .body("새로운 멤버가 생성됨 ! - nickname" + member.getNickname());
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<Member>> list(){
        return ResponseEntity
                .ok()
                .body(new ArrayList<>(memberStore.values()));

    }


/*

    // 단일 조회
    @GetMapping("/account")
    public ResponseEntity<String> getMember(@RequestParam("account") String account){
        Member member = memberStore.get(account);
        if(@RequestParam("account") != account){
        }
*/

}
