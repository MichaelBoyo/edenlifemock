package com.edenlifemock.laundry;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LaundryOrderRepository extends JpaRepository<LaundryOrder, Long> {
}
