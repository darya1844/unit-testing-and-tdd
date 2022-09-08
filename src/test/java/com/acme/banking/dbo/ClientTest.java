package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite")
public class ClientTest {

    @Test
    public void shouldStorePropertiesWhenCreated() {
        final int clientId = 1;
        final String clientName = "dummy client name";
        Client sut = new Client(clientId, clientName);
        assertThat(sut, allOf(
                hasProperty("id", is(sut.getId())),
                hasProperty("name", is(sut.getName()))));
    }

    @Test
    public void shouldNotStorePropertiesWhenCreatedWithNullId() {
        final int clientId = 0;
        final String clientName = "dummy client name";
        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));
    }

    @Test
    public void shouldNotStorePropertiesWhenCreatedWithNullName() {
        final int clientId = 1;
        final String clientName = null;
        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));
    }
}
