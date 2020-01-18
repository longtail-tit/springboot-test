package com.jsy.book.springboot.domain.posts;

import javafx.geometry.Pos;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;

        /*
        @Entity : 테이블과 링크될 클래스. JPA 어노테이션.
        @GeneratedValue : PK 생성 규칙. 스프링부트 2.0 에서는 GenerationType.IDENTITY 옵션 추가해야만 auto_increment가 된다.
        @Column : 테이블의 칼럼을 나타낸다. 굳이 선언 안해도 해당 클래스의 필드는 모두 칼럼이 된다.
         */
    }
}
