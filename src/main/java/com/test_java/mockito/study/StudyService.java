package com.test_java.mockito.study;

import com.test_java.mockito.domain.Member;
import com.test_java.mockito.domain.Study;
import com.test_java.mockito.member.MemberService;

import java.util.Optional;

public class StudyService {

    private final MemberService memberService;

    private final StudyRepository repository;

    public StudyService(MemberService memberService, StudyRepository repository) {
        assert memberService != null;
        assert repository != null;
        this.memberService = memberService;
        this.repository = repository;
    }

    public Study createNewStudy(Long memberId, Study study) {
        Optional<Member> member = memberService.findById(memberId);
        if (member.isPresent()) {
            study.setOwnerId(memberId);
        } else {
            throw new IllegalArgumentException("Member doesn't exist for id: '" + memberId + "'");
        }
        Study newStudy =  repository.save(study);
        memberService.notify(newStudy);
        memberService.notify(member);
        return newStudy;
    }

}
