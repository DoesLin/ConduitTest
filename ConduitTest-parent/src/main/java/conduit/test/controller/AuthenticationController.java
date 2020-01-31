package conduit.test.controller;

import conduit.test.config.JwtTokenUtil;
import conduit.test.controller.auth.JwtRequest;
import conduit.test.controller.auth.JwtResponse;
import conduit.test.dto.DtoAccount;
import conduit.test.repository.AccountRepo;
import conduit.test.repository.dao.DaoAccount;
import conduit.test.service.AccountDS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AccountDS accountDS;
    @Autowired
    private AccountRepo accountRepo;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            final UserDetails userDetails = accountDS
                    .loadUserByUsername(authenticationRequest.getUsername());

            DaoAccount user = accountRepo.findByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(token, user.getRole()));
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

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED");
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS");
        }
    }
}
