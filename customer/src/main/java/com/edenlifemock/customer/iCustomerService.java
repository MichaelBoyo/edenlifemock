package com.edenlifemock.customer;

import com.edenlifemock.clients.cleaning.CleaningOrderRequest;
import com.edenlifemock.clients.food.FoodResponse;
import com.edenlifemock.clients.food.OrderFoodRequest;
import com.edenlifemock.clients.laundry.LaundryOrderRequest;
import com.edenlifemock.clients.notification.NotificationResponse;

public interface iCustomerService {
    NotificationResponse saveCustomer(CustomerRequest customerRequest);
    Customer getCustomer(Long customerId);
    NotificationResponse updateUSer(UpdateCustomerRequest updateCustomerRequest);
    NotificationResponse deleteCustomer(Long customerId);
    NotificationResponse orderLaundry(LaundryOrderRequest laundryOrderRequest);

    NotificationResponse orderCleaning(CleaningOrderRequest cleaningOrderRequest);

    NotificationResponse orderMeal(OrderFoodRequest orderFoodRequest);

}
