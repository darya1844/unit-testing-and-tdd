package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class SavingAccountTest {

    private final static int clientId = 1;
    private final static String clientName = "dummy client name";

    @Test
    public void shouldStorePropertiesWhenCreated() {
        final int accountId = 1;
        final Client client = new Client(clientId, clientName);
        final double amount = 1;
        SavingAccount sut = new SavingAccount(accountId, client, amount);
        assertThat(sut, allOf(
                hasProperty("id", is(sut.getId())),
                hasProperty("client", is(sut.getClient())),
                hasProperty("amount", is(sut.getAmount()))));
    }

    @Test
    public void shouldNotStorePropertiesWhenCreatedWithNullId() {
        final int accountId = 0;
        final Client client = new Client(clientId, clientName);
        final double amount = 1;
        assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(accountId, client, amount));
    }

    @Test
    public void shouldNotStorePropertiesWhenCreatedWithNullClient() {
        final int accountId = 1;
        final Client client = null;
        final double amount = 1;
        assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(accountId, client, amount));
    }

    @Test
    public void shouldNotStorePropertiesWhenCreatedWithNullAmount() {
        final int accountId = 1;
        final Client client = new Client(clientId, clientName);
        final double amount = -1;
        assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(accountId, client, amount));
    }

    @Test
    public void shouldStoreClientWhenCreated() { //consistency
        final int accountId = 1;
        final Client client = new Client(clientId, clientName);
        final double amount = 110;
        SavingAccount sut = new SavingAccount(accountId, client, amount);
        assertThat(sut.getClient(), is(client));
        //проверить у клиента счет
    }
}
