package com.giveandgrow.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @Column(name = "user_id")
    private UUID id;

    @Column(name = "identification")
    private String identification;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_last_name")
    private String middleLastName;

    @Column(name = "email")
    private String email;

    @Column(name = "institution")
    private String institution;

    @Column(name = "phone_of_reference")
    private String phoneOfReference;

    @Column(name = "confirmed_email")
    private boolean confirmedEmail;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "confirmed_phone_number")
    private boolean confirmedPhoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "account_statement")
    private boolean accountStatement;

    @Column(name = "role")
    private String role;
}