package org.spring.democqrsaxon.shared.events;

import org.spring.democqrsaxon.shared.enums.AccountStatus;

public record AccountStatusUpdatedEvent(String accountId, AccountStatus fromStatus, AccountStatus toStatus) {
}