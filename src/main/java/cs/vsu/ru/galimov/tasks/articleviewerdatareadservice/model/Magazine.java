package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model;

import lombok.Data;

@Data
public class Magazine {
    private String name;
    private String link;

    public Magazine(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public Magazine() {
    }
}