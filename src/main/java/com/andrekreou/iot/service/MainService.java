package com.andrekreou.iot.service;

import com.andrekreou.iot.CryptoClient;
import com.andrekreou.iot.entity.CryptoNews;
import com.andrekreou.iot.repository.CryptoNewsRepo;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MainService {

    private final CryptoNewsRepo cryptoNewsRepo;
    private final CryptoClient cryptoClient;

    @Autowired
    public MainService(CryptoNewsRepo cryptoNewsRepo, CryptoClient cryptoClient) {
        this.cryptoNewsRepo = cryptoNewsRepo;
        this.cryptoClient = cryptoClient;
    }

    @Timed(value = "show.time", description = "Time taken to return showAllRates")
    public List<CryptoNews> showAllRates(){
        return cryptoNewsRepo.findAll();
    }

    public void persistCrypto() {
        List<CryptoNews> cryptoNewsList = cryptoClient.fetchCryptoNews();
        cryptoNewsRepo.saveAll(cryptoNewsList);
    }
}
