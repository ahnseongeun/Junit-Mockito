package com.test_java.mockito.study;


import com.test_java.mockito.domain.Member;
import com.test_java.mockito.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Mock을 사용하기 위해서 mock으로 생성한 객체가 null값이 아니기 위허서는 @ExtendWith 애노테이션을 붙혀줘야한다.
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
    }
}