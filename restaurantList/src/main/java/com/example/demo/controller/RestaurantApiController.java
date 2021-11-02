package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.AuthUser;
import com.example.demo.wishlist.service.WishListService;
import com.example.demo.wishlist.vo.WishListVo;

import io.swagger.annotations.Api;

import lombok.extern.slf4j.Slf4j;

@AuthUser
@RestController
@Slf4j
@Api(tags = {"RESTAURANT API CONTROLLER"})
@RequestMapping("/api/restaurant")
public class RestaurantApiController {
	
	//============================================= 맛집 리스트 ===================================================
    
	@Autowired
	private WishListService wishListService;

	//맛집 검색
    @GetMapping("/search")
    public WishListVo search(@RequestParam String query){
        return wishListService.search(query);
    }

    //위시리스트 추가
    @PostMapping("/add")
    public WishListVo add(@RequestBody WishListVo wishListVo){
        log.info("{}", wishListVo);
        return wishListService.add(wishListVo);
    }

    @GetMapping("/all")
    public List<WishListVo> findAll(){
        return wishListService.findAll();
    }

    //위시리스트 삭제
    @DeleteMapping("/{index}")
    public void delete(@PathVariable int index){
        wishListService.delete(index);
    }

    //방문자 수 증가
    @PostMapping("/{index}")
    public void addVisit(@PathVariable int index){
        wishListService.addVisit(index);
    }
}

