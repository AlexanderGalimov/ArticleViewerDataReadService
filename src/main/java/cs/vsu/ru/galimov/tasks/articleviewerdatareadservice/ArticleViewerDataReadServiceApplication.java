package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl.ArticleServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Data Read CourseWork Service", version = "1.0", description = "Article Viewer Data read service"))
public class ArticleViewerDataReadServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(ArticleViewerDataReadServiceApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ArticleViewerDataReadServiceApplication.class, args);

        ArticleServiceImpl service = context.getBean(ArticleServiceImpl.class);

        String text = service.findAll().get(1).getFullText();

        // Извлекаем аннотацию
        String annotation = extractAnnotation(text);
        System.out.println("Аннотация: " + annotation);

        // Извлекаем ключевые слова
        List<String> keywords = extractKeywords(text);
        System.out.println("Ключевые слова: " + keywords);
    }

    private static String extractAnnotation(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        String startMarker = "Аннотация:";
        String endMarker = "Ключевые слова:";

        int startIndex = text.indexOf(startMarker);
        int endIndex = text.indexOf(endMarker);

        if (startIndex == -1 || endIndex == -1 || startIndex >= endIndex) {
            return "";
        }

        // Извлекаем текст между маркерами
        return text.substring(startIndex + startMarker.length(), endIndex).trim();
    }

    private static List<String> extractKeywords(String text) {
        if (text == null || text.isEmpty()) {
            return new ArrayList<>();
        }

        String startMarker = "Ключевые слова:";
        int startIndex = text.indexOf(startMarker);

        if (startIndex == -1) {
            return new ArrayList<>();
        }

        // Извлекаем текст после "Ключевые слова:"
        String keywordsText = text.substring(startIndex + startMarker.length()).trim();

        // Убираем возможные переносы строк и лишние пробелы
        keywordsText = keywordsText.replaceAll("\\s+", " ").trim();

        // Ищем точку, которая завершает ключевые слова
        int endIndex = keywordsText.indexOf(".");
        if (endIndex != -1) {
            keywordsText = keywordsText.substring(0, endIndex).trim();
        }

        // Разделяем по запятым и создаем список
        String[] keywordsArray = keywordsText.split(",");
        List<String> keywordsList = new ArrayList<>();
        for (String keyword : keywordsArray) {
            keywordsList.add(keyword.trim());
        }

        return keywordsList;
    }

}
