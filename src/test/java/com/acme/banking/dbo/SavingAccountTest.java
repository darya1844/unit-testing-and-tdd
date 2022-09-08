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
        final int id = 1;
        final Client client = new Client(clientId, clientName);
        final double amount = 110;
        SavingAccount sut = new SavingAccount(id, client, amount);
        assertThat(sut, allOf(
                hasProperty("id", is(sut.getId())),
                hasProperty("client", is(sut.getClient())),
                hasProperty("amount", is(sut.getAmount()))));
    }

    @Test
    public void shouldNotStorePropertiesWhenCreatedWithNullId() {
        final int id = 0;
        final Client client = new Client(clientId, clientName);
        final double amount = 110;
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, amount));
    }

    @Test
    public void shouldNotStorePropertiesWhenCreatedWithNullClient() {
        final int id = 1;
        final Client client = null;
        final double amount = 110;
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, amount));
    }

    @Test
    public void shouldNotStorePropertiesWhenCreatedWithNullAmount() {
        final int id = 1;
        final Client client = new Client(clientId, clientName);
        final double amount = -110;
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, amount));
    }

    @Test
    public void shouldStoreClientWhenCreated() {
        final int id = 1;
        final Client client = new Client(clientId, clientName);
        final double amount = 110;
        SavingAccount sut = new SavingAccount(id, client, amount);
        assertThat(sut.getClient(), is(client));
    }
}
