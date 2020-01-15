package com.jsy.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Getter
@RequiredArgsConstructor
public class HelloResponseDtoTest {

    @Test
    public void 룸복_테스트() throws Exception{
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);

        /*
        assertThat : assertj 테스트 검증 라이브러리의 검증 메소드
                     검증하고 싶은 대상을 메소드 인자로 받는다. dto.getNAme
                     체이닝 -> isEqualTo 같은거 지원
        isEqualTo : assertj의 동등 비교 메소드. assertThat 과 isEqualTo 의 값을 비교해서 같을 때만 성공

        Junit의 vs assertj의 assertThat 차이?
        - Junit을 사용하면 is()와 같은 CoreMatchers 라이브러리가 필요하다.
        - assertj가 자동완성이 더 확실하게 지원된다. (Junit에서의 Matcher 라이브러리는 자동완성 지원이 약함)

         */
    }
}
