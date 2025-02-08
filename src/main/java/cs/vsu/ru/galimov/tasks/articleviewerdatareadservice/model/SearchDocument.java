package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "search_documents")
public class SearchDocument {

    @Id
    @Field(type = FieldType.Keyword)
    private String mongoId;

    @Field(type = FieldType.Text, analyzer = "russian")
    private String fullText;

    public SearchDocument() {}

    public SearchDocument(String mongoId, String fullText) {
        this.mongoId = mongoId;
        this.fullText = fullText;
    }
}
