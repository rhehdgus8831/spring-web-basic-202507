package com.spring.basic.chap4_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chap4-1")
public class PetController4_1 {

    // pet.jsp를 열여줘 (뷰 포워딩) - 페이지 라우팅
    @GetMapping("/pet-page")
    public String petPage() {
        return "";
    }
}
