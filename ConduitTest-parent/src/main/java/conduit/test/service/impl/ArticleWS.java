package conduit.test.service.impl;

import conduit.test.repository.AccountRepo;
import conduit.test.repository.ArticleRepo;
import conduit.test.repository.ChefMagasinRepo;
import conduit.test.repository.VendeurRepo;
import conduit.test.repository.dao.DaoAccount;
import conduit.test.repository.dao.DaoArticle;
import conduit.test.repository.dao.DaoChefMagasin;
import conduit.test.repository.dao.DaoVendeur;
import conduit.test.dto.DtoArticle;
import conduit.test.service.IWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ArticleWS implements IWebService {

    @Autowired
    private ArticleRepo articleRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private VendeurRepo vendeurRepo;
    @Autowired
    private ChefMagasinRepo chefMagasinRepo;

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

        newArticle.setVendeur(vendeurRepo.findByUsername(article.getVendeurName()));
        return articleRepo.save(newArticle);
    }

    @Override
    public DaoArticle update(Object object) {
        DtoArticle article = (DtoArticle) object;

        DaoArticle newArticle = articleRepo.findBySerial(article.getSerial());

        newArticle.setSerial(article.getSerial());
        newArticle.setName(article.getName());
        newArticle.setCategorie(article.getCategorie());
        newArticle.setDescription(article.getDescription());
        newArticle.setPrix(article.getPrix());
        newArticle.setQuantite(article.getQuantite());

//        DtoVendeur vendeur = article.getVendeur();
        newArticle.setVendeur(vendeurRepo.findByUsername(article.getVendeurName()));
        return articleRepo.save(newArticle);
    }

    @Override
    public DaoArticle getById(long id) {
        throw new NotImplementedException();
    }

    public DaoArticle getBySerial(String serial) {
        return articleRepo.findBySerial(serial);
    }

    @Override
    public void delete(long id) {
        throw new NotImplementedException();
    }

    public void delete(String serial) {
        DaoArticle article = articleRepo.findBySerial(serial);
        articleRepo.delete(article);
    }

    @Override
    public List<DaoArticle> getAlls() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        DaoAccount account = accountRepo.findByUsername(user.getUsername());

        if (account.getRole().compareTo("Vendeur") == 0) {
            DaoVendeur vendeur = vendeurRepo.findByUsername(user.getUsername());
            return articleRepo.findByVendeurId(vendeur.getId());
        } else if (account.getRole().compareTo("ChefMagasin") == 0) {
            DaoChefMagasin chefMagasin = chefMagasinRepo.findByUsername(user.getUsername());
            List<DaoVendeur> listVendeurs = chefMagasin.getListeVendeurs();
//            List<DaoArticle> listArticles = new ArrayList<>();
            List<List<DaoArticle>> listListArticles = new ArrayList<>();

            for (DaoVendeur vendeur : listVendeurs) {
                listListArticles.add(articleRepo.findByVendeurId(vendeur.getId()));
            }
//            listArticles = listListArticles.stream()
//                    .flatMap(List::stream)
//                    .collect(Collectors.toList());

            return listListArticles.stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
        } else {
            return articleRepo.findAll();
        }
    }
}