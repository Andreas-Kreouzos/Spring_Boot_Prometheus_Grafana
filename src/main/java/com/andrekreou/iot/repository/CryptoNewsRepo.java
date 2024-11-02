package com.andrekreou.iot.repository;

import com.andrekreou.iot.entity.CryptoNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Interface that multiple classes can use and connects with data JPA
//The JPA is a map that takes the variables mapped in Movies class
//as first parameter and as second, returns the data type of the Id.
@Repository
public interface CryptoNewsRepo
        extends JpaRepository<CryptoNews,Integer> {

}
