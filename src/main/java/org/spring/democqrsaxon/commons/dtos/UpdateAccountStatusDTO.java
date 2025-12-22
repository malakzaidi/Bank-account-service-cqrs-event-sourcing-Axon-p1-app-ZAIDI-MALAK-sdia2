package org.spring.democqrsaxon.commons.dtos;


import org.spring.democqrsaxon.commons.enums.AccountStatus;

public record UpdateAccountStatusDTO(String accountId, AccountStatus accountStatus) {
}