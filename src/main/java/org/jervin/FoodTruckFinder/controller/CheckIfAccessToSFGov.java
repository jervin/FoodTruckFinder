package org.jervin.FoodTruckFinder.controller;

import org.jervin.FoodTruckFinder.dao.IFoodTrucksDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CheckIfAccessToSFGov implements HealthIndicator {
    private static final Logger logger = LoggerFactory.getLogger(CheckIfAccessToSFGov.class);
    @Autowired
    private IFoodTrucksDAO dao;

    @Override
    public Health health() {
        if (dao.checkHealth())
            return Health.up().build();
        return Health.down().withDetail("access to SF Gov", "Cannot access live data").build();
    }
}
