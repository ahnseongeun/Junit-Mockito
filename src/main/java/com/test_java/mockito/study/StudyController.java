package com.test_java.mockito.study;

import com.test_java.mockito.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudyController {

    public final StudyRepository studyRepository;

    public StudyController(StudyRepository studyRepository){
        this.studyRepository = studyRepository;
    }

    @GetMapping("/study/{id}")
    public Study getStudy(@PathVariable Long id) {
        return studyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Study not found for '" + id + "'"));
    }

    @PostMapping("/study")
    public Study createsStudy(@RequestBody Study study) {
        return studyRepository.save(study);
    }

}
