package org.spring.democqrsaxon.command.aggregates;


import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.spring.democqrsaxon.command.commands.AddAccountCommand;
import org.spring.democqrsaxon.enums.AccountStatus;
import org.spring.democqrsaxon.events.AccountCreatedEvent;

@Aggregate
@Slf4j
public class AccountAggregate {
    private String accountId;
    private double balance;
    private AccountStatus status;

    public AccountAggregate() {}


    @CommandHandler
    public AccountAggregate(AddAccountCommand command) {
        log.info("[CommandHandler] Received AddAccountCommand");
        if (command.getInitialBalance()<=0) throw new IllegalArgumentException("initial balance must be positive");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getInitialBalance(),
                AccountStatus.CREATED,
                command.getCurrency()
        ));

    }
    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        log.info("[EventSourcingHandler] Received AccountCreatedEvent");
        this.accountId = event.getAccountId();
        this.balance = event.getInitialBalance();
        this.status = event.getStatus();
    }
}
