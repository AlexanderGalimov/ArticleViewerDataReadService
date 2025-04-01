package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.minio;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class MinioAutoConfiguration {

    private final MinioProperties properties;

    @Bean
    public MinioTemplate template() {
        return new MinioTemplate(properties);
    }
}


