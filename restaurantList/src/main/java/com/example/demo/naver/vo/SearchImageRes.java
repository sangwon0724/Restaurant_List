package com.example.demo.naver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImageRes {

	//검색 결과를 생성한 시간
	//타입 : datetime
    private String lastBuildDate;
    
    //검색 결과 문서의 총 개수
    private int total;
    
    //검색 결과 문서의 시작점
    private int start;

    //검색된 검색 결과의 개수
    private int display;
    
    //개별 검색 결과
    //title, link, thumbnail, sizeheight, sizewidth 등을 포함
    private List<SearchImageItem> items;



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchImageItem {
        private String title; //이미지의 제목
        private String link; //이미지의 하이퍼텍스트 link
        private String thumbnail; //이미지의 썸네일 link
        private String sizewidth; //이미지의 썸네일 너비 (단위 : 픽셀)
        private String sizeheight; //이미지의 썸네일 높이 (단위 : 픽셀)
    }
}