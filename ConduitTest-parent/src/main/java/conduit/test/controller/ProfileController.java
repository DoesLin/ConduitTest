package conduit.test.controller;

import conduit.test.repository.AccountRepo;
import conduit.test.repository.dao.DaoAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @Autowired
    private AccountRepo accountRepo;

    @RequestMapping({ "/profile" })
    public String profile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        DaoAccount account = accountRepo.findByUsername(user.getUsername());
        return account.toString();
    }

}
