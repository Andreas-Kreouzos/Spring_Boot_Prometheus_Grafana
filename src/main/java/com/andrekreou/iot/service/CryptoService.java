package com.andrekreou.iot.service;

import com.andrekreou.iot.client.CryptoClient;
import com.andrekreou.iot.entity.CryptoNews;
import com.andrekreou.iot.repository.CryptoRepository;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CryptoService {

    private final CryptoRepository cryptoRepository;
    private final CryptoClient cryptoClient;

    @Autowired
    public CryptoService(CryptoRepository cryptoRepository, CryptoClient cryptoClient) {
        this.cryptoRepository = cryptoRepository;
        this.cryptoClient = cryptoClient;
    }

    @Timed(value = "show.time", description = "Time taken to return showAllRates")
    public List<CryptoNews> showAllRates(){
        return cryptoRepository.findAll();
    }

    public void persistCrypto() {
        List<CryptoNews> cryptoNewsList = cryptoClient.fetchCryptoNews();
        cryptoRepository.saveAll(cryptoNewsList);
    }
}
