package com.jsy.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello_return() throws Exception{
        String hello = "hello world";

        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
/*
   MockMvc
   : 웹 애플리케이션을 애플리케이션 서버에 배포하지 않고도 스프링 MVC의 동작을 재현할 수 있는 클래스. HTTP GET,POST 등에 대한 API테스트를 할 수 있다.
      MockMVC   -요청->   TestDispatcherServlet(매핑정보 확인. 그에 맞는 핸들러(컨트롤러) 메서드 호출)
          -> 반환하는 실행 결과를 받아 실행 결과가 맞는지 확인

   .perform("get("/hello")")  : Mock<VC를 통해 /hello 주소로 HTTP GET 요청을 한다. 체이닝 지원
   .andExpect(status.isOk())  : mvc.perform 의 결과 검증. HTTP Header의 Status를 검증한다. OK인지 200인지 아닌지 등을 검증
   .andExpect(content().toString(hello))  : Controoler에서 "hello"를 리천하기 때문에 이 값이 맞는지를 검증.


* */
    }

    @Test
    public void helloDto_return() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                        .param("name",name)
                        .param("amount",String.valueOf(amount)))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.name",is(name)))
                            .andExpect(jsonPath("$.amount",is(amount)));
        /*
        param : API 테스트 할 때 사용될 요청 파라미터를 설정한다.
                단, 값은 String 만 허용된다.
                숫자/날짜 등의 데이터도 문자열로 변경해야한다.
        jsonPath : JSON 응답값을 필드별로 검증할 수 있는 메소드
                   $를 기준으로 필드명을 명시
         */
    }


}
