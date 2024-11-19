package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce;

import lombok.Data;

import java.util.List;

@Data
public class ArticleResponseDTO {
    private String title;

    private String departmentMagazineName;

    private String departmentMagazineUrl;

    private String archiveInfo;

    private String archiveLink;

    private List<String> authorsNames;

    private String pdfLink;

    private List<String> keywords;

    private String annotation;
}
