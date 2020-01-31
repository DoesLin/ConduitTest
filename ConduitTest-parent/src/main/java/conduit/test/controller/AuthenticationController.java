package conduit.test.controller;

import conduit.test.controller.auth.JwtRequest;
import conduit.test.dto.DtoAccount;
import conduit.test.service.AccountDS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AccountDS accountDS;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        try {
            return ResponseEntity.ok(accountDS.createAuthenticationToken(authenticationRequest));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveAccount(@RequestBody DtoAccount account) throws Exception {
        try {
            return ResponseEntity.ok(accountDS.create(account));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
}
