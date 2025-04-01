package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Node("Subject")
public class Subject {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private List<String> authorsNames;

    private String departmentMagazineName;

    @Relationship(type = "RELATED_TO")
    private Set<Subject> relatedSubjects;
}
