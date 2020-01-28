package conduit.test.service.impl;

import conduit.test.dao.IDaoArticle;
import conduit.test.dao.IDaoVendeur;
import conduit.test.dao.impl.DaoArticle;
import conduit.test.dto.DtoArticle;
import conduit.test.dto.DtoVendeur;
import conduit.test.service.IWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Service
public class ArticleWS implements IWebService {

    @Autowired
    private IDaoArticle iDaoArticle;
    @Autowired
    private IDaoVendeur iDaoVendeur;

    @Override
    public DaoArticle create(Object object) {
        DtoArticle article = (DtoArticle) object;

        DaoArticle newArticle = new DaoArticle();
        newArticle.setSerial(article.getSerial());
        newArticle.setName(article.getName());
        newArticle.setCategorie(article.getCategorie());
        newArticle.setDescription(article.getDescription());
        newArticle.setPrix(article.getPrix());
        newArticle.setQuantite(article.getQuantite());

        newArticle.setVendeur(iDaoVendeur.findByUsername(article.getVendeurName()));
        return iDaoArticle.save(newArticle);
    }

    @Override
    public DaoArticle update(Object object) {
        DtoArticle article = (DtoArticle) object;

        DaoArticle newArticle = iDaoArticle.findBySerial(article.getSerial());

        newArticle.setSerial(article.getSerial());
        newArticle.setName(article.getName());
        newArticle.setCategorie(article.getCategorie());
        newArticle.setDescription(article.getDescription());
        newArticle.setPrix(article.getPrix());
        newArticle.setQuantite(article.getQuantite());

//        DtoVendeur vendeur = article.getVendeur();
        newArticle.setVendeur(iDaoVendeur.findByUsername(article.getVendeurName()));
        return iDaoArticle.save(newArticle);
    }

    @Override
    public DaoArticle getById(long id) {
        throw new NotImplementedException();
    }

    public DaoArticle getBySerial(String serial) {
        return iDaoArticle.findBySerial(serial);
    }

    @Override
    public void delete(long id) {
        throw new NotImplementedException();
    }

    public void delete(String serial) {
        DaoArticle article = iDaoArticle.findBySerial(serial);
        iDaoArticle.delete(article);
    }

    @Override
    public List<DaoArticle> getAlls() {
        return iDaoArticle.findAll();
    }
}