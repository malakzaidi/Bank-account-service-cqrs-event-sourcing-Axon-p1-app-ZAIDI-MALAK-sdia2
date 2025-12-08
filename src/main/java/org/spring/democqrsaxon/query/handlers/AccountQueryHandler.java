package org.spring.democqrsaxon.query.handlers;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.spring.democqrsaxon.query.dtos.AccountEvent;
import org.spring.democqrsaxon.query.entities.Account;
import org.spring.democqrsaxon.query.entities.Operation;
import org.spring.democqrsaxon.query.repository.AccountRepository;
import org.spring.democqrsaxon.query.repository.OperationRepository;
import org.spring.democqrsaxon.shared.dtos.AccountStatement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountQueryHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    public AccountQueryHandler(AccountRepository accountRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }
    @QueryHandler
    public List<Account> on(GetAllAccounts query){
        return accountRepository.findAll();
    }
    @QueryHandler
    public AccountStatement on(GetAccountStatement query){
        Account account = accountRepository.findById(query.getAccountId()).get();
        List<Operation> operations = operationRepository.findByAccountId(query.getAccountId());
        return new AccountStatement(account, operations);
    }

    @QueryHandler
    public AccountEvent on(WatchEventQuery query){
        return AccountEvent.builder().build();
    }


}