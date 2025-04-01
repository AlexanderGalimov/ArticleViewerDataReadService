package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "search_documents")
public class SearchDocument {

    @Id
    @Field(type = FieldType.Keyword)
    private String mongoId;

    @Field(type = FieldType.Text, analyzer = "russian")
    private String fullText;
}
