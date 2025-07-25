package com.spring.basic.chap5_3.controller;

import com.spring.basic.chap3_2.entity.Member;
import com.spring.basic.chap5_3.dto.request.MemberCreateDto;
import com.spring.basic.chap5_4.dto.response.MemberListResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/v5-3/members")
@Slf4j // 로그 라이브러리
public class MemberController5_3 {

    // key: account, value : member instance
    private Map<String, Member> memberStore = new HashMap<>();

    public MemberController5_3() {
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

    /*
        로그의 기본 요소 - 감사, 모니터링, 재해복구
        1. 로그 내용
        2. 로그 레벨 - 중요도
          - TRACE : 단순히 객체가생성되거나, 함수가 호출거나 하는 로그를 찍을 때
          - DEBUG : 개발하면서 변수값을 추적하거나 디버깅 용도
          - INFO : 운영서버의 일반적인 내용을 작성 (서버가 실행, 특정 핵심 요청이 들어오거나)
          - WARN : 뭔가 이상이 생긴 경우, 다만 프로그램에 큰 위해는 아닌 경우
          - ERROR : 커다란 예외가 발생하거나 프로그램에 치명적인 오류가 난 경우
          - FATAL : 시스템 장애

        3. 로그가 찍힌 시간
        4. 로그가 찍힌 위치 (파일 경로)
     */

    // 전체 조회
    @GetMapping
    public ResponseEntity<?> memberList() {

        log.trace("memberList 메서드 호출됨");

        log.info("/api/v5-3/members : GET - 요청 시작!");

        List<MemberListResponse> responses = memberStore.values()
                .stream()
                .map(MemberListResponse::from)
                .collect(toList());

        log.debug("members.size = {}", responses.size());

        if (responses.size() <= 0) {
            log.warn("회원 데이터가 없습니다.");
            return ResponseEntity.notFound().build();
        }


        try {
            log.debug("members[0].nickname = {}", responses.get(0).getNick());
        } catch (Exception e) {
            log.error("서버 에러입니다.");
            return ResponseEntity.internalServerError().body("서버 에러입니다.");
        }

        log.trace("memberList 메서드 호출 종료됨");
        return ResponseEntity
                .ok()
                .body(responses);
    }

    // 회원 생성
    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody @Valid MemberCreateDto dto
            // 입력값 검증 오류 내용을 갖고있는 객체
            , BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) { // 검증결과 에러가 있다면
            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getFieldErrors().forEach(err -> {
                errorMap.put(err.getField(), err.getDefaultMessage());
            });

            log.warn("회원가입 입력값 오류가 발생함!");
            return ResponseEntity.badRequest().body(errorMap);
        }

        log.info("param - {}", dto);

        // 데이터 베이스 저장 : UID를 포함, 비밀번호를 인코딩
        // dto -> entity로 변환하는 과정
//        Member member = Member.builder()
//                .account(dto.getUserAcc())
//                .password(dto.getPw())
//                .nickname(dto.getNick())
//                .build();

//        Member member = new Member(dto);

        Member member = MemberCreateDto.from(dto);

        memberStore.put(dto.getUserAcc(), member);

        return ResponseEntity.ok("created member: " + member);
    }

}
