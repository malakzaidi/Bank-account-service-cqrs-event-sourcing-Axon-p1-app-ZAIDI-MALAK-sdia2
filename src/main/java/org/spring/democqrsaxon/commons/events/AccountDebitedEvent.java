package org.spring.democqrsaxon.commons.events;

public record AccountDebitedEvent(String accountId, double amount) {
}