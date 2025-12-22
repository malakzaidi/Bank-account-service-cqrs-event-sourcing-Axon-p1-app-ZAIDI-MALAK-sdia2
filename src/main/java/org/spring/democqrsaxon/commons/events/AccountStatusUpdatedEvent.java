package org.spring.democqrsaxon.commons.events;

import org.spring.democqrsaxon.commons.enums.AccountStatus;

public record AccountStatusUpdatedEvent(String accountId, AccountStatus fromStatus, AccountStatus toStatus) {
}