package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.persist.AccountRepostory;
import com.acme.banking.dbo.persist.ClientRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProcessingTest {

    private String clientName;
    private int clientId;
    @Mock
    private Client client;
    @Mock
    private Account accountTo;
    @Mock
    private Account accountFrom;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    Cash cash;
    @Mock
    ClientRepository clientRepository;
    @Mock
    AccountRepostory accountRepostory;
    @InjectMocks
    Processing sut;

    @BeforeEach
    public void setUp() {
        clientName = "Dummy name";
        clientId = 1;
    }

    @Test
    public void shouldCallCashMethod() {
        sut.cash(1.0, 1);

        verify(cash, times(1)).log(1.0, 1);
    }

    @Test
    public void shouldCreateClient() {
        sut.createClient(clientId, clientName);
        verify(clientRepository).save(any(Client.class));
    }

    @Test
    public void shouldReturnAccountByClient() {
        when(clientRepository.findById(clientId)).thenReturn(client);
        when(client.getAccounts()).thenReturn(asList(accountTo));
        assertThat(sut.getAccountsByClientId(clientId), allOf(hasItem(is(accountTo))));
    }

    @Test
    public void shouldSaveTransaction() {
        int accountIdTo = 1;
        int accountIdFrom = 2;
        double amountToTransfer = 1.;
        when(accountRepostory.findById(accountIdTo)).thenReturn(accountTo);
        when(accountRepostory.findById(accountIdFrom)).thenReturn(accountFrom);
        when(accountFrom.getAmount()).thenReturn(1.);
        when(accountTo.getAmount()).thenReturn(2.);
        sut.transfer(accountIdFrom, accountIdTo, amountToTransfer);
        verify(accountFrom).setAmount(0);
        verify(accountTo).setAmount(3.);
        verify(accountRepostory).save(accountFrom);
        verify(accountRepostory).save(accountTo);
    }
}
