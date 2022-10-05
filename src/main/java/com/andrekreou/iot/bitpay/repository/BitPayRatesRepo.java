package com.andrekreou.iot.bitpay.repository;

import com.andrekreou.iot.bitpay.model.BitPayRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Interface that multiple classes can use and connects with data JPA
//The JPA is a map that takes the variables mapped in BitPayRates class
//as first parameter and as second, returns the data type of the Id.
@Repository
public interface BitPayRatesRepo
        extends JpaRepository<BitPayRates,Integer> {

}
