package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Authors")
public class Author {
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;
}
