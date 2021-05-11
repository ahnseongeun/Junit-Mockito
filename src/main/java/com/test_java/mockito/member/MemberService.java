package com.test_java.mockito.member;


import com.test_java.mockito.domain.Member;
import com.test_java.mockito.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

}

