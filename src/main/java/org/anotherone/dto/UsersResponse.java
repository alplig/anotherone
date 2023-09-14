package org.anotherone.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


@Data
@Accessors(chain = true)
public class UsersResponse {

    private LocalDateTime created;
    private String firstName;
    private String lastName;
    private String middleName;
    private AccountResponse account;
}
