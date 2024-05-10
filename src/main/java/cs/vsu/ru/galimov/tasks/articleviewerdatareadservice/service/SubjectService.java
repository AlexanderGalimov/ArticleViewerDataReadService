package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Subject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SubjectService {
    Subject createSubject(Subject subject);

    List<Subject> getAllSubjects();

    Subject getSubjectById(Long id);

    Subject updateSubject(Long id, Subject subject);

    void deleteSubject(Long id);

    Subject findByTitle(String title);

    List<Subject> findByDepartmentMagazineName(String departmentMagazineName);

    List<Subject> findByTitleContaining(String title);
}
