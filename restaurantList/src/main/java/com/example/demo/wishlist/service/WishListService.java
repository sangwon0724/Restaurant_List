package com.example.demo.wishlist.service;

import com.example.demo.naver.NaverClient;
import com.example.demo.naver.vo.SearchImageReq;
import com.example.demo.naver.vo.SearchLocalReq;
import com.example.demo.wishlist.vo.WishListVo;
import com.example.demo.wishlist.entity.WishListEntity;
import com.example.demo.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

    //검색 결과 리턴
    public WishListVo search(String query){
        // 지역 검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);
        var searchLocalRes = naverClient.searchLocal(searchLocalReq);

        //지역 검색 결과 존재
        if(searchLocalRes.getTotal() > 0){
            var localItem = searchLocalRes.getItems().stream().findFirst().get(); //지역검색 결과
            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>","");
            var searchImageReq = new SearchImageReq(); //이미지 검색 요청용 객체
            searchImageReq.setQuery(imageQuery);

            // 이미지 검색
            var searchImageRes = naverClient.searchImage(searchImageReq); //이미지 검색 결과

            //이미지 검색 결과 존재
            if(searchImageRes.getTotal() > 0){
                var imageItem = searchImageRes.getItems().stream().findFirst().get();

                // 결과를 리턴
                var result = new WishListVo();
                result.setTitle(localItem.getTitle().replaceAll("<[^>]*>",""));
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());
                return result;
            }
        }

        return new WishListVo();
    }

    //위시리스트 추가
    public WishListVo add(WishListVo wishListVo) {
        var entity = voToEntity(wishListVo);
        var saveEntity = wishListRepository.save(entity);
        return entityToVo(saveEntity);
    }
    
    //vo → entity
    private WishListEntity voToEntity(WishListVo wishListVo){
        var entity = new WishListEntity();
        entity.setIndex(wishListVo.getIndex());
        entity.setTitle(wishListVo.getTitle());
        entity.setCategory(wishListVo.getCategory());
        entity.setAddress(wishListVo.getAddress());
        entity.setRoadAddress(wishListVo.getRoadAddress());
        entity.setHomePageLink(wishListVo.getHomePageLink());
        entity.setImageLink(wishListVo.getImageLink());
        entity.setVisit(wishListVo.isVisit());
        entity.setVisitCount(wishListVo.getVisitCount());
        entity.setLastVisitDate(wishListVo.getLastVisitDate());
        return entity;
    }

    //entity → vo
    private WishListVo entityToVo(WishListEntity wishListEntity){
        var vo = new WishListVo();
        vo.setIndex(wishListEntity.getIndex());
        vo.setTitle(wishListEntity.getTitle().replaceAll("<[^>]*>",""));
        vo.setCategory(wishListEntity.getCategory());
        vo.setAddress(wishListEntity.getAddress());
        vo.setRoadAddress(wishListEntity.getRoadAddress());
        vo.setHomePageLink(wishListEntity.getHomePageLink());
        vo.setImageLink(wishListEntity.getImageLink());
        vo.setVisit(wishListEntity.isVisit());
        vo.setVisitCount(wishListEntity.getVisitCount());
        vo.setLastVisitDate(wishListEntity.getLastVisitDate());
        return vo;
    }

    //모든 데이터 리턴
    public List<WishListVo> findAll() {
        return wishListRepository.findAll()
                .stream()
                .map(it -> entityToVo(it))
                .collect(Collectors.toList());
    }

    //위시리스트 삭제
    public void delete(int index) {
        wishListRepository.deleteById(index);
    }

    //방문자 수 증가
    public void addVisit(int index){
        var wishItem = wishListRepository.findById(index);
        if(wishItem.isPresent()){
            var item = wishItem.get();
            item.setVisit(true);
            item.setVisitCount(item.getVisitCount()+1);
        }
    }
}