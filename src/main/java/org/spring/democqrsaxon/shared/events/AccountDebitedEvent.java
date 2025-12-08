package org.spring.democqrsaxon.shared.events;

public record AccountDebitedEvent(String accountId, double amount) {
}