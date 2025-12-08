package org.spring.democqrsaxon.command.aggregates;


import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.spring.democqrsaxon.command.commands.AddAccountCommand;
import org.spring.democqrsaxon.command.commands.CreditAccountCommand;
import org.spring.democqrsaxon.command.commands.DebitAccountCommand;
import org.spring.democqrsaxon.command.commands.UpdateAccountStatusCommand;
import org.spring.democqrsaxon.shared.enums.AccountStatus;
import org.spring.democqrsaxon.shared.events.AccountCreatedEvent;
import org.spring.democqrsaxon.shared.events.AccountCreditedEvent;
import org.spring.democqrsaxon.shared.events.AccountDebitedEvent;
import org.spring.democqrsaxon.shared.events.AccountStatusUpdatedEvent;

@Aggregate
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
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

    @CommandHandler
    public void handleCommand(DebitAccountCommand command){
        log.info("DebitAccountCommand Command Received");
        if (!this.getAmount().equals(AccountStatus.ACTIVATED)) throw  new RuntimeException("This account can not be debited because of the account is not activated. The current status is "+status);
        if (command.getAmount()>currentBalance) throw  new RuntimeException("Balance not sufficient exception");
        AggregateLifecycle.apply(new AccountDebitedEvent(
                command.getId(),
                command.getAmount()
        ));
    }
    @CommandHandler
    public void handleCommand(CreditAccountCommand command){
        log.info("CreditAccountCommand Command Received");
        if (!this.getStatus().equals(AccountStatus.ACTIVATED)) throw  new RuntimeException("This account can not be debited because of the account is not activated. The current status is "+status);
        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                command.getAmount()
        ));
    }
    @CommandHandler
    public void handleCommand(UpdateAccountStatusCommand command){
        log.info("UpdateAccountStatusCommand Command Received");
        if (this.getStatus().equals(command.getAccountStatus())) throw  new RuntimeException("This account is already the "+status+ " state");
        AggregateLifecycle.apply(new AccountStatusUpdatedEvent(
                command.getId(),
                status,
                command.getAccountStatus()
        ));
    }
    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        log.info("[EventSourcingHandler] Received AccountCreatedEvent");
        this.accountId = event.getAccountId();
        this.balance = event.getInitialBalance();
        this.status = event.getStatus();
    }
    @EventSourcingHandler
    //@EventHandler
    public void on(AccountDebitedEvent event){
        log.info("AccountDebitedEvent occured");
        this.accountId =event.accountId();
        this.currentBalance = this.currentBalance - event.amount();
    }
    @EventSourcingHandler
    //@EventHandler
    public void on(AccountCreditedEvent event){
        log.info("AccountCreditedEvent occured");
        this.accountId =event.accountId();
        this.currentBalance = this.currentBalance + event.amount();
    }

    @EventSourcingHandler
    //@EventHandler
    public void on(AccountStatusUpdatedEvent event){
        log.info("AccountStatusUpdatedEvent occured");
        this.accountId =event.accountId();
        this.status = event.toStatus();
    }


}
