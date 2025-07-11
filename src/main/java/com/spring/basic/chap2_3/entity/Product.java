package com.spring.basic.chap2_3.entity;


import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 전체생성자

//@Data 전부 다 생성하지만 실무에서는 시용 X
public class Product {

    private Long serialNo; // 상품 시리얼 번호
    private String name; // 상품명
    private int price; // 상품가격

}
