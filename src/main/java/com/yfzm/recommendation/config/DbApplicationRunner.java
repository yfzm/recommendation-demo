package com.yfzm.recommendation.config;

import com.yfzm.recommendation.service.DataPreparation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DbApplicationRunner implements ApplicationRunner {
    private final DataPreparation dataPreparation;

    @Autowired
    public DbApplicationRunner(DataPreparation dataPreparation) {
        this.dataPreparation = dataPreparation;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        dataPreparation.initializeDatabase();
    }
}
