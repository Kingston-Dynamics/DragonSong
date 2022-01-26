package com.kdyncs.dragonsong.api.web;

import com.kdyncs.dragonsong.api.service.account.Registration;
import com.kdyncs.dragonsong.api.service.account.RegistrationService;
import com.kdyncs.dragonsong.api.util.response.ResponseFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Tag(name = "Account")
@RestController
@RequestMapping("/account")
public class AccountController {

    // Spring Components
    private final ResponseFactory response;
    private final RegistrationService registrationService;

    // Logging
    private final Logger log = LogManager.getLogger();

    @Autowired
    public AccountController(ResponseFactory response, RegistrationService registrationService) {
        this.response = response;
        this.registrationService = registrationService;
    }

    @PostConstruct
    public void init() {
        log.info("Loading {}", this.getClass().getSimpleName());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Registration registration) {

        log.trace("Creating Account.");

        // Account must not exist to be created
        if (registrationService.exists(registration.getUsername()))
        {
            return response.buildResponse(HttpStatus.BAD_REQUEST, "Username Taken");
        }

        registrationService.createAccount(registration);

        // Tell user we created it.
        return response.buildResponse(HttpStatus.OK, "Account Registered");
    }

    /*
     * TODO: Fix This
     *
     * The implementation is super naive at the moment and only exists for testing purposes. In an ideal world I would
     * add a boatload of security checks but unfortunately I didn't get that far in this particular version of the
     * application. 
     */
    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Registration registration) {
        log.trace("Deleting Account.");

        // Account must exist to be deleted
        if (!registrationService.exists(registration.getUsername()))
        {
            return response.buildResponse(HttpStatus.NOT_FOUND, "Unknown Account");
        }

        registrationService.deleteAccount(registration);

        return response.buildResponse(HttpStatus.OK, "Account Deleted");
    }
}
