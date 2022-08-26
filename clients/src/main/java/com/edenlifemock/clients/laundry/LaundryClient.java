package com.edenlifemock.clients.laundry;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("laundry")
public interface LaundryClient {
    @PostMapping("api/v1/laundry")
    LaundryOrderResponse createLaundryOrder(@RequestBody LaundryOrderRequest laundryOrderRequest);
}
