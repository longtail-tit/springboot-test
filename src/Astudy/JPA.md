<h1>JPA</h1>

> JPA란?  

 객체지향 프로그래밍 언어와 관계형 데이터베이스를 중간에서 패러다임 일치를 시켜주기 위한 기술

 개발자 - 객체지향 프로그래밍 
  --> JPA가 관계형 데이터베이스에 맞게 SQL 대신 생성해줌
  

  * 객체 중심으로 개발할 수 있다. 
  * 생산성 향상, 유지보수 하기 편하다.
  * 규모가 크고, 대규모 트래픽과 데이터를 가진 서비스에 적합하다. 
  

> SpringData JPA

JPA는 인터페이스이다. 이를 구현하기 위해서는 구현체가 필요하다.  대표적으로는 Hibernate, Eclipse Link 등이 있다. 
하지만 Spring에서 JPA를 사용할 때 이 구현체를 직접 다루지 않는다.

* JPA <- Hibernate <- Spring Data JPA

> JPA 장점

* CRUD 쿼리를 직접 작성할 필요 X
* 부모,자식 관계 표현
* 1:N 관계 표현 
* 상태와 행위를 한 곳에서 관리

> Spring Data JPA 테스트코드 작성 

~~~
package com.jsy.book.springboot.domain.posts;

import javafx.geometry.Pos;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {  // save, findall 기능

    @Autowired
    PostsRepository  postsRepository ;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }
    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 내용";

        postsRepository.save(Posts.builder().title(title).content(content).author("jsy").build()); 

        //when
        List<Posts> postsList = postsRepository.findAll(); 

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}

~~~

* @After : Junit 단위테스트 끝날 때마다 수행되는 메소드 지정. 주로 테스트간 데이터 침범 막기 위해 사용. 
* postsRepository.save : id가 있으면 udpate, 없으면 insert쿼리가 실행된다. 
* findAll : 테이블 posts에 있는 모든 데이터를 조회해오는 메소드 
 
 실행 후 쿼리 로그를 보기 위해선 application.properties 파일을 추가해줘야한다. 
 ~~~
spring.jpa.show_sql=true
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
~~~
 아래 줄은 출력되는 쿼리를 MySQL 버전으로 변경하는 것  
 
 
 > 등록/수정/조회 API 만들기 

API를 만들기 위해 3개의 클래스가 필요하다. 
* Request 데이터를 받을 Dto 
* API 요청을 받을 Controller
* 트랜잭션, 도메인 기능 간의 순서를 보장하는 Service 

<b>

***Web Layer***  
* 컨트롤러(@Controller)와 JSP/Freemarker 등의 뷰 템플릿 영역. 
* 필터, 인터셉터, 컨트롤러 어드바이스(@ControllerAdvice)등 외부 요청과 응답에 대한 전반적인 영역.

***Service Layer*** 
 * @Service에 사용되는 영역 
 * 일반적으로 Controller와 Dao의 중간 영역에서 사용된다. 
 * @Transaction이 사용되어야하는 영역이기도 하다. 

***Repository Layer***
* DataBase와 같이 데이터 저장소에 접근하는 영역
* Dao 영역

***Dtos***
* 계층 간에 테이터 교환을 위한 객체
* ex) 뷰 템플릿 엔진에 사용될 객체나 Repository Layer에서 결과로 넘겨준 객체 등

***Domain Model***
* 개발 대상을 모든 사람이 동일한 관점에서 이해할 수 있고 공유할 수 있도록 단순화 시킨 것. 
* ex) 택시 앱 - 배차,승차,요금 등이 모두 도메인이 될 수 있다. 
* @Entity 가 사용된 영역도 도메인 모델

비즈니스 처리를 담당해야 할 곳은 domain 이다.

기존엔 모든 로직이 서비스 클래스 내부에서 처리. 그러다보니 **서비스 계층이 무의미, 객체란 단순히 데이터 덩어리** 역할만 하게 되었다. 

반면 도메인 모델에서 처리할 경우 서비스 메소드는 트랜잭션과 도메이 ㄴ간의 순서만 보장할 수 있다. 