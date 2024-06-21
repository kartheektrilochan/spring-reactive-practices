package com.kkt.learning.repository;

import com.kkt.learning.collections.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends ReactiveCrudRepository<Account,String> {

}