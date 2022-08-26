package com.edenlifemock.cleaning;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CleaningOrder {
    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "laundry_order_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "laundry_order_id_sequence"
    )
    private Long cleaningOrderId;
    private String customerName;
    private String contactNo;
    private String address;
    private Integer noOfRooms;
    private BigDecimal price;
}
