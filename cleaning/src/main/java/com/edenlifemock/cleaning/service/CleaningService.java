package com.edenlifemock.cleaning.service;


import com.edenlifemock.cleaning.CleaningOrder;
import com.edenlifemock.cleaning.dtos.requests.UpdateCleaningOrderRequest;
import com.edenlifemock.cleaning.dtos.response.CleaningOrderUpdateResponse;
import com.edenlifemock.cleaning.dtos.response.DeleteCleaningOrderResponse;
import com.edenlifemock.clients.cleaning.CleaningOrderRequest;
import com.edenlifemock.clients.cleaning.CleaningOrderResponse;

public interface CleaningService {
    CleaningOrderResponse createLaundryOrder(CleaningOrderRequest laundryOrderRequest);
    CleaningOrder getLaundryOrder(Long CleaningOrderId);
    CleaningOrderUpdateResponse updateLaundryOrder(UpdateCleaningOrderRequest updateCleaningOrderRequest);
    DeleteCleaningOrderResponse deleteLaundryOrder(Long CleaningOrderId);
}
