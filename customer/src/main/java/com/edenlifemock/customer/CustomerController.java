package com.edenlifemock.customer;

import com.edenlifemock.clients.cleaning.CleaningOrderRequest;
import com.edenlifemock.clients.food.OrderFoodRequest;
import com.edenlifemock.clients.laundry.LaundryOrderRequest;
import com.edenlifemock.clients.notification.NotificationResponse;
import com.edenlifemock.customer.dtos.CustomerRequest;
import com.edenlifemock.customer.dtos.CustomerResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
@Slf4j
public class CustomerController {
    private final com.edenlifemock.customer.service.iCustomerService iCustomerService;

    @PostMapping
    public NotificationResponse registerUser(@RequestBody CustomerRequest customerRequest){
        log.info("registering customer {}",customerRequest);
        return iCustomerService.saveCustomer(customerRequest);
    }

    @GetMapping
    public List<CustomerResponse> getAllCustomers(){
        log.info("fetching all customers");
        return iCustomerService.getAllCustomers();
    }

    @GetMapping("/laundry")
    public NotificationResponse orderLaundry(@RequestBody LaundryOrderRequest laundryOrderRequest){
        log.info("laundry order in process {}",laundryOrderRequest);
        return iCustomerService.orderLaundry(laundryOrderRequest);
    }

    @GetMapping("/cleaning")
    NotificationResponse orderCleaning(@RequestBody CleaningOrderRequest cleaningOrderRequest){
        log.info("cleaning order in process {}",cleaningOrderRequest);
        return iCustomerService.orderCleaning(cleaningOrderRequest);
    }

    @GetMapping("/food")
    public NotificationResponse orderMeal(@RequestBody OrderFoodRequest orderFoodRequest){
        log.info("food order in process {}",orderFoodRequest);
        return iCustomerService.orderMeal(orderFoodRequest);
    }


}
