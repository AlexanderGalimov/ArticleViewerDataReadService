package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.repository;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Subject;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends Neo4jRepository<Subject, Long> {
    List<Subject> findByDepartmentMagazineName(String departmentMagazineName);

    Subject findByTitle(String title);

    List<Subject> findByTitleContaining(String title);
}