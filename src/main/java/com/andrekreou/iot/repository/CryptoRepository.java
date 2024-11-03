package com.andrekreou.iot.repository;

import com.andrekreou.iot.entity.CryptoNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepository extends JpaRepository<CryptoNews, Integer> {

}
