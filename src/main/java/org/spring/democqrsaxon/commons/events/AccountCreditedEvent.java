package org.spring.democqrsaxon.commons.events;

public record AccountCreditedEvent(String accountId, double amount) {
}