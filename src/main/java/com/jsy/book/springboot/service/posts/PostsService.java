package com.jsy.book.springboot.service.posts;

import com.jsy.book.springboot.domain.posts.PostsRepository;
import com.jsy.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /*
    Bean 주입 방식
    - @Autowired
    - setter
    - 생성자 (권장)

    @RequiredArgsConstructor 을 사용하면 final 이 선언된 모든 필드를 인자값으로 하는 생성자를 생성해준다.
    생성자를 직접 안쓰고 롬복 어노테이션 사용하는 이유: 해당 클래스의 의존성 관계가 변경디 때마다 생성자 코드를 계속해서 수정하는 번거로움을 해결하기 위해
     */

}
