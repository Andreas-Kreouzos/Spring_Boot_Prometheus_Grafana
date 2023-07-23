package com.andrekreou.iot.control.service;

import com.andrekreou.iot.crypto.model.CryptoNews;
import com.andrekreou.iot.crypto.repository.CryptoNewsRepo;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MainService {

    private final CryptoNewsRepo cryptoNewsRepo;

    @Autowired
    public MainService(CryptoNewsRepo cryptoNewsRepo) {
        this.cryptoNewsRepo = cryptoNewsRepo;
    }

    @Timed(value = "show.time", description = "Time taken to return showAllRates")
    public List<CryptoNews> showAllRates(){
        return new ArrayList<>(cryptoNewsRepo.findAll());
    }

    public CryptoNews saveOneCryptoItem(CryptoNews cryptoNews){
        CryptoNews saveCrypto = CryptoNews.builder()
                .news_provider_name(cryptoNews.getNews_provider_name())
                .HEADLINE(cryptoNews.getHEADLINE())
                .news_link(cryptoNews.getNews_link())
                .related_image(cryptoNews.getRelated_image())
                .build();
        return this.cryptoNewsRepo.save(saveCrypto);
    }
}