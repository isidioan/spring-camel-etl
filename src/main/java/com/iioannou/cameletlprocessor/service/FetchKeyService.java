package com.iioannou.cameletlprocessor.service;

import com.iioannou.cameletlprocessor.util.Keys;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchKeyService {

    private final JdbcTemplate jdbcTemplate;

    private static final String query = "SELECT ID FROM ";


    public FetchKeyService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Long> fetchAllOrderIds() {
        return jdbcTemplate.queryForList(query.concat(Keys.ORDER_TABLE), Long.class);
    }
}
