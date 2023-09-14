package org.anotherone.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateUserRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private CreateAccountRequest account;
}
