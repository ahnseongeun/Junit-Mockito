package com.test_java.mockito.study;


import com.test_java.mockito.domain.Member;
import com.test_java.mockito.domain.Study;
import com.test_java.mockito.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Mock을 사용하기 위해서 mock으로 생성한 객체가 null값이 아니기 위허서는 @ExtendWith 애노테이션을 붙혀줘야한다.
 */
/**
 * 모든 Mock 객체의 행동
 * - Null을 리턴한다. (Optional 타입 경우에는 Optional.null로 나옴)
 * - Primitive 타입은 기본 Primitive 값
 * - 콜렉션은 비어있는 콜렉션
 * - Void 메소드는 예외를 던지지 않고 아무런 일도 발생하지 않는다.
 * -> Stubbing
 * - 특정한 매개변수를 받은 경우 특정한 값을 리턴하거나 예외를 던지도록 만들 수 있다.
 * - Void 메소드 특정 매개변수를 받거나 호출된 경우 예외를 발생 시킬 수 있다.
 * - 메소드가 동일한 매개변수로 여러번 호출될 때 각각 다르게 행동하도록 조작 할 수 있다.
 */
@ExtendWith(MockitoExtension.class)
class StudyServiceTest {


    @Test
    void createStudyServiceTest(){
        /**
         * 인터페이스를 객체로 만들어서 테스트를 하기 위해서는 구현체를 만들어 줘야한다.
         */
        //MemberService memberService = new MemberService();
        //StudyRepository studyRepository = new StudyRepository();
    }
    /**
     * Mock을 사용하기 적절할때 interface는 있는데 해당 구현체가 없을 경우 Mock을 사용하면 좋다.
     * 각각 Mock 객체를 만들어서 사용하는방법
     */
    @Test
    void createStudyService(){

        MemberService memberService = mock(MemberService.class);
        StudyRepository studyRepository = mock(StudyRepository.class);

        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
    }

    /**
     * Mock 애노테이션을 이용해서 사용하는 방법
     */
    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    void createStudyService2(){

        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
    }

    

    @Test
    void createStudyService3(@Mock MemberService memberService1,
                             @Mock StudyRepository studyRepository1){

        StudyService studyService =
                new StudyService(memberService1,studyRepository1);
        assertNotNull(studyService);

        //Optional.empty()
        Optional<Member> member = memberService1.findById(1L);
        //아무일도 일어나지 않는다.
        memberService1.validate(2L);

        Member member1 = new Member(2L,"test@naver.com");

        /**
         * memberService1에서 2L로 findById를 하게되면 thenReturn 원하는 객체로 return된다.
         *
         */
        when(memberService1.findById(2L))
                .thenReturn(Optional.of(member1));

        Study study = new Study(10,"java");

        /**
         * 실제로 2L로 findById가 발생했기 때문에 findById는 member1의 객체가 들어가게 된다.
         */
        Optional<Member> findById = memberService1.findById(2L);
        assertEquals("test@naver.com",findById.get().getEmail());
        studyService.createNewStudy(2L,study);

        /**
         * 2L이 아니라 범용적이게 하는 방법
         * any()
         * anyInt()
         */
        when(memberService1.findById(any()))
                .thenReturn(Optional.of(member1));
    }

    /**
     * return이 아니라 예외처리하는 방법
     */
    @Test
    void createStudyService4(){
        StudyService studyService = new StudyService(memberService,studyRepository);
        assertNotNull(studyService);

        Member member = new Member(1L,"test@naver.com");

        //when(memberService.findById(1L))
        //        .thenThrow(new RuntimeException());

        //memberService.findById(any());

        /**
         * void일 경우
         */
        doThrow(new IllegalArgumentException())
                .when(memberService)
                .validate(1L);

        /**
         * 예외 테스트 하는 법
         */
        assertThrows(IllegalArgumentException.class, () -> {
           memberService.validate(1L);
        });

        memberService.validate(2L);
    }

    /**
     * 메소드가 여러번 호출 될 때 다르게 호출하기
     */
    @Test
    void createStudyService5(){
        StudyService studyService = new StudyService(memberService,studyRepository);
        assertNotNull(studyService);

        Member member = new Member(1L,"test@naver.com");

        /**
         * findById가 호출되었을 때 첫번째 리턴, 두번째 리턴, 세번째 리턴을 다르게 한다.
         */
        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());

        Optional<Member> byId = memberService.findById(1L);
        assertEquals("test@naver.com", byId.get().getEmail());


        assertThrows(RuntimeException.class, () -> {
            memberService.findById(2L);
        });

        assertEquals(Optional.empty(), memberService.findById(3L));

    }

    @Test
    void createStudyService6(){
        Study study = new Study(10, "테스트");
        StudyService studyService = new StudyService(memberService,studyRepository);
        Member member = new Member(1L,"ast3138@naver.com");
        //TODO memberService 객체에 findById 메소드를 1L 값으로 호출하면 member 객체를 리턴하도록 Stubbing
        when(memberService.findById(1L))
                .thenReturn(Optional.of(member));

        //TODO studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴하도록 Stubbing
        when(studyRepository.save(study))
                .thenReturn(study);

        studyService.createNewStudy(1L,study);
        assertNotNull(study.getOwner());
        assertEquals(member.getId(),study.getOwner());
    }

}