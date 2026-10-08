package com.app.ecom.dto;

import lombok.Data;

@Data
public class RequestUser {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDTO address;
}
