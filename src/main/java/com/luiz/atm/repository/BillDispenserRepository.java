package com.luiz.atm.repository;

import com.luiz.atm.model.BillDispenser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDispenserRepository extends CrudRepository<BillDispenser, Integer> {
    BillDispenser findBillDispenserByValue(int value);

    @Query(value = " select sum(value * quantity) total  " +
            " from bill_dispenser ", nativeQuery = true)
    Long sumTotalOfBills();
}
