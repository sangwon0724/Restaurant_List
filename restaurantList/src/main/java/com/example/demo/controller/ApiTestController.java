package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.AuthUser;
import com.example.demo.service.AsyncService;
import com.example.demo.service.Calculator;
import com.example.demo.service.RestTemplateService;
import com.example.demo.vo.CalculatorReq;
import com.example.demo.vo.CalculatorRes;
import com.example.demo.vo.UserRequest;
import com.example.demo.vo.UserResponse;
import com.example.demo.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@AuthUser
@RestController
@Slf4j
@Api(tags = {"REST API TEST CONTROLLER"})
@RequestMapping("/api/test")
public class ApiTestController {
	@Autowired
	AsyncService asyncService; //????????? ??????
	
	@Autowired
	RestTemplateService restTemplateService; //Server To Server
	
	@Autowired
	Calculator calculator; //JUnit
	
	//============================================= ObjectMapper ===================================================
	
	@GetMapping("/objectMapper")
	public HashMap<String, Object> apiTestObjectMapper() throws JsonProcessingException {
        HashMap<String, Object> result = new HashMap<String, Object>();

		ObjectMapper objectMapper = new ObjectMapper();
		
		//Java Object To Json
		UserVO user = UserVO.builder().name("HongGilDong").age(25).email("hong@naver.com").birthday("19970229").phoneNumber("0001112222").build();
		String javaObjectToJson = objectMapper.writeValueAsString(user);
		result.put("Java Obejct To Json : ", javaObjectToJson);
		
		//Json To Java Object
		String jsonString = "{\"name\":\"jsonString\",\"age\":25,\"email\":\"jsonString@naver.com\",\"phoneNumber\":\"0001112222\",\"birthday\":\"19970229\"}";
		UserVO jsonToJavaObject = objectMapper.readValue(jsonString, UserVO.class);
		result.put("Json To Java Obejct : ", jsonToJavaObject.toString());
		        
		//Mapping Json Array to Java List
		String jsonStringA = "{\"name\":\"jsonStringA\",\"age\":25,\"email\":\"jsonStringA@naver.com\",\"phoneNumber\":\"0001112222\",\"birthday\":\"19970229\"}";
		String jsonStringB = "{\"name\":\"jsonStringB\",\"age\":25,\"email\":\"jsonStringB@naver.com\",\"phoneNumber\":\"0001112222\",\"birthday\":\"19970229\"}";
		String jsonArray = "["+jsonStringA+","+jsonStringB+"]";
		List<UserVO> users = objectMapper.readValue(jsonArray, new TypeReference<List<UserVO>>(){});
		result.put("Mapping Json Array to Java List : ", users);
		        
		//Mapping Json data to a Map
		String jsonStringForMap = "{\"name\":\"jsonStringForMap\",\"age\":25,\"email\":\"jsonStringForMap@naver.com\",\"phoneNumber\":\"0001112222\",\"birthday\":\"19970229\"}";
		HashMap<String, Object> map = objectMapper.readValue(jsonStringForMap, new TypeReference<HashMap<String,Object>>(){});
		result.put("Mapping Json data to a Map : ", map);
		        
		return result;
	}
	
	//============================================= ????????? ?????? ===================================================
	
	@PostMapping("/validation")
	public ResponseEntity apiTestValidation(@Valid @RequestBody UserVO user, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			
			bindingResult.getAllErrors().forEach(error -> {
				//FieldError field = (FieldError) error;
				String message = error.getDefaultMessage();
				
				//System.out.println("field : " + field);
				System.out.println("message : " + message);
				
				//sb.append("field : " + field);
				sb.append("message : " + message);
			});
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
		}
		
		return ResponseEntity.ok(user);
	}
	
	//============================================= ?????? ===================================================
	
	@PostMapping("/filter/post")
    public UserVO postApiTestFilter(@RequestBody UserVO user){
        return user;
    }

    @DeleteMapping("/filter/delete")
    public ResponseEntity deleteApiTestFilter(){
        return ResponseEntity.ok().build();
    }
    
	//============================================= ????????? ?????? ===================================================

    @GetMapping("/async/listenableFuture")
    public ListenableFuture<Integer> listenableFuture() throws InterruptedException, ExecutionException {
    	log.info("/async/listenableFuture");
        return asyncService.listenableFuture(10000);
    }

    @GetMapping("/async/completableFuture")
    public CompletableFuture<String> completableFuture() throws Exception {
    	log.info("/async/completableFuture");
        return asyncService.completableFuture(1000);
    }
    
	//============================================= Server To Server ===================================================
    
    @GetMapping("/server_to_server/client/call_server")
    public Object callServer(@RequestParam String mode) throws Exception {
    	log.info("/server_to_server/client/call_server");
    	
    	Object result = null;
    	
    	switch (mode) {
		case "get":
			result = restTemplateService.getUser();
			break;
		case "post":
			result = restTemplateService.postUser();
			break;
		case "put":
			restTemplateService.putUser();
			result=true;
			break;
		case "delete":
			restTemplateService.deleteUser();
			result=true;
			break;
		case "exchange":
			restTemplateService.exchange();
			break;
		}
    	
		return result;
    	
    }
    
	//============================================= JUnit ===================================================
    
    @GetMapping("/junit/calculator/sum")
    public int getApiTestJUnit(CalculatorReq calculatorReq){
    	 var result = calculator.sum(calculatorReq.getX(), calculatorReq.getY());
         return result;
    }
    
    @PostMapping("/junit/calculator/sum")
    public CalculatorRes postApiTestJUnit(@RequestBody CalculatorReq calculatorReq){
    	CalculatorRes res = new CalculatorRes();
        int sum = calculator.sum(calculatorReq.getX(), calculatorReq.getY());
        res.setResult(sum);

        var body = new CalculatorRes.Result();
        body.setResponseResult(sum);

        res.setBody(body);
        return res;
    }
    
	//============================================= Swagger ===================================================
    
    @ApiOperation(value = "hello method", notes = "???????????? ?????? GET API")
    @ApiImplicitParams({
    	@ApiImplicitParam(name="name", value="????????? ??????"),
    	@ApiImplicitParam(name="age", value="????????? ??????")
    })
    @GetMapping("/swagger/{name}")
    public String swaggerHello(@PathVariable String name, @RequestParam int age){
        return "hello";
    }

    @GetMapping("/swagger/user")
    public UserResponse getSwaggerUser(UserRequest userRequest){
        return new UserResponse(userRequest.getName(), userRequest.getAge());
    }

    @PostMapping("/swagger/user")
    @ApiResponse(code = 404, message = "not found")
    public UserResponse postSwaggerUser(@RequestBody UserRequest userRequest){
        return new UserResponse(userRequest.getName(), userRequest.getAge());
    }
    
	//============================================= ?????? API ?????? ===================================================
    
    @GetMapping("/naver/map")
    public ResponseEntity<String> apiTestNaver(){
    	return restTemplateService.naver();
    }
}

