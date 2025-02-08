package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Article;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.SearchDocument;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl.ArticleServiceImpl;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl.SearchDocumentServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.util.List;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.repository")
@OpenAPIDefinition(info = @Info(title = "Data Read CourseWork Service", version = "1.0", description = "Article Viewer Data read service"))
public class ArticleViewerDataReadServiceApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ArticleViewerDataReadServiceApplication.class, args);

        ArticleServiceImpl articleService = context.getBean(ArticleServiceImpl.class);

        SearchDocumentServiceImpl searchDocumentService = context.getBean(SearchDocumentServiceImpl.class);

        List<Article> articleList = articleService.findAll();

        SearchDocument document = new SearchDocument("test1", "территорий");
        searchDocumentService.save(document);

//        for (int i = 0; i < 10; i++) {
//            Article article = articleList.get(i);
//
//            SearchDocument document = new SearchDocument(article.getId(), article.getFullText());
//            searchDocumentService.save(document);
//        }

        List<SearchDocument> documents = searchDocumentService.search("территория");
        System.out.println(documents);
    }
}
