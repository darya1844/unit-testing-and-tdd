package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.persist.AccountRepostory;
import com.acme.banking.dbo.persist.ClientRepository;

import java.util.Collection;

public class Processing {
    private final ClientRepository clientRepository;
    private final AccountRepostory accountRepostory;

    private final Cash cash;

    public Processing(ClientRepository clientRepository, AccountRepostory accountRepostory, Cash cash) {
        this.clientRepository = clientRepository;
        this.accountRepostory = accountRepostory;
        this.cash = cash;
    }

    public Client createClient(int id, String name) {
        Client client = new Client(id, name);
        clientRepository.save(client);
        return client;
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        Client client = clientRepository.findById(clientId);
        return client.getAccounts();
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        Account from = accountRepostory.findById(fromAccountId);
        Account to = accountRepostory.findById(toAccountId);

        from.setAmount(from.getAmount() - amount);
        to.setAmount(to.getAmount() + amount);

        accountRepostory.save(from);
        accountRepostory.save(to);
    }

    public void cash(double amount, int fromAccountId) {
        cash.log(amount, fromAccountId);
    }
}
