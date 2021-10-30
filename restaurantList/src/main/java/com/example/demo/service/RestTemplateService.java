package com.example.demo.service;

import java.net.URI;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RestTemplateService {
	//공통  : [ http://localhost:8093/server_to_server/client/call_server ]를 호출
	
	//GET 방식으로 호출 (Select)
	public String getUser() {
		URI uri = UriComponentsBuilder
					.fromUriString("http://localhost:8093")
					.path("/server_to_server/client/call_server")
					.encode()
					.build()
					.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		
		return result;
	}
	
	//POST 방식으로 호출 (Insert)
	public UserVO postUser() {
		URI uri = UriComponentsBuilder
					.fromUriString("http://localhost:8093")
					.path("/server_to_server/client/call_server")
					.encode()
					.build()
					.toUri();
		
		UserVO user = UserVO.builder().name("HongGilDong").age(25).email("hong@naver.com").birthday("19970229").phoneNumber("0001112222").build();
		RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserVO> result = restTemplate.postForEntity(uri, user, UserVO.class);
        
        return result.getBody();
	}
	
	//PUT 방식으로 호출 (Update)
	public void putUser() {
		URI uri = UriComponentsBuilder
					.fromUriString("http://localhost:8093")
					.path("/server_to_server/client/call_server")
					.encode()
					.build()
					.toUri();
		
		UserVO user = UserVO.builder().name("HongGilDong").age(25).email("hong@naver.com").birthday("19970229").phoneNumber("0003334444").build();
		RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(uri, user);
	}
	
	//DELETE 방식으로 호출 (Delete)
	public void deleteUser() {
		URI uri = UriComponentsBuilder
					.fromUriString("http://localhost:8093")
					.path("/server_to_server/client/call_server")
					.queryParam("name", "HongGilDong")
					.encode()
					.build()
					.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri);
	}
	
	//데이터 교환 (exchange)
	public void exchange() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8093")
				.path("/server_to_server/client/call_server")
				.queryParam("name", "HongGilDong")
				.encode()
				.build()
				.toUri();

		UserVO user = UserVO.builder().name("HongGilDong").age(25).email("hong@naver.com").birthday("19970229").phoneNumber("0001112222").build();

        RequestEntity<UserVO> req = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON) //org.springframework.http.MediaType
                .header("x-authorization","my-header")
                .body(user);

        RestTemplate restTemplate = new RestTemplate();
        
        ResponseEntity<UserVO> response = restTemplate.exchange(req, new ParameterizedTypeReference<>(){}); //org.springframework.core.ParameterizedTypeReference
        log.info("{}",response.getStatusCode());
        log.info("{}",response.getHeaders());
        log.info("{}",response.getBody());
	}
	
	public ResponseEntity<String> naver(){
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query","구로")
                .queryParam("display","10")
                .queryParam("start","1")
                .queryParam("sort","random")
                .encode()
                .build()
                .toUri();
        log.info("uri : {}", uri);

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id","애플리케이션 등록 시 발급받은 client id 값")
                .header("X-Naver-Client-Secret","애플리케이션 등록 시 발급받은 client secret 값")
                .build();

        RestTemplate restTemplate = new RestTemplate();
        
        ResponseEntity<String> response = restTemplate.exchange(req, new ParameterizedTypeReference<>(){});
        
        log.info("{}",response.getStatusCode());
        log.info("{}",response.getHeaders());
        log.info("{}",response.getBody());
        
        return response;
    }
}
