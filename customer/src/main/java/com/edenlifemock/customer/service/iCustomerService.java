package com.edenlifemock.customer.service;

import com.edenlifemock.clients.cleaning.CleaningOrderRequest;
import com.edenlifemock.clients.food.OrderFoodRequest;
import com.edenlifemock.clients.laundry.LaundryOrderRequest;
import com.edenlifemock.clients.notification.NotificationResponse;
import com.edenlifemock.customer.Customer;
import com.edenlifemock.customer.dtos.CustomerRequest;
import com.edenlifemock.customer.dtos.CustomerResponse;
import com.edenlifemock.customer.dtos.UpdateCustomerRequest;

import java.util.List;

public interface iCustomerService {
    NotificationResponse saveCustomer(CustomerRequest customerRequest);
    Customer getCustomer(Long customerId);
    NotificationResponse updateUSer(UpdateCustomerRequest updateCustomerRequest);
    NotificationResponse deleteCustomer(Long customerId);
    List<CustomerResponse> getAllCustomers();
    NotificationResponse orderLaundry(LaundryOrderRequest laundryOrderRequest);

    NotificationResponse orderCleaning(CleaningOrderRequest cleaningOrderRequest);

    NotificationResponse orderMeal(OrderFoodRequest orderFoodRequest);

}
