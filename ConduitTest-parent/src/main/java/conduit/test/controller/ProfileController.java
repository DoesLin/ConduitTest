package conduit.test.controller;

import conduit.test.dao.IDaoAccount;
import conduit.test.dao.impl.DaoAccount;
import conduit.test.dto.DtoAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @Autowired
    private IDaoAccount iDaoAccount;

    @RequestMapping({ "/profile" })
    public String profile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        DaoAccount account = iDaoAccount.findByUsername(user.getUsername());
        return account.toString();
    }

}
