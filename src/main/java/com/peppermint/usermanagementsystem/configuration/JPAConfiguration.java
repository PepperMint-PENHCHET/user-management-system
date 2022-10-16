package com.peppermint.usermanagementsystem.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
@EntityScan({"com.peppermint"})
@EnableJpaRepositories(basePackages = "com.peppermint")
@EnableTransactionManagement
@EnableAsync
public class JPAConfiguration {

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    @Bean
    public EntityManager entityManager(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        return localContainerEntityManagerFactoryBean.getObject().createEntityManager();
    }

}
