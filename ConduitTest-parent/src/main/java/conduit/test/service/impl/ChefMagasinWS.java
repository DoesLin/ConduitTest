package conduit.test.service.impl;

import conduit.test.dto.DtoArticle;
import conduit.test.dto.DtoChefMagasin;
import conduit.test.dto.DtoVendeur;
import conduit.test.repository.AccountRepo;
import conduit.test.repository.ChefMagasinRepo;
import conduit.test.repository.PdgRepo;
import conduit.test.repository.VendeurRepo;
import conduit.test.repository.dao.*;
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

/**
 * Utilisation de stream pour la visualisation des chefs de magasin !
 */
@Service
public class ChefMagasinWS implements IWebService {

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private VendeurRepo vendeurRepo;
    @Autowired
    private ChefMagasinRepo chefMagasinRepo;
    @Autowired
    private PdgRepo pdgRepo;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public DaoChefMagasin create(Object object) throws Exception {
        try {
            DtoChefMagasin chefMagasin = (DtoChefMagasin) object;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) auth.getPrincipal();

            DaoAccount account = accountRepo.findByUsername(user.getUsername());
            if (account.getRole().compareTo("Pdg") != 0) {
                throw new Exception("Current user is not allowed!");
            }
            DaoPdg pdg = pdgRepo.findByUsername(user.getUsername());
            DaoChefMagasin newChefMagasin = new DaoChefMagasin();

            if (chefMagasin != null) {
                // Create account
                DaoAccount newUser = new DaoAccount();
                newUser.setUsername(chefMagasin.getUsername());
                newUser.setPassword(bcryptEncoder.encode(chefMagasin.getPassword()));
                newUser.setRole("ChefMagasin");
                newUser.setManagername(chefMagasin.getUsername());
                accountRepo.save(newUser);

                // Create chefMagasin
                newChefMagasin.setUsername(chefMagasin.getUsername());
                newChefMagasin.setListeVendeurs(new ArrayList<DaoVendeur>());
                newChefMagasin.setPdg(pdg);
                return chefMagasinRepo.save(newChefMagasin);
            } else {
                throw new Exception("Fail to create chefMagasin!");
            }
        } catch (Exception e) {
            if (e.getMessage().compareTo("Current user is not allowed!") == 0) {
                throw e;
            }
            throw new Exception("Fail to create chefMagasin!");
        }

    }

    @Override
    public DaoChefMagasin update(Object object) throws Exception {
        try {
            DtoChefMagasin chefMagasin = (DtoChefMagasin) object;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) auth.getPrincipal();

            DaoAccount account = accountRepo.findByUsername(user.getUsername());
            if (account.getRole().compareTo("Pdg") != 0) {
                throw new Exception("Current user is not allowed!");
            }

            List<DaoChefMagasin> daoChefMagasinList = this.getAlls();
            DaoChefMagasin newChefMagasin = null;
            for (DaoChefMagasin daoChefMagasin : daoChefMagasinList) {
                if (daoChefMagasin.getUsername().compareTo(chefMagasin.getUsername()) == 0) {
                    newChefMagasin = daoChefMagasin;
                }
            }
            if (newChefMagasin == null) {
                throw new Exception("Current user is not allowed!");
            }

            if (chefMagasin != null) {
                // Update account
                DaoAccount newUser = accountRepo.findByUsername(chefMagasin.getUsername());
                newUser.setPassword(bcryptEncoder.encode(chefMagasin.getPassword()));
                accountRepo.save(newUser);

                // Update chefMagasin
                newChefMagasin.setUsername(chefMagasin.getUsername());
                return chefMagasinRepo.save(newChefMagasin);
            } else {
                throw new Exception("Fail to update chefMagasin!");
            }
        } catch (Exception e) {
            if (e.getMessage().compareTo("Current user is not allowed!") == 0) {
                throw e;
            }
            throw new Exception("Fail to update chefMagasin!");
        }
    }

    @Override
    public DaoChefMagasin getByName(String name) throws Exception {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) auth.getPrincipal();
            DaoAccount account = accountRepo.findByUsername(user.getUsername());
            if (account.getRole().compareTo("Pdg") != 0) {
                throw new Exception("Current user is not allowed!");
            }

            DaoChefMagasin chefMagasin = chefMagasinRepo.findByUsername(name);
            if (chefMagasin == null) {
                throw new Exception("");
            }
            return chefMagasin;
        } catch (Exception e) {
            if (e.getMessage().compareTo("Current user is not allowed!") == 0) {
                throw e;
            }
            throw new Exception("Fail to get chefMagasin!");
        }
    }

    @Override
    @Transactional
    public void delete(String name) throws Exception {

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) auth.getPrincipal();
            DaoAccount account = accountRepo.findByUsername(user.getUsername());
            if (account.getRole().compareTo("Pdg") != 0) {
                throw new Exception("Current user is not allowed!");
            }

            List<DaoChefMagasin> daoChefMagasinList = this.getAlls();
            DaoChefMagasin newChefMagasin = null;
            for (DaoChefMagasin daoChefMagasin : daoChefMagasinList) {
                if (daoChefMagasin.getUsername().compareTo(name) == 0) {
                    newChefMagasin = daoChefMagasin;
                }
            }
            if (newChefMagasin == null) {
                throw new Exception("Current user is not allowed!");
            }

            DaoChefMagasin chefMagasin = chefMagasinRepo.findByUsername(name);
            if (chefMagasin != null) {
                // Delete account
                DaoAccount newUser = accountRepo.findByUsername(chefMagasin.getUsername());
                accountRepo.delete(newUser);

                // Delete chefMagasin
                chefMagasinRepo.deleteByUsername(chefMagasin.getUsername());
            } else {
                throw new Exception("Fail to delete chefMagasin!");
            }
        } catch (Exception e) {
            if (e.getMessage().compareTo("Current user is not allowed!") == 0) {
                throw e;
            }
            throw new Exception("Fail to delete chefMagasin!");
        }
    }

    @Override
    public List<DaoChefMagasin> getAlls() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        DaoAccount account = accountRepo.findByUsername(user.getUsername());

        if (account.getRole().compareTo("Vendeur") == 0) {
            throw new Exception("Current user is not allowed!");
        } else if (account.getRole().compareTo("ChefMagasin") == 0) {
            throw new Exception("Current user is not allowed!");
        } else if (account.getRole().compareTo("Pdg") == 0){
            return chefMagasinRepo.findAll();
        } else {
            throw new Exception("Current user is not allowed!");
        }
    }
}