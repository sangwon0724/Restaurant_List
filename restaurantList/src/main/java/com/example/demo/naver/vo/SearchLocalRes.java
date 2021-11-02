package com.example.demo.naver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchLocalRes {

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
    //title , link, description, address, mapx, mapy 등을 포함
    private List<SearchLocalItem> items;



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchLocalItem {
        private String title; //업체, 기관명
        private String link; //하이퍼텍스트 link
        private String description; //설명
        private String telephone; //빈 문자열 반환. 과거에 제공되던 항목이라 하위 호환성을 위해 존재
        private String address; //주소
        private String roadAddress; //업체, 기관명의 도로명 주소
        private int mapx; //위치 정보의 x 좌표
        private int mapy; //위치 정보의 y 좌표
        
        //업체, 기관의 분류 정보
        //API 가이드에서는 items 상위에 있었으나 실제로는 items의 하위에 포함
        private String category; 
    }
}