package org.spring.democqrsaxon.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

    @AllArgsConstructor @Getter
    public class AccountStatement {
        private Account account;
        private List<Operation> operations;
    }

