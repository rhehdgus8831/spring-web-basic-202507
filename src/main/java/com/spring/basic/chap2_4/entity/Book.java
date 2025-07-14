package com.spring.basic.chap2_4.entity;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    // long타입이 아닌 Long을 쓰는 이유 : null 과 0의 차이 null은 가격자체가 설정 안된 것 , 0은 그냥 0원임
    private Long id; // 책을 유일하게 구분할 수 있는 식별자
    // @Setter 타이틀만 setter 쓸 수 있음
    private String title;
    private String author;
    private int Price;

}
