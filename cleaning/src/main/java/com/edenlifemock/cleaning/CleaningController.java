package com.edenlifemock.cleaning;

import com.edenlifemock.cleaning.service.CleaningService;
import com.edenlifemock.clients.cleaning.CleaningOrderRequest;
import com.edenlifemock.clients.cleaning.CleaningOrderResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cleaning")
@AllArgsConstructor
@Slf4j
public class CleaningController {
    private final CleaningService cleaningService;

    @PostMapping
    CleaningOrderResponse createLaundryOrder(@RequestBody CleaningOrderRequest cleaningOrderRequest){
        log.info("preparing cleaningOrder order {}",cleaningOrderRequest);
        return cleaningService.createLaundryOrder(cleaningOrderRequest);
    }

}
