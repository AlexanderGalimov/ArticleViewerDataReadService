package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.repository;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.SearchDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchDocumentRepository extends ElasticsearchRepository<SearchDocument, String> {
}
