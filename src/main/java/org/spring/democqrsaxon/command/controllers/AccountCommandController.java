package org.spring.democqrsaxon.command.controllers;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.spring.democqrsaxon.command.aggregates.AccountAggregate;
import org.spring.democqrsaxon.command.commands.CreateAccountCommand;
import org.spring.democqrsaxon.command.commands.CreditAccountCommand;
import org.spring.democqrsaxon.command.commands.DebitAccountCommand;
import org.spring.democqrsaxon.command.commands.UpdateAccountStatusCommand;
import org.spring.democqrsaxon.commons.dtos.CreateAccountDTO;
import org.spring.democqrsaxon.commons.dtos.CreditAccountDTO;
import org.spring.democqrsaxon.commons.dtos.DebitAccountDTO;
import org.spring.democqrsaxon.commons.dtos.UpdateAccountStatusDTO;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands/accounts")
public class AccountCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;
    private AccountAggregate accountAggregate;

    public AccountCommandController(CommandGateway commandGateway, EventStore eventStore, AccountAggregate accountAggregate) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
        this.accountAggregate = accountAggregate;
    }

    @PostMapping("/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountDTO request){
        CompletableFuture<String> result = this.commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                request.initialBalance(),
                request.currency()
        ));
        return result;
    }
    @PostMapping("/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountDTO request){
        CompletableFuture<String> result = this.commandGateway.send(new DebitAccountCommand(
                request.accountId(),
                request.amount()
        ));
        return result;
    }
    @PostMapping("/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountDTO request){
        CompletableFuture<String> result = this.commandGateway.send(new CreditAccountCommand(
                request.accountId(),
                request.amount()
        ));
        return result;
    }
    @PutMapping("/updateStatus")
    public CompletableFuture<String> updateStatus(@RequestBody UpdateAccountStatusDTO request){
        CompletableFuture<String> result = this.commandGateway.send(new UpdateAccountStatusCommand(
                request.accountId(),
                request.accountStatus()
        ));
        return result;
    }
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception  exception){
        return exception.getMessage();
    }

    @GetMapping("/events/{accountId}")
    public Stream eventStore(@PathVariable String accountId){
        return eventStore.readEvents(accountId).asStream();
    }
    @GetMapping("/accountAggregate")
    public AccountAggregate accountAggregate(){
        return accountAggregate;
    }
}
