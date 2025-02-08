package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.config;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.SearchDocument;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

@Configuration
public class ElasticsearchConfig {
    private final ElasticsearchOperations elasticsearchOperations;

    public ElasticsearchConfig(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Bean
    public IndexOperations createIndexIfNotExists() {
        IndexOperations indexOps = elasticsearchOperations.indexOps(IndexCoordinates.of("search_documents"));

        if (!indexOps.exists()) {
            indexOps.create();
            indexOps.putMapping(elasticsearchOperations.indexOps(SearchDocument.class).createMapping());
        }

        return indexOps;
    }
}

