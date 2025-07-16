package com.spring.basic.score.dto.response;

import com.spring.basic.score.entity.Score;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseRenderList {

    private Long id;           // 학생 ID
    private String name;       // 학생 이름
    private int kor, eng, math; // 국영수 점수
    private int sum;           // 총점
    private double avg;        // 평균
    private int rank;          // 순위
    private String maskingName; // 마스킹된 이름

    /**
     * Score에서 ResponseRenderList로 변환
     */
    public static ResponseRenderList from(Score score) {
        int total = score.getKor() + score.getEng() + score.getMath();
        double average = total / 3.0;

        // 닉네임 마스킹 처리
        String originName = score.getName();
        String maskingName = "";
        if (originName.length() > 1) {
            maskingName = originName.charAt(0) + "*" + originName.charAt(originName.length() - 1);
        } else {
            maskingName = originName;
        }


        return ResponseRenderList.builder()
                .id(score.getId())
                .name(score.getName())
                .kor(score.getKor())
                .eng(score.getEng())
                .math(score.getMath())
                .sum(total)
                .avg(average)
                .rank(1)
                .maskingName(maskingName)
                .build();
    }

    /**
     * Score 리스트를 ResponseRenderList 리스트로 변환
     */
    public static List<ResponseRenderList> fromList(List<Score> scoreList) {
        return scoreList.stream()
                .map(ResponseRenderList::from)
                .collect(Collectors.toList());
    }
}
