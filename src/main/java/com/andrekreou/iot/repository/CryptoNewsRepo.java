package com.andrekreou.iot.repository;

import com.andrekreou.iot.entity.CryptoNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoNewsRepo extends JpaRepository<CryptoNews, Integer> {

}
