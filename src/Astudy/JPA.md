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