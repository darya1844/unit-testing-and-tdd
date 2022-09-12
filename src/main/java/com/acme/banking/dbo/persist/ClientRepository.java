package com.acme.banking.dbo.persist;

import com.acme.banking.dbo.domain.Client;

public interface ClientRepository {
    Client findById(int clientId);
}
