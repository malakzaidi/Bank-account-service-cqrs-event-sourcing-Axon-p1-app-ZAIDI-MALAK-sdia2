package org.spring.democqrsaxon.commons.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.spring.democqrsaxon.query.entities.Account;
import org.spring.democqrsaxon.query.entities.Operation;

import java.util.List;

    @AllArgsConstructor @Getter
    public class AccountStatement {
        private Account account;
        private List<Operation> operations;
    }

