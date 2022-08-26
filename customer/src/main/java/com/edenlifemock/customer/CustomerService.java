package com.edenlifemock.customer;

import com.edenlifemock.clients.cleaning.CleaningClient;
import com.edenlifemock.clients.cleaning.CleaningOrderRequest;
import com.edenlifemock.clients.laundry.LaundryClient;
import com.edenlifemock.clients.laundry.LaundryOrderRequest;
import com.edenlifemock.clients.notification.NotificationClient;
import com.edenlifemock.clients.notification.NotificationRequest;
import com.edenlifemock.clients.notification.NotificationResponse;
import com.edenlifemock.customer.exceptions.EmailAlreadyExistException;
import com.edenlifemock.customer.exceptions.UnregisteredUserException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService implements iCustomerService {
    private final CustomerRepository customerRepository;

    private final NotificationClient notificationClient;

    private final LaundryClient laundryClient;

    private final CleaningClient cleaningClient;

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
    public Customer getCustomer(Long customerId) {
        return null;
    }

    @Override
    public NotificationResponse updateUSer(UpdateCustomerRequest updateCustomerRequest) {
        return null;
    }

    @Override
    public NotificationResponse deleteCustomer(Long customerId) {
        return null;
    }

    @Override
    public NotificationResponse orderLaundry(LaundryOrderRequest laundryOrderRequest) {
        isRegisteredUser(laundryOrderRequest.email());
        var resp = laundryClient.createLaundryOrder(laundryOrderRequest);

        return notificationClient.sendNotification(new NotificationRequest(
                laundryOrderRequest.customerId(),
                laundryOrderRequest.customerName(),
                resp.message(),
                laundryOrderRequest.email()
        ));
    }

    private void isRegisteredUser(String email) {
        var anyMatch = customerRepository.findAll()
                .stream().anyMatch(customer -> customer.getEmail().equalsIgnoreCase(email));

        if(!anyMatch){
            throw new UnregisteredUserException("Unregistered user,please sign Up with Us");
        }
    }

    @Override
    public NotificationResponse orderCleaning(CleaningOrderRequest cleaningOrderRequest) {
        log.info("email-> {}", cleaningOrderRequest.email());
        isRegisteredUser(cleaningOrderRequest.email());
        var resp = cleaningClient.createLaundryOrder(cleaningOrderRequest);
        return notificationClient.sendNotification(new NotificationRequest(
                cleaningOrderRequest.customerId(),
                cleaningOrderRequest.customerName(),
                resp.message(),
                cleaningOrderRequest.email()
        ));
    }


}
