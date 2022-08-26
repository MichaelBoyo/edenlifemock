package com.edenlifemock.laundry.services;

import com.edenlifemock.clients.laundry.LaundryOrderRequest;
import com.edenlifemock.clients.laundry.LaundryOrderResponse;
import com.edenlifemock.laundry.LaundryOrder;
import com.edenlifemock.laundry.dtos.responses.DeleteOrderResponse;
import com.edenlifemock.laundry.dtos.requests.UpdateLaundryOrderRequest;
import com.edenlifemock.laundry.dtos.responses.LaundryOrderUpdateResponse;

public interface iLaundryService {
    LaundryOrderResponse createLaundryOrder(LaundryOrderRequest laundryOrderRequest);
    LaundryOrder getLaundryOrder(Long laundryOrderId);
    LaundryOrderUpdateResponse updateLaundryOrder(UpdateLaundryOrderRequest updateLaundryOrderRequest);
    DeleteOrderResponse deleteLaundryOrder(Long laundryOrderId);

}
