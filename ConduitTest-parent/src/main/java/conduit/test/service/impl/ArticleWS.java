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

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        DaoAccount account = accountRepo.findByUsername(user.getUsername());
        if (account.getRole().compareTo("Vendeur") != 0) {
            throw new Exception("Current user is not allowed!");
        }
        DaoVendeur vendeur = vendeurRepo.findByUsername(user.getUsername());
        DaoArticle newArticle = articleRepo.findByStatusAndVendeurId(article.getSerial(), vendeur.getId());

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
    public DaoArticle getById(long id) {
        throw new UnsupportedOperationException();
    }

    public DaoArticle getBySerial(String serial) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        DaoVendeur vendeur = vendeurRepo.findByUsername(user.getUsername());
        return articleRepo.findByStatusAndVendeurId(serial, vendeur.getId());
    }

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException();
    }

    public void delete(String serial) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        DaoVendeur vendeur = vendeurRepo.findByUsername(user.getUsername());
        DaoArticle article = articleRepo.findByStatusAndVendeurId(serial, vendeur.getId());

        if (article != null) {
            articleRepo.delete(article);
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
        } else {
            return articleRepo.findAll();
        }
    }
}