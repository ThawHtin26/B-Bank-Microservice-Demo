package com.bbank.accounts.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Customer Entity Class
 * @Author Thaw Htin Aung
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    private String name;

    @Email
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;
}
