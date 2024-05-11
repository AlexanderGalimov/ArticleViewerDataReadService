package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
