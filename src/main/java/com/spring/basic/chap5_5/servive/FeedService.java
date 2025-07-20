package com.spring.basic.chap5_5.servive;


import com.spring.basic.chap5_5.dto.request.FeedCreateRequest;
import com.spring.basic.chap5_5.dto.ressponse.FeedDetailResponse;
import com.spring.basic.chap5_5.dto.ressponse.FeedListResponse;
import com.spring.basic.chap5_5.entity.Feed;
import com.spring.basic.chap5_5.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// 데이터 변환 및 예외처리, 트랜젝션 담당
// controller -> service -> repository
@Service
@RequiredArgsConstructor // 생성자 자동화
public class FeedService {

    private final FeedRepository feedRepository;

    // 목록 요청에 대한 중간처리
    public List<FeedListResponse> listProcess(){
        // 저장소에게 접근해서 목록을 가져오라고 시킴 (의존객체에 위임)
        List<Feed> feeds = feedRepository.getFeeds();
        // 원본으로 주지말고 가릴거는 가리고 예쁘게 만들어서 컨트롤러에 전달
        List<FeedListResponse> responses = feeds.stream()
                .map(feed -> FeedListResponse.from(feed))
                .collect(Collectors.toList());

        return responses;
    }
    // 생성 요청에 대한 중간처리

    public void createProcess(FeedCreateRequest dto){
        feedRepository.save(FeedCreateRequest.toEntity(dto));
    }



    // 삭제 요청에 대한 중간처리
    public void removeProcess(Long id) {
        boolean flag = feedRepository.deleteById(id);
        if (!flag) {
            throw new IllegalArgumentException("존재하지 않는 ID: " + id);
        }
    }
    // 개별처리 요청에 대한 중간처리

    public FeedDetailResponse findOneProcess(Long id) {
        return null;
    }





}
