package com.spring.basic.score.routes;

import com.spring.basic.score.dto.request.RequestScore;
import com.spring.basic.score.dto.response.ResponseRenderList;
import com.spring.basic.score.service.ScoreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api/v1/scores")
public class ScorePageController {

    private final ScoreService scoreService;

    public ScorePageController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    // JSP 페이지 렌더링용
    @GetMapping("/score-page")
    public String showScorePage(Model model) {
        List<ResponseRenderList> scores = scoreService.getAllScoresWithRank();
        model.addAttribute("scoreList", scores);
        return "score/score-page";
    }

    // API 조회용
    @GetMapping
    @ResponseBody
    public List<ResponseRenderList> getScores() {
        return scoreService.getAllScoresWithRank();
    }

    // 성적 등록 API
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createScore(@RequestBody @Valid RequestScore requestDto,
                                         BindingResult bindingResult) {

        // 유효성 검사 실패 시
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }

        // 성공 시
        try {
            ResponseRenderList response = scoreService.createScore(requestDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("성적 등록 중 오류가 발생했습니다.");
        }
    }
}
