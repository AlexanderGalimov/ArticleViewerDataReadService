package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Subject;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.repository.SubjectRepository;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Override
    public Subject updateSubject(Long id, Subject subject) {
        if (subjectRepository.existsById(id)) {
            subject.setId(id);
            return subjectRepository.save(subject);
        }
        return null;
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public List<Subject> findByAuthorsNamesContaining(String authorName) {
        return subjectRepository.findByAuthorsNamesContaining(authorName);
    }

    @Override
    public List<Subject> findByDepartmentMagazineName(String departmentMagazineName) {
        return subjectRepository.findByDepartmentMagazineName(departmentMagazineName);
    }

    @Override
    public List<Subject> findByTitleContaining(String title) {
        return subjectRepository.findByTitleContaining(title);
    }
}
