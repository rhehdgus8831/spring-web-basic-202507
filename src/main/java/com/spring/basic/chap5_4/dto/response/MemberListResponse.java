package com.spring.basic.chap5_4.dto.response;

import lombok.*;

// 클라이언트에게 멤버 목록을 보내줄 대 사용할 응답 DTO
@EqualsAndHashCode
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MemberListResponse {
    private String id;
    private String email; // account에 대응
    private String nick; // 가운데 글자 마스킹 (첫 글자랑 마지막 글자 빼고)
}
