package com.kdyncs.dragonsong.api.service.account;

import com.kdyncs.dragonsong.database.schema.user.dao.AccountDAO;
import com.kdyncs.dragonsong.database.schema.user.model.AccountModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
public class RegistrationService {

    // Logging
    private static final Logger log = LogManager.getLogger();

    // Spring Components
    private final AccountDAO accounts;

    @Autowired
    public RegistrationService(AccountDAO accounts) {
        this.accounts = accounts;
    }

    public void createAccount(Registration registration)
    {
        AccountModel account = new AccountModel();

        account.setId(UUID.randomUUID());
        account.setUsername(registration.getUsername());
        // TODO: Use SecurePass here
        account.setPassword(registration.getPassword());
        account.setCreateTimestamp(Timestamp.from(Instant.now()));

        accounts.save(account);

    }

    public void deleteAccount(Registration registration)
    {
        // TODO: This is not Ideal; Simplify it.

        AccountModel account = accounts.findByUsername(registration.getUsername());

        accounts.delete(account);
    }

    public boolean exists(String username)
    {
        // Find the Account
        var account = accounts.findByUsername(username);

        // If the account isn't null then it exists
        return account != null;
    }
}
