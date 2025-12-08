package org.spring.democqrsaxon.shared.dtos;


import lombok.*;

import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OperationDTO {
    private Long id;
    private Instant date;
    private double amount;
    private OperationType type;
    private String accountId;
}