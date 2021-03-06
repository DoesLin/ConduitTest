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
import org.springframework.transaction.annotation.Transactional;

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
            if (e.getMessage().compareTo("Current user is not allowed!") == 0) {
                throw e;
            }
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
            if (account.getRole().compareTo("ChefMagasin") != 0 &&
                    account.getRole().compareTo("Pdg") != 0) {
                throw new Exception("Current user is not allowed!");
            }

            List<DaoVendeur> daoVendeurList = this.getAlls();
            DaoVendeur newVendeur = null;
            for (DaoVendeur daoVendeur : daoVendeurList) {
                if (daoVendeur.getUsername().compareTo(vendeur.getUsername()) == 0) {
                    newVendeur = daoVendeur;
                }
            }
            if (newVendeur == null) {
                throw new Exception("Current user is not allowed!");
            }

            if (vendeur != null) {
                // Update account
                DaoAccount newUser = accountRepo.findByUsername(vendeur.getUsername());
                newUser.setPassword(bcryptEncoder.encode(vendeur.getPassword()));
                accountRepo.save(newUser);

                // Update vendeur
                newVendeur.setUsername(vendeur.getUsername());
                return vendeurRepo.save(newVendeur);
            } else {
                throw new Exception("Fail to update vendeur!");
            }
        } catch (Exception e) {
            if (e.getMessage().compareTo("Current user is not allowed!") == 0) {
                throw e;
            }
            throw new Exception("Fail to update vendeur!");
        }
    }

    @Override
    public DaoVendeur getByName(String name) throws Exception {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) auth.getPrincipal();
            DaoAccount account = accountRepo.findByUsername(user.getUsername());
            if (account.getRole().compareTo("ChefMagasin") != 0 &&
                    account.getRole().compareTo("Pdg") != 0) {
                throw new Exception("Current user is not allowed!");
            }

            List<DaoVendeur> daoVendeurList = this.getAlls();
            DaoVendeur vendeur = null;
            for (DaoVendeur daoVendeur : daoVendeurList) {
                if (daoVendeur.getUsername().compareTo(name) == 0) {
                    vendeur = daoVendeur;
                }
            }
            if (vendeur == null) {
                throw new Exception("Current user is not allowed!");
            }
//            DaoChefMagasin chefMagasin = chefMagasinRepo.findByUsername(user.getUsername());
//
//            DaoVendeur vendeur = vendeurRepo.findByUsernameAndChefMagasinId(name, chefMagasin.getId());
//            if (vendeur == null) {
//                throw new Exception("");
//            }
            return vendeur;
        } catch (Exception e) {
            if (e.getMessage().compareTo("Current user is not allowed!") == 0) {
                throw e;
            }
            throw new Exception("Fail to get vendeur!");
        }
    }

    @Override
    @Transactional
    public void delete(String name) throws Exception {

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) auth.getPrincipal();
            DaoAccount account = accountRepo.findByUsername(user.getUsername());
            if (account.getRole().compareTo("ChefMagasin") != 0 &&
                    account.getRole().compareTo("Pdg") != 0) {
                throw new Exception("Current user is not allowed!");
            }
//            DaoChefMagasin chefMagasin = chefMagasinRepo.findByUsername(user.getUsername());

            List<DaoVendeur> daoVendeurList = this.getAlls();
            DaoVendeur vendeur = null;
            for (DaoVendeur daoVendeur : daoVendeurList) {
                if (daoVendeur.getUsername().compareTo(name) == 0) {
                    vendeur = daoVendeur;
                }
            }
            if (vendeur == null) {
                throw new Exception("Current user is not allowed!");
            }

//            DaoVendeur vendeur = vendeurRepo.findByUsernameAndChefMagasinId(name, chefMagasin.getId());
            if (vendeur != null) {
                // Delete account
                DaoAccount newUser = accountRepo.findByUsername(vendeur.getUsername());
                accountRepo.delete(newUser);

                // Delete vendeur
                vendeurRepo.deleteByUsername(vendeur.getUsername());
            } else {
                throw new Exception("Fail to delete vendeur!");
            }
        } catch (Exception e) {
            if (e.getMessage().compareTo("Current user is not allowed!") == 0) {
                throw e;
            }
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
        } else if (account.getRole().compareTo("Pdg") == 0) {
            return vendeurRepo.findAll();
        } else {
            throw new Exception("Current user is not allowed!");
        }
    }
}