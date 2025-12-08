package org.spring.democqrsaxon.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.commandhandling.CommandHandler;
import org.spring.democqrsaxon.command.aggregates.AccountAggregate;
import org.spring.democqrsaxon.command.commands.AddAccountCommand;
import org.spring.democqrsaxon.enums.AccountStatus;

@Getter
@AllArgsConstructor
public class AccountCreatedEvent {
    private String accountId;
    private double initialBalance;
    private AccountStatus status;
    private String currency;

}
