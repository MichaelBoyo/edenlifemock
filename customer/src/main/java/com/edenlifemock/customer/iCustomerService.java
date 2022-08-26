package com.edenlifemock.customer;

import com.edenlifemock.clients.laundry.LaundryOrderRequest;
import com.edenlifemock.clients.notification.NotificationResponse;
import org.springframework.http.ResponseEntity;

public interface iCustomerService {
    NotificationResponse saveCustomer(CustomerRequest customerRequest);
    NotificationResponse orderLaundry(LaundryOrderRequest laundryOrderRequest);

}
