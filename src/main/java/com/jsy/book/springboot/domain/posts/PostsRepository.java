package com.jsy.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    // JpaRepository <Entity클래스, PK타입> 을 상속하면 기본적인 CRUD메소드가 생성된다.
    // * Entity클래스와 기본 Entity Repository는 함께 위치해야한다. Entity클래스는 기본 Repository 없이는 제대로 역할을 할 수 가 없기때문에


}
