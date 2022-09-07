package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;

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
        assertAll("SavingAccount store its properties",
                () -> assertEquals(id, sut.getId()),
                () -> assertEquals(client, sut.getClient()),
                () -> assertEquals(amount, sut.getAmount()));
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
}
