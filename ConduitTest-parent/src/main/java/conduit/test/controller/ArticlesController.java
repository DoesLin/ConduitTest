package conduit.test.controller;

import conduit.test.repository.dao.DaoArticle;
import conduit.test.dto.DtoArticle;
import conduit.test.service.impl.ArticleWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping({"/articles"})
public class ArticlesController {


    @Autowired
    private ArticleWS articleWS;

//    private List<ArticleMock> articleMocks = createList();

    @GetMapping(produces = "application/json")
    public List<DtoArticle> firstPage() {
        List<DtoArticle> listArticles = new ArrayList<DtoArticle>();

        List<DaoArticle> listDaoArticles = articleWS.getAlls();
        for (DaoArticle daoArticle : listDaoArticles) {
            DtoArticle article = new DtoArticle(daoArticle);
            listArticles.add(article);
        }

        return listArticles;
    }

    @DeleteMapping(path = {"/{serial}"})
    public void delete(@PathVariable("serial") String serial) {
        articleWS.delete(serial);
    }

    @PostMapping
    public DtoArticle create(@RequestBody DtoArticle article) {
        return new DtoArticle(articleWS.create(article));
    }

    @PutMapping
    public DtoArticle update(@RequestBody DtoArticle article) {
        return new DtoArticle(articleWS.update(article));
    }

//    private static List<ArticleMock> createList() {
//        List<ArticleMock> tempArticleMocks = new ArrayList<>();
//        ArticleMock art1 = new ArticleMock();
//        art1.setId(1);
//        art1.setName("article1");
//        art1.setCategorie("categorie1");
//        art1.setDescription("description1");
//        art1.setPrix(3000);
//        art1.setQuantite(2);
//
//        ArticleMock art2 = new ArticleMock();
//        art2.setId(2);
//        art2.setName("article2");
//        art2.setCategorie("categorie2");
//        art2.setDescription("description2");
//        art2.setPrix(5000);
//        art2.setQuantite(5);
//        tempArticleMocks.add(art1);
//        tempArticleMocks.add(art2);
//        return tempArticleMocks;
//    }
}