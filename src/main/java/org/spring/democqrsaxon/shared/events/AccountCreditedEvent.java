package org.spring.democqrsaxon.shared.events;

public record AccountCreditedEvent(String accountId, double amount) {
}