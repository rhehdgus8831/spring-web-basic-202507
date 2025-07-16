package com.spring.basic.score.entity;

import lombok.*;

@Setter @Getter @ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor @Builder
public class Score {
    private Long id;
    private String name;
    private int kor, eng, math;
    private int total;
    private double average;
    private int rank;  // 추가

    // 빌더 커스터마이징
    public static class ScoreBuilder {
        public Score build() {
            if (total == 0) {
                total = kor + eng + math;
            }
            if (average == 0.0) {
                average = Math.round((total / 3.0) * 100) / 100.0;
            }
            return new Score(id, name, kor, eng, math, total, average, 0);
        }
    }
}
