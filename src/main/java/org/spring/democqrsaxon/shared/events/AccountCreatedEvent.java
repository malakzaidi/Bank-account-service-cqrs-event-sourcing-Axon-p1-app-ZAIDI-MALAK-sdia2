package org.spring.democqrsaxon.shared.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.spring.democqrsaxon.shared.enums.AccountStatus;

@Getter
@AllArgsConstructor
public class AccountCreatedEvent {
    private String accountId;
    private double initialBalance;
    private AccountStatus status;
    private String currency;

}
