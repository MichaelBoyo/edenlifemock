package com.edenlifemock.laundry;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LaundryOrder {
    @Id
    @SequenceGenerator(
            name = "laundry_order_id_sequence",
            sequenceName = "laundry_order_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "laundry_order_id_sequence"
    )
    private Long laundryOrderId;
    private String customerName;
    private String phoneNumber;
    private String address;
    private Integer noOfClothing;
    private BigDecimal price;
}
