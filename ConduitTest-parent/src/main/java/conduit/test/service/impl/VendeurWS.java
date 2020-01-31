package conduit.test.service.impl;

import conduit.test.dto.DtoArticle;
import conduit.test.dto.DtoVendeur;
import conduit.test.repository.AccountRepo;
import conduit.test.repository.ArticleRepo;
import conduit.test.repository.ChefMagasinRepo;
import conduit.test.repository.VendeurRepo;
import conduit.test.repository.dao.DaoAccount;
import conduit.test.repository.dao.DaoArticle;
import conduit.test.repository.dao.DaoChefMagasin;
import conduit.test.repository.dao.DaoVendeur;
import conduit.test.service.IWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utilisation de stream pour la visualisation des vendeurs !
 */
@Service
public class VendeurWS implements IWebService {

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private VendeurRepo vendeurRepo;
    @Autowired
    private ChefMagasinRepo chefMagasinRepo;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public DaoVendeur create(Object object) throws Exception {
        try {
            DtoVendeur vendeur = (DtoVendeur) object;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) auth.getPrincipal();

            DaoAccount account = accountRepo.findByUsername(user.getUsername());
            if (account.getRole().compareTo("ChefMagasin") != 0) {
                throw new Exception("Current user is not allowed!");
            }
            DaoChefMagasin chefMagasin = chefMagasinRepo.findByUsername(user.getUsername());
            DaoVendeur newVendeur = new DaoVendeur();

            if (vendeur != null) {
                // Create account
                DaoAccount newUser = new DaoAccount();
                newUser.setUsername(vendeur.getUsername());
                newUser.setPassword(bcryptEncoder.encode(vendeur.getPassword()));
                newUser.setRole("Vendeur");
//            newUser.setRole(vendeur.getRole());
                newUser.setManagername(chefMagasin.getUsername());
                accountRepo.save(newUser);

                // Create vendeur
                newVendeur.setUsername(vendeur.getUsername());
                newVendeur.setListeArticles(new ArrayList<DaoArticle>());
                newVendeur.setChefMagasin(chefMagasin);
                return vendeurRepo.save(newVendeur);
            } else {
                throw new Exception("Fail to create vendeur!");
            }
        } catch (Exception e) {
            throw new Exception("Fail to create vendeur!");
        }

    }

    @Override
    public DaoVendeur update(Object object) throws Exception {
        try {
            DtoVendeur vendeur = (DtoVendeur) object;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) auth.getPrincipal();

            DaoAccount account = accountRepo.findByUsername(user.getUsername());
            if (account.getRole().compareTo("ChefMagasin") != 0) {
                throw new Exception("Current user is not allowed!");
            }
            DaoChefMagasin chefMagasin = chefMagasinRepo.findByUsername(user.getUsername());
            DaoVendeur newVendeur = vendeurRepo.findByUsername(vendeur.getUsername());

            if (vendeur != null) {
                // Update account
                DaoAccount newUser = accountRepo.findByUsername(vendeur.getUsername());
//        newUser.setUsername(vendeur.getUsername());
                newUser.setPassword(bcryptEncoder.encode(vendeur.getPassword()));
//        newUser.setRole(vendeur.getRole());
                newUser.setManagername(chefMagasin.getUsername());
                accountRepo.save(newUser);

                // Update vendeur
                newVendeur.setUsername(vendeur.getUsername());
                List<DaoArticle> listeArticles = new ArrayList<>();
                List<DtoArticle> listDtoArticles = vendeur.getListeArticles();
                for (DtoArticle article : listDtoArticles) {
                    DaoArticle newArticle = new DaoArticle();
                    newArticle.setSerial(article.getSerial());
                    newArticle.setName(article.getName());
                    newArticle.setCategorie(article.getCategorie());
                    newArticle.setDescription(article.getDescription());
                    newArticle.setPrix(article.getPrix());
                    newArticle.setQuantite(article.getQuantite());

                    newArticle.setVendeur(newVendeur);
                    listeArticles.add(newArticle);
                }
                newVendeur.setListeArticles(listeArticles);
                newVendeur.setChefMagasin(chefMagasin);
                return vendeurRepo.save(newVendeur);
            } else {
                throw new Exception("Fail to update vendeur!");
            }
        } catch (Exception e) {
            throw new Exception("Fail to update vendeur!");
        }
    }

    @Override
    public DaoVendeur getByName(String name) throws Exception {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) auth.getPrincipal();
            DaoAccount account = accountRepo.findByUsername(user.getUsername());
            if (account.getRole().compareTo("ChefMagasin") != 0) {
                throw new Exception("Current user is not allowed!");
            }
            DaoChefMagasin chefMagasin = chefMagasinRepo.findByUsername(user.getUsername());

            return vendeurRepo.findByUsernameAndChefMagasinId(name, chefMagasin.getId());
        } catch (Exception e) {
            throw new Exception("Fail to get vendeur!");
        }
    }

    @Override
    public void delete(String name) throws Exception {

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) auth.getPrincipal();
            DaoAccount account = accountRepo.findByUsername(user.getUsername());
            if (account.getRole().compareTo("ChefMagasin") != 0) {
                throw new Exception("Current user is not allowed!");
            }
            DaoChefMagasin chefMagasin = chefMagasinRepo.findByUsername(user.getUsername());

            DaoVendeur vendeur = vendeurRepo.findByUsernameAndChefMagasinId(name, chefMagasin.getId());
            if (vendeur != null) {
                // Delete account
                DaoAccount newUser = accountRepo.findByUsername(vendeur.getUsername());
                accountRepo.delete(newUser);

                // Delete vendeur
                vendeurRepo.delete(vendeur);
            } else {
                throw new Exception("Fail to delete vendeur!");
            }
        } catch (Exception e) {
            throw new Exception("Fail to delete vendeur!");
        }
    }

    @Override
    public List<DaoVendeur> getAlls() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        DaoAccount account = accountRepo.findByUsername(user.getUsername());

        if (account.getRole().compareTo("Vendeur") == 0) {
            throw new Exception("Current user is not allowed!");
        } else if (account.getRole().compareTo("ChefMagasin") == 0) {
            DaoChefMagasin chefMagasin = chefMagasinRepo.findByUsername(user.getUsername());
            return chefMagasin.getListeVendeurs();
        } else {
            return vendeurRepo.findAll();
        }
    }
}