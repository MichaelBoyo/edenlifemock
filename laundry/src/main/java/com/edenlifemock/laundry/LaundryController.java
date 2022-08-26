package com.edenlifemock.laundry;

import com.edenlifemock.clients.laundry.LaundryOrderRequest;
import com.edenlifemock.laundry.services.iLaundryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/laundry")
@Slf4j
@AllArgsConstructor
public class LaundryController {
    private final iLaundryService iLaundryService;

    @PostMapping
    public ResponseEntity<?> createLaundryOrder(@RequestBody LaundryOrderRequest laundryOrderRequest){
        log.info("preparing laundry order {}",laundryOrderRequest);
        return new ResponseEntity<>(iLaundryService.createLaundryOrder(laundryOrderRequest), HttpStatus.OK);
    }
}
