package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.SearchDocument;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SearchDocumentService {
    SearchDocument save(SearchDocument document);

    SearchDocument update(String id, SearchDocument subject);

    List<SearchDocument> search(String query);
}
