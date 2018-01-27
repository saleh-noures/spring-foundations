package com.noures.common.config;

import com.noures.common.data.repository.CustomerRepository;
import com.noures.common.data.repository.InventoryItemRepository;
import com.noures.common.data.repository.SalesOrderRepository;
import com.noures.common.service.InventoryService;
import com.noures.common.service.OrderService;
import com.noures.common.service.impl.InventoryServiceImpl;
import com.noures.common.service.impl.OrderServiceImpl;
import com.noures.common.util.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

/* @Configuration Will declare the class as a configuration class */
@Configuration

/* You can span your beans configuration over many configuration classes then usr @Import for importing */
@Import(DataConfig.class)

/*
1- @PropertySource is used to Externalize Bean Configurations
2- ${spring.profiles.active} is a Spring expression language
3- spring.profiles.active is an environment variable that should be defined in Run menu -> edit configurations
*/
@PropertySource("classpath:/application-${spring.profiles.active}.properties")

/* @ComponentScan will enable componant scanning */
@ComponentScan(basePackages = {"com.noures.common"})
public class AppConfig {

/* The value will be read from the properties files */
    @Value("${greeting.text}")
    private String greetingText;

    @Value("${greeting.preamble}")
    private String greetingPreamble;

/* Another example of Spring expression language*/
    @Value("#{new Boolean(environment['spring.profiles.active']=='dev')}")
    private boolean isDev;

/* Comment out the @scope to convert the scope to singleton the see the result */
    @Bean
    @Scope("prototype")
    public Worker worker(){
        return new Worker(greetingPreamble, greetingText, isDev);
    }

    /*Notice that inventoryServiceImpl was annotated with @Service, so no need to define it as a bean here*/

    /* The name of the bean will be the method name and the dependencies will listed as method parameters. */
    @Bean
    public OrderService orderService(InventoryService inventoryService, CustomerRepository customerRepository, SalesOrderRepository salesOrderRepository){
        return new OrderServiceImpl(inventoryService, customerRepository, salesOrderRepository);
    }


    public static void main (String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = context.getBean(OrderService.class);
        System.out.println(orderService==null?"NULL":"A OK");
        Worker worker = context.getBean(Worker.class);
        worker.execute();
        Worker worker1 = context.getBean(Worker.class);
        worker1.execute();
    }
}

