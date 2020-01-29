package conduit.test.service;

import java.util.ArrayList;

import conduit.test.repository.AccountRepo;
import conduit.test.repository.ChefMagasinRepo;
import conduit.test.repository.VendeurRepo;
import conduit.test.repository.dao.DaoAccount;
import conduit.test.repository.dao.DaoChefMagasin;
import conduit.test.repository.dao.DaoVendeur;
import conduit.test.dto.DtoAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountDS implements UserDetailsService {

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private VendeurRepo vendeurRepo;
    @Autowired
    private ChefMagasinRepo chefMagasinRepo;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DaoAccount user = accountRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Account not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public DaoAccount create(DtoAccount user) throws Exception {
        DaoAccount newUser = new DaoAccount();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setRole(user.getRole());
        newUser.setManagername(user.getManagername());

        if (user.getRole().compareTo("Vendeur") == 0) {
            DaoVendeur newVendeur = new DaoVendeur();
            newVendeur.setUsername(user.getUsername());
            DaoChefMagasin chefMagasin = chefMagasinRepo.findByUsername(user.getManagername());
            if (chefMagasinRepo.findByUsername(user.getManagername()) == null) {
                throw new UsernameNotFoundException("ChefMagasin not found with username: " + user.getManagername());
            }
            newVendeur.setChefMagasin(chefMagasin);
            vendeurRepo.save(newVendeur);
        } else if (user.getRole().compareTo("ChefMagasin") == 0) {
            DaoChefMagasin newChefMagasin = new DaoChefMagasin();
            newChefMagasin.setUsername(user.getUsername());
            chefMagasinRepo.save(newChefMagasin);
        }

        return accountRepo.save(newUser);
    }

}