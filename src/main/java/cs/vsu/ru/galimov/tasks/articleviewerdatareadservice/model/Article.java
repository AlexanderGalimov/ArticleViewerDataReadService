package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Articles")
public class Article {
    @Id
    private String id;

    private Magazine magazine;

    private DepartmentMagazine departmentMagazine;

    private DateArchive dateArchive;

    private PDFParams pdfParams;

    private List<String> authorIds;

    private String fullText;

    private List<String> keywords;

    private List<String> lpKeywords;

    private String annotation;

    private String uniqUIIDS3;

    public Article(Magazine magazine, DepartmentMagazine departmentMagazine, DateArchive dateArchive, PDFParams pdfParams) {
        this.magazine = magazine;
        this.departmentMagazine = departmentMagazine;
        this.dateArchive = dateArchive;
        this.pdfParams = pdfParams;
    }
}

