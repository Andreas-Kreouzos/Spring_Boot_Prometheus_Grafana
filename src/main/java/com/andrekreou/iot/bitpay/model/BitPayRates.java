package com.andrekreou.iot.bitpay.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

//This class is responsible for mapping the key variables from
//the JSON array to be imported. The name keys from JSON have
//to be exactly the same as here, in order for data to be fetched.


//Don't forget to apply Lombok, at the end of the project as appendix!!!
@Entity
@Table
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitPayRates {
    @Id
    @SequenceGenerator(
            name = "bitpay_sequence",
            sequenceName = "bitpay_sequence",
            allocationSize = 1
            )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bitpay_sequence"
    )
    private Integer id;
    private String code;
    private String name;
    private Long rate;


    @CreationTimestamp
    private java.time.LocalDateTime timestamp;

    protected BitPayRates() {
    }

    public BitPayRates(String code, String name, Long rate, LocalDateTime timestamp) {
        this.code = code;
        this.name = name;
        this.rate = rate;
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "BitPayRates{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                '}';
    }
}
