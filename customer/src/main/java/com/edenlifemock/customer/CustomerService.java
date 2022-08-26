package com.edenlifemock.customer;

import com.edenlifemock.clients.laundry.LaundryClient;
import com.edenlifemock.clients.laundry.LaundryOrderRequest;
import com.edenlifemock.clients.notification.NotificationClient;
import com.edenlifemock.clients.notification.NotificationRequest;
import com.edenlifemock.clients.notification.NotificationResponse;
import com.edenlifemock.customer.exceptions.EmailAlreadyExistException;
import com.edenlifemock.customer.exceptions.UnregisteredUserException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CustomerService implements iCustomerService {
    private final CustomerRepository customerRepository;

    private final NotificationClient notificationClient;

    private final LaundryClient laundryClient;

    @Override
    public NotificationResponse saveCustomer(CustomerRequest customerRequest) {
        boolean anyMatch = customerRepository.findAll().stream().
                anyMatch(customer -> customer.getEmail().equals(customerRequest.email()));
        if (anyMatch) {
            throw new EmailAlreadyExistException("email already exist");
        }
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .password(customerRequest.password())
                .dateRegistered(LocalDateTime.now())
                .build();
        customerRepository.saveAndFlush(customer);

        return notificationClient.sendNotification(
                new NotificationRequest(customer.getCustomerId(),
                        customer.getFirstName() + " " + customer.getLastName(),
                        "hi " + customer.getFirstName() + " " + customer.getLastName() + " welcome to EdenLife",
                        customer.getEmail()));

    }

    @Override
    public NotificationResponse orderLaundry(LaundryOrderRequest laundryOrderRequest) {
        var anyMatch = customerRepository.findAll()
                .stream().anyMatch(customer -> customer.getEmail().equalsIgnoreCase(laundryOrderRequest.email()));

        if(!anyMatch){
            throw new UnregisteredUserException("please sign Up with Us");
        }
        var resp = laundryClient.createLaundryOrder(laundryOrderRequest);

        return notificationClient.sendNotification(new NotificationRequest(
                laundryOrderRequest.customerId(),
                laundryOrderRequest.customerName(),
                resp.message(),
                laundryOrderRequest.email()
        ));
    }


}
