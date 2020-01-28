package conduit.test.service;

import java.util.ArrayList;

import conduit.test.dao.IDaoAccount;
import conduit.test.dao.impl.DaoAccount;
import conduit.test.dto.DtoAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtAccountWS implements UserDetailsService {

    @Autowired
    private IDaoAccount iDaoAccount;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DaoAccount user = iDaoAccount.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public DaoAccount save(DtoAccount user) {
        DaoAccount newUser = new DaoAccount();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return iDaoAccount.save(newUser);
    }

}