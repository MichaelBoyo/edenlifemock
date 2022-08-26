package com.edenlifemock.clients.cleaning;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("cleaning")
public interface CleaningClient {

    @PostMapping("api/v1/cleaning")
    CleaningOrderResponse createLaundryOrder(@RequestBody CleaningOrderRequest laundryOrderRequest);
}
