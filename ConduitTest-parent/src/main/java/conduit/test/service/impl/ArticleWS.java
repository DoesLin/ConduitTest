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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utilisation de stream pour la visualisation des articles !
 */
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
    public DaoArticle create(Object object) throws Exception {
        DtoArticle article = (DtoArticle) object;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        DaoAccount account = accountRepo.findByUsername(user.getUsername());
        if (account.getRole().compareTo("Vendeur") != 0) {
            throw new Exception("Current user is not allowed!");
        }
        DaoVendeur vendeur = vendeurRepo.findByUsername(user.getUsername());
        DaoArticle newArticle = new DaoArticle();

        if (article != null) {
            newArticle.setSerial(article.getSerial());
            newArticle.setName(article.getName());
            newArticle.setCategorie(article.getCategorie());
            newArticle.setDescription(article.getDescription());
            newArticle.setPrix(article.getPrix());
            newArticle.setQuantite(article.getQuantite());

            newArticle.setVendeur(vendeur);
            return articleRepo.save(newArticle);
        } else {
            throw new Exception("Fail to create article!");
        }
    }

    @Override
    public DaoArticle update(Object object) throws Exception {
        DtoArticle article = (DtoArticle) object;

        List<DaoArticle> daoArticleList = this.getAlls();
        DaoArticle newArticle = null;
        for (DaoArticle daoArticle1 : daoArticleList) {
            if (daoArticle1.getSerial().compareTo(article.getSerial()) == 0) {
                newArticle = daoArticle1;
            }
        }
        if (newArticle == null) {
            throw new Exception("Current user is not allowed!");
        }

        if (article != null) {
            newArticle.setSerial(article.getSerial());
            newArticle.setName(article.getName());
            newArticle.setCategorie(article.getCategorie());
            newArticle.setDescription(article.getDescription());
            newArticle.setPrix(article.getPrix());
            newArticle.setQuantite(article.getQuantite());

            return articleRepo.save(newArticle);
        } else {
            throw new Exception("Fail to update article!");
        }
    }

    @Override
    public DaoArticle getByName(String serial) throws Exception {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) auth.getPrincipal();
            DaoVendeur vendeur = vendeurRepo.findByUsername(user.getUsername());
            DaoArticle article = articleRepo.findByStatusAndVendeurId(serial, vendeur.getId());
            if (article == null) {
                throw new Exception();
            }
            return article;
        } catch (Exception e){
            throw new Exception("Fail to get article!");
        }
    }

    @Override
    @Transactional
    public void delete(String serial) throws Exception {

        List<DaoArticle> daoArticleList = this.getAlls();
        DaoArticle article = null;
        for (DaoArticle daoArticle : daoArticleList) {
            if (daoArticle.getSerial().compareTo(serial) == 0) {
                article = daoArticle;
            }
        }
        if (article == null) {
            throw new Exception("Current user is not allowed!");
        }
        if (article != null) {
            articleRepo.deleteBySerial(article.getSerial());
        } else {
            throw new Exception("Fail to delete article!");
        }
    }

    @Override
    public List<DaoArticle> getAlls() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        DaoAccount account = accountRepo.findByUsername(user.getUsername());

        if (account.getRole().compareTo("Vendeur") == 0) {
            DaoVendeur vendeur = vendeurRepo.findByUsername(user.getUsername());
            return vendeur.getListeArticles();
        } else if (account.getRole().compareTo("ChefMagasin") == 0) {
            DaoChefMagasin chefMagasin = chefMagasinRepo.findByUsername(user.getUsername());
            List<DaoVendeur> listVendeurs = chefMagasin.getListeVendeurs();
            List<List<DaoArticle>> listListArticles = new ArrayList<>();

            for (DaoVendeur vendeur : listVendeurs) {
                listListArticles.add(vendeur.getListeArticles());
            }

            return listListArticles.stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
        } else if (account.getRole().compareTo("Pdg") == 0){
            return articleRepo.findAll();
        } else {
            throw new Exception("Current user is not allowed!");
        }
    }
}