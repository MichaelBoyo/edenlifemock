package com.edenlifemock.cleaning.service;

import com.edenlifemock.cleaning.CleaningOrder;
import com.edenlifemock.cleaning.CleaningRepository;
import com.edenlifemock.cleaning.dtos.requests.UpdateCleaningOrderRequest;
import com.edenlifemock.cleaning.dtos.response.CleaningOrderUpdateResponse;
import com.edenlifemock.cleaning.dtos.response.DeleteCleaningOrderResponse;
import com.edenlifemock.clients.cleaning.CleaningOrderRequest;
import com.edenlifemock.clients.cleaning.CleaningOrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CleaningServiceImpl implements CleaningService {

    private final CleaningRepository cleaningRepository;

    @Override
    public CleaningOrderResponse createLaundryOrder(CleaningOrderRequest laundryOrderRequest) {
        CleaningOrder cleaningOrder = CleaningOrder.builder()
                .address(laundryOrderRequest.address())
                .customerName(laundryOrderRequest.customerName())
                .contactNo(laundryOrderRequest.contactNo())
                .noOfRooms(laundryOrderRequest.noOfRooms())
                .price(calculatePrice(laundryOrderRequest.noOfRooms()))
                .build();

        cleaningRepository.saveAndFlush(cleaningOrder);
        return new CleaningOrderResponse("cleaning request with id "+ cleaningOrder.getCleaningOrderId()+" received successfully");
    }

    private BigDecimal calculatePrice(Integer noOfRooms) {
        return BigDecimal.valueOf(noOfRooms * 700);
    }

    @Override
    public CleaningOrder getLaundryOrder(Long CleaningOrderId) {
        return null;
    }

    @Override
    public CleaningOrderUpdateResponse updateLaundryOrder(UpdateCleaningOrderRequest updateCleaningOrderRequest) {
        return null;
    }

    @Override
    public DeleteCleaningOrderResponse deleteLaundryOrder(Long CleaningOrderId) {
        return null;
    }
}
