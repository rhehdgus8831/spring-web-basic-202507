package com.spring.basic.chap2_3.Practice;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/practice/api/v1")
public class PracticeController2_3 {

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome to spring MVC!";
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id") String ProductId) {
        return "product Id = " + ProductId;
    }

    @GetMapping("/books")
    public String getBook(
            @RequestParam("author") String author,
            @RequestParam("genre") String genre
    ){
        return "author = " + author + ", genre = " + genre;
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("query") String query,
            @RequestParam(value = "value", defaultValue = "1") int page)
    { return "Query: " + query + "page =" + page;
    }


        @GetMapping("/info/{userId}")
        public String getInfo(@PathVariable("userId") String userId) {
            return "User Info: " + userId;
        }

}


