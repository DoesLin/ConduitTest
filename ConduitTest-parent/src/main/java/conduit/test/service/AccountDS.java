package conduit.test.service;

import java.util.ArrayList;

import conduit.test.dao.IDaoAccount;
import conduit.test.dao.IDaoChefMagasin;
import conduit.test.dao.IDaoVendeur;
import conduit.test.dao.impl.DaoAccount;
import conduit.test.dao.impl.DaoChefMagasin;
import conduit.test.dao.impl.DaoVendeur;
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
    private IDaoAccount iDaoAccount;
    @Autowired
    private IDaoVendeur iDaoVendeur;
    @Autowired
    private IDaoChefMagasin iDaoChefMagasin;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DaoAccount user = iDaoAccount.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Account not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public DaoAccount save(DtoAccount user) {
        DaoAccount newUser = new DaoAccount();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setRole(user.getRole());
        newUser.setManagername(user.getManagername());

        if (user.getRole().compareTo("Vendeur") == 0) {
            DaoVendeur newVendeur = new DaoVendeur();
            newVendeur.setUsername(user.getUsername());
            DaoChefMagasin chefMagasin = iDaoChefMagasin.findByUsername(user.getManagername());
            if (iDaoChefMagasin.findByUsername(user.getManagername()) == null) {
                throw new UsernameNotFoundException("ChefMagasin not found with username: " + user.getManagername());
            }
            newVendeur.setChefMagasin(chefMagasin);
            iDaoVendeur.save(newVendeur);
        } else if (user.getRole().compareTo("ChefMagasin") == 0) {
            DaoChefMagasin newChefMagasin = new DaoChefMagasin();
            newChefMagasin.setUsername(user.getUsername());
            iDaoChefMagasin.save(newChefMagasin);
        }

        return iDaoAccount.save(newUser);
    }

}