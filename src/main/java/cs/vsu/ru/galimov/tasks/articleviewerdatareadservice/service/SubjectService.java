package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Subject;

import java.util.List;

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
