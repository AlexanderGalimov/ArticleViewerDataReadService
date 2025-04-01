package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.SearchDocument;

import java.util.List;

public interface SearchDocumentService {
    SearchDocument save(SearchDocument document);

    SearchDocument update(String id, SearchDocument subject);

    List<SearchDocument> search(String query);
}
