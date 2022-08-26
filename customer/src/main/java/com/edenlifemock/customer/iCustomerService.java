package com.edenlifemock.customer;

import com.edenlifemock.clients.cleaning.CleaningOrderRequest;
import com.edenlifemock.clients.laundry.LaundryOrderRequest;
import com.edenlifemock.clients.notification.NotificationResponse;
import org.springframework.http.ResponseEntity;

public interface iCustomerService {
    NotificationResponse saveCustomer(CustomerRequest customerRequest);
    Customer getCustomer(Long customerId);
    NotificationResponse updateUSer(UpdateCustomerRequest updateCustomerRequest);
    NotificationResponse deleteCustomer(Long customerId);
    NotificationResponse orderLaundry(LaundryOrderRequest laundryOrderRequest);

    NotificationResponse orderCleaning(CleaningOrderRequest cleaningOrderRequest);



}
