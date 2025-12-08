package org.spring.democqrsaxon.shared.dtos;


import org.spring.democqrsaxon.shared.enums.AccountStatus;

public record UpdateAccountStatusDTO(String accountId, AccountStatus accountStatus) {
}