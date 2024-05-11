package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.exception.NotFoundException;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Article;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.repository.ArticleRepository;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    private ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article insert(Article article) {
        return articleRepository.insert(article);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public void delete(String id) {
        Article archive = findById(id);
        articleRepository.delete(archive);
    }

    @Override
    public Article findById(String id) {
        return articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Article not found with id: " + id));
    }

    @Override
    public Article findByUniqUIIDS3(String uniqUIIDS3) {
        return articleRepository.findByUniqUIIDS3(uniqUIIDS3);
    }

    @Override
    public Article update(Article object) {
        return articleRepository.save(object);
    }

    @Override
    public List<Article> findByAuthorIdsContaining(String authorId) {
        return articleRepository.findByAuthorIdsContaining(authorId);
    }

    @Override
    public Article findByPdfParamsTitle(String title) {
        return articleRepository.findByPdfParamsTitle(title);
    }

    @Override
    public List<Article> findByPdfParamsTitleContaining(String title) {
        return articleRepository.findByPdfParamsTitleContaining(title);
    }

    @Override
    public List<Article> findByFullTextContaining(String fullText) {
        return articleRepository.findByFullTextContaining(fullText);
    }

    @Override
    public List<Article> findByDepartmentMagazineNameContaining(String departmentMagazineName) {
        return articleRepository.findByDepartmentMagazineNameContaining(departmentMagazineName);
    }
}