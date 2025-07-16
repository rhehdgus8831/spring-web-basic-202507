package com.spring.basic.score.repository;

import com.spring.basic.score.entity.Score;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository  // @Controller에서 변경
public class ScoreRepository {

    private final List<Score> scoreList = new ArrayList<>();
    private Long nextId = 1L;

    public ScoreRepository() {

    }

    public Score save(Score score) {
        score.setId(nextId++);
        scoreList.add(score);
        return score;
    }

    public List<Score> findAllWithRank() {
        List<Score> scores = new ArrayList<>(scoreList);

        // 평균 기준 내림차순 정렬
        scores.sort((a, b) -> Double.compare(
                (b.getKor() + b.getEng() + b.getMath()) / 3.0,
                (a.getKor() + a.getEng() + a.getMath()) / 3.0
        ));

        // 랭크 부여
        for (int i = 0; i < scores.size(); i++) {
            scores.get(i).setRank(i + 1);
        }

        return scores;
    }
}
