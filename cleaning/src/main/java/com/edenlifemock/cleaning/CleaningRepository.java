package com.edenlifemock.cleaning;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CleaningRepository extends JpaRepository<CleaningOrder, Long> {
}
