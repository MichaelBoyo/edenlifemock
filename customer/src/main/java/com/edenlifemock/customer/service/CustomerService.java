package com.edenlifemock.customer.service;

import com.edenlifemock.amqp.RabbitMQMessageProducer;
import com.edenlifemock.clients.cleaning.CleaningClient;
import com.edenlifemock.clients.cleaning.CleaningOrderRequest;
import com.edenlifemock.clients.food.FoodClient;
import com.edenlifemock.clients.food.MealPlanRequest;
import com.edenlifemock.clients.food.OrderFoodRequest;
import com.edenlifemock.clients.laundry.LaundryClient;
import com.edenlifemock.clients.laundry.LaundryOrderRequest;
import com.edenlifemock.clients.notification.NotificationRequest;
import com.edenlifemock.clients.notification.NotificationResponse;
import com.edenlifemock.customer.Customer;
import com.edenlifemock.customer.CustomerRepository;
import com.edenlifemock.customer.dtos.CustomerRequest;
import com.edenlifemock.customer.dtos.CustomerResponse;
import com.edenlifemock.customer.dtos.Role;
import com.edenlifemock.customer.dtos.UpdateCustomerRequest;
import com.edenlifemock.customer.exceptions.EmailAlreadyExistException;
import com.edenlifemock.customer.exceptions.UnregisteredUserException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.edenlifemock.customer.dtos.Role.ADMIN;
import static com.edenlifemock.customer.dtos.Role.USER;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService implements iCustomerService {
    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;
    private final LaundryClient laundryClient;

    private final CleaningClient cleaningClient;

    private final FoodClient foodClient;

    private final RabbitMQMessageProducer rabbitMQMessageProducer;

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
                .username(customerRequest.username())
                .password(passwordEncoder.encode(customerRequest.password()))
                .dateRegistered(LocalDateTime.now())
                .build();
        if (customerRequest.role().equals("admin"))customer.addRole(ADMIN);

        customer.addRole(USER);
        customerRepository.saveAndFlush(customer);

        NotificationRequest notifiCationRequest = new NotificationRequest(customer.getCustomerId(),
                customer.getFirstName() + " " + customer.getLastName(),
                "hi " + customer.getFirstName() + " " + customer.getLastName() + " welcome to EdenLife",
                customer.getEmail());

        publisher(notifiCationRequest);

        return new NotificationResponse("hi " + customer.getFirstName() + " " +
                customer.getLastName() + " welcome to EdenLife");
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
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream().map(customer -> new CustomerResponse(customer.getCustomerId(),
                customer.getEmail(), customer.getFirstName(), customer.getLastName(), customer.getDateRegistered())).toList();
    }

    @Override
    public NotificationResponse orderLaundry(LaundryOrderRequest laundryOrderRequest) {
        isRegisteredUser(laundryOrderRequest.email());

        var customer = customerRepository.findCustomerByEmail(laundryOrderRequest.email());

        var resp = laundryClient.createLaundryOrder(laundryOrderRequest);

        NotificationRequest notifiCationRequest = new NotificationRequest(
                customer.getCustomerId(),
                laundryOrderRequest.customerName(),
                resp.message(),
                laundryOrderRequest.email()
        );

        publisher(notifiCationRequest);

        return new NotificationResponse("laundry order successful");
    }

    private void isRegisteredUser(String email) {
        if (customerRepository.findAll()
                .stream().noneMatch(customer -> customer.getEmail().equalsIgnoreCase(email))) {
            throw new UnregisteredUserException("Unregistered user,please sign Up with Us");
        }
    }

    @Override
    public NotificationResponse orderCleaning(CleaningOrderRequest cleaningOrderRequest) {
        log.info("email-> {}", cleaningOrderRequest.email());
        isRegisteredUser(cleaningOrderRequest.email());
        var customer = customerRepository.findCustomerByEmail(cleaningOrderRequest.email());
        var resp = cleaningClient.createLaundryOrder(cleaningOrderRequest);
        NotificationRequest notifiCationRequest = new NotificationRequest(
                customer.getCustomerId(),
                cleaningOrderRequest.customerName(),
                resp.message(),
                cleaningOrderRequest.email()
        );
        publisher(notifiCationRequest);

        return new NotificationResponse("ordered successfully");
    }

    @Override
    public NotificationResponse orderMeal(OrderFoodRequest orderFoodRequest) {
        log.info("ordering food-> {}", orderFoodRequest.email());
        isRegisteredUser(orderFoodRequest.email());
        var customer = customerRepository.findCustomerByEmail(orderFoodRequest.email());
        var resp = foodClient.getMealByName(orderFoodRequest.mealName());
        NotificationRequest notifiCationRequest = new NotificationRequest(
                customer.getCustomerId(),
                orderFoodRequest.customerName(),
                resp.message(),
                orderFoodRequest.email()
        );
        publisher(notifiCationRequest);

        return new NotificationResponse("meal ordered successfully");
    }

    private void publisher(NotificationRequest notifiCationRequest) {
        rabbitMQMessageProducer.publish(
                notifiCationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }

    @Override
    public NotificationResponse orderWeeklyMealPlan(MealPlanRequest request) {
        var resp = foodClient.orderWeeklyMealPlan(request);
        var customer = customerRepository.findCustomerByEmail(request.email());

        customerRepository.saveAndFlush(customer);
        NotificationRequest notifiCationRequest = new NotificationRequest(
                customer.getCustomerId(),
                customer.getFirstName(),
                resp.toString(),
                request.email()
        );
        publisher(notifiCationRequest);
        return new NotificationResponse("weekly meal ordered successfully");
    }

    @Override
    public NotificationResponse deleteAllCustomers() {
        customerRepository.deleteAll();
        return new NotificationResponse("all users deleted successfully");
    }



}
