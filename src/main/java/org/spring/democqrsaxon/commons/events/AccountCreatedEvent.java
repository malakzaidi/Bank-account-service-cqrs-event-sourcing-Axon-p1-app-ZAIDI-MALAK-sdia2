package org.spring.democqrsaxon.commons.events;

import org.spring.democqrsaxon.commons.enums.AccountStatus;

public record AccountCreatedEvent(String accountId, double initialBalance, String currency, AccountStatus accountStatus) {
}