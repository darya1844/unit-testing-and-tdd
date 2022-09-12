package com.acme.banking.dbo.persist;

import com.acme.banking.dbo.domain.Account;

public interface AccountRepostory {
    Account findById(int fromAccountId);

    void save(Account from);
}
