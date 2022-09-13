package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.service.Reporting;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportingTest {

    @Mock
    Branch branch;
    @Mock
    Account account;
    @Mock
    Client client;

    @Test
    public void shouldGetReport() {
        Reporting sut = new Reporting();
        assertEquals(
                "Bank" +
                        "========" +
                        "Branch # 2" +
                        "------" +
                        "Client # 1" +
                        "### Account # 1: 100" +
                        "### Account # 2: 200" +
                        "Client # 2" +
                        "<absent>",
                sut.getReport(branch));
    }

    @Test
    public void shouldGetReport2() {
        Reporting sut = new Reporting();
        when(branch.getAccounts()).thenReturn(asList(account));
        when(client.getId()).thenReturn(1);
        when(account.getClient()).thenReturn(client);
        when(account.getId()).thenReturn(1);
        when(account.getAmount()).thenReturn(1.);
        String s = sut.getReport2(branch);
        System.out.println(s);
    }
}
