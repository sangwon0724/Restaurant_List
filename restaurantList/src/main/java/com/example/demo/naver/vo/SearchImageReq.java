package com.example.demo.naver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImageReq {
	
    private String query = "";
    
    //검색 결과 출력 건수 지정
    //1(기본값), 5(최대)
    private int display = 1;

    //검색 시작 위치로 1만 가능
    //1(기본값), 1(최대)
    private int start = 1;

    //정렬 옵션: sim (유사도순), date (날짜순)
    private String sort = "sim";

    //사이즈 필터 옵션: all(전체, 기본값), large(큰 사이즈), medium(중간 사이즈), small(작은 사이즈)
    private String filter = "all";

    public MultiValueMap<String, String> toMultiValueMap(){
        var map = new LinkedMultiValueMap<String, String>();

        map.add("query",query);
        map.add("display",String.valueOf(display));
        map.add("start", String.valueOf(start));
        map.add("sort",sort);
        map.add("filter",filter);
        
        return map;
    }
}