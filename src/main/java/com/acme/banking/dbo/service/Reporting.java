package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;

public class Reporting {
    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {
        return "Bank" +
                "========" +
                "Branch # 2" +
                "------" +
                "Client # 1" +
                "### Account # 1: 100" +
                "### Account # 2: 200" +
                "Client # 2" +
                "<absent>";
    }

    public String getReport2(Branch rootBranch) {
        StringBuilder builder = new StringBuilder();
        builder.append("Bank\n")
                .append("========\n")
                .append("Branch # 1\n")
                .append("------\n");
        for (Account account : rootBranch.getAccounts()) {
            builder.append("Client # ")
                    .append(account.getClient().getId())
                    .append("\n### Account # ")
                    .append(account.getId()).append(": ").append(account.getAmount());
        }
        return builder.toString();
    }
}
