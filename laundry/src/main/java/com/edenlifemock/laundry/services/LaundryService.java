package com.edenlifemock.laundry.services;

import com.edenlifemock.clients.laundry.LaundryOrderRequest;
import com.edenlifemock.clients.laundry.LaundryOrderResponse;
import com.edenlifemock.laundry.LaundryOrder;
import com.edenlifemock.laundry.LaundryOrderRepository;
import com.edenlifemock.laundry.dtos.requests.UpdateLaundryOrderRequest;
import com.edenlifemock.laundry.dtos.responses.DeleteOrderResponse;
import com.edenlifemock.laundry.dtos.responses.LaundryOrderUpdateResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class LaundryService implements iLaundryService{
    private final LaundryOrderRepository laundryOrderRepository;

    @Override
    public LaundryOrderResponse createLaundryOrder(LaundryOrderRequest laundryOrderRequest) {
        LaundryOrder laundryOrder = LaundryOrder.builder()
                .address(laundryOrderRequest.address())
                .noOfClothing(laundryOrderRequest.noOfClothing())
                .customerName(laundryOrderRequest.customerName())
                .phoneNumber(laundryOrderRequest.phone())
                .price(calculateLaundryPrice(laundryOrderRequest.noOfClothing()))
                .build();
        laundryOrderRepository.saveAndFlush(laundryOrder);


        return new LaundryOrderResponse("order "+laundryOrder.getLaundryOrderId()+" received successfully" );
    }

    private BigDecimal calculateLaundryPrice(Integer noOfClothing) {
        return new BigDecimal(noOfClothing * 1000);
    }

    @Override
    public LaundryOrder getLaundryOrder(Long laundryOrderId) {
        return null;
    }

    @Override
    public LaundryOrderUpdateResponse updateLaundryOrder(UpdateLaundryOrderRequest updateLaundryOrderRequest) {
        return null;
    }

    @Override
    public DeleteOrderResponse deleteLaundryOrder(Long laundryOrderId) {
        return null;
    }
}
