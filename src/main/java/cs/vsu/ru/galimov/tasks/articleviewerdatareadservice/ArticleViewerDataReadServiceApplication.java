package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.repository")
@EnableConfigurationProperties
@OpenAPIDefinition(info = @Info(title = "Data Read CourseWork Service", version = "1.0", description = "Article Viewer Data read service"))
public class ArticleViewerDataReadServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleViewerDataReadServiceApplication.class, args);
    }
}
