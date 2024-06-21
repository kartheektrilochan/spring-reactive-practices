package com.kkt.learning.service;

import com.kkt.learning.collections.Account;
import com.kkt.learning.repository.AccountCrudRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
@AllArgsConstructor
public class ReservationDataGenerator {

    private AccountCrudRepository reservationRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void ready() {
        log.info("Application is ready");
        Flux<String> names = Flux.just("Kartheek", "Devika");
        Flux<Account> reservationFLux = names.map(name -> new Account(null, "karthee",22.0));
        Flux<Account> result = reservationFLux.flatMap(i -> this.reservationRepository.save(i));

        this.reservationRepository
                .deleteAll()
                .thenMany(result)
                .thenMany(this.reservationRepository.findAll())
                .subscribe(i -> log.info("request={}", i));

    }
}
