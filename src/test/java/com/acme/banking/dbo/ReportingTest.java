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

    private static final String REPORT1 = "Bank\n" +
            "========\n" +
            "Branch # 1\n" +
            "------\n" +
            "Client # 1\n" +
            "### Account # 1: 1.0";
    private static final String REPORT2 = "Bank\n" +
            "========\n" +
            "Branch # 1\n" +
            "------\n" +
            "Client # 1\n" +
            "### Account # 1: 1.0\n" +
            "Client # 2\n" +
            "### Account # 2: 2.0";

    private static final String REPORT3 = "Bank\n" +
            "========\n" +
            "Branch # 1\n" +
            "------\n" +
            "Client # 1\n" +
            "### Account # 1: 1.0\n" +
            "Client # 2\n" +
            "### Account # 2: 2.0\n" +
            "Client # 2\n" +
            "### Account # 3: 3.0";

    @Mock
    Branch branch;
    @Mock
    Account account1;
    @Mock
    Client client1;
    @Mock
    Account account2;
    @Mock
    Account account3;
    @Mock
    Client client2;

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
        when(branch.getAccounts()).thenReturn(asList(account1));
        when(client1.getId()).thenReturn(1);
        when(account1.getClient()).thenReturn(client1);
        when(account1.getId()).thenReturn(1);
        when(account1.getAmount()).thenReturn(1.);
        String report = sut.getReport2(branch);
        System.out.println(report);
        assertEquals(REPORT1, report);
    }

    @Test
    public void shouldGetReport3() {
        Reporting sut = new Reporting();
        when(branch.getAccounts()).thenReturn(asList(account1, account2));
        when(client1.getId()).thenReturn(1);
        when(account1.getClient()).thenReturn(client1);
        when(account1.getId()).thenReturn(1);
        when(account1.getAmount()).thenReturn(1.);
        when(client2.getId()).thenReturn(2);
        when(account2.getClient()).thenReturn(client2);
        when(account2.getId()).thenReturn(2);
        when(account2.getAmount()).thenReturn(2.);
        String report = sut.getReport2(branch);
        System.out.println(report);
        assertEquals(REPORT2, report);
    }

    @Test
    public void shouldGetReport4() {
        Reporting sut = new Reporting();
        when(branch.getAccounts()).thenReturn(asList(account1, account2, account3));
        when(client1.getId()).thenReturn(1);
        when(account1.getClient()).thenReturn(client1);
        when(account1.getId()).thenReturn(1);
        when(account1.getAmount()).thenReturn(1.);
        when(client2.getId()).thenReturn(2);
        when(account2.getClient()).thenReturn(client2);
        when(account2.getId()).thenReturn(2);
        when(account2.getAmount()).thenReturn(2.);
        when(account3.getClient()).thenReturn(client2);
        when(account3.getId()).thenReturn(3);
        when(account3.getAmount()).thenReturn(3.);
        String report = sut.getReport2(branch);
        System.out.println(report);
        assertEquals(REPORT3, report);
    }
}
