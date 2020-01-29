package conduit.test.controller;

import conduit.test.repository.dao.DaoArticle;
import conduit.test.dto.DtoArticle;
import conduit.test.service.impl.ArticleWS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping({"/articles"})
public class ArticlesController {

    @Autowired
    private ArticleWS articleWS;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    private List<ArticleMock> articleMocks = createList();

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> display() {
        try {
            List<DtoArticle> listArticles = new ArrayList<DtoArticle>();

            List<DaoArticle> listDaoArticles = articleWS.getAlls();
            for (DaoArticle daoArticle : listDaoArticles) {
                DtoArticle article = new DtoArticle(daoArticle);
                listArticles.add(article);
            }

            return ResponseEntity.ok(listArticles);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.ok(null);
        }
    }

    @DeleteMapping(path = {"/{serial}"})
    public void delete(@PathVariable("serial") String serial) {
        try {
            articleWS.delete(serial);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DtoArticle article) {
        try {
            return ResponseEntity.ok(new DtoArticle(articleWS.create(article)));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.ok(null);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody DtoArticle article) {
        try {
            return ResponseEntity.ok(new DtoArticle(articleWS.update(article)));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.ok(null);
        }
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