package com.noures.common.config;

import com.noures.common.data.repository.CustomerRepository;
import com.noures.common.data.repository.InventoryItemRepository;
import com.noures.common.data.repository.SalesOrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {

    @Bean
    public InventoryItemRepository inventoryItemRepository(){
        return new InventoryItemRepository();
    }

    @Bean
    public CustomerRepository customerRepository(){
        return new CustomerRepository();
    }

    @Bean
    public SalesOrderRepository salesOrderRepository(){
        return new SalesOrderRepository();
    }
}
