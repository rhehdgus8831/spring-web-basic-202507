package com.spring.basic.chap5_3.dto.request;

import lombok.*;

// 회원가입 전용 객체
@EqualsAndHashCode
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MemberCreateDto {

    private String userAcc;
    private String pw;
    private String nick;

}
