package com.spring.basic.score.service;

import com.spring.basic.score.dto.request.RequestScore;
import com.spring.basic.score.dto.response.ResponseRenderList;
import com.spring.basic.score.entity.Score;
import com.spring.basic.score.repository.ScoreRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;

    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    /**
     * 성적 등록
     */
    public ResponseRenderList createScore(@Valid RequestScore requestDto) {
        // 1. DTO를 Entity로 변환
        Score score = requestDto.toEntity();  // static 제거

        // 2. Repository에 저장
        Score savedScore = scoreRepository.save(score);

        // 3. Entity를 Response DTO로 변환하여 반환
        return ResponseRenderList.from(savedScore);
    }

    /**
     * 전체 성적 조회 (랭크 포함)
     */
    public List<ResponseRenderList> getAllScoresWithRank() {
        List<Score> rankedScores = scoreRepository.findAllWithRank();
        return ResponseRenderList.fromList(rankedScores);
    }
}
