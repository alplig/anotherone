package org.anotherone.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AccountResponse {

    private String email;
    private String phoneNum;
    private List<String> roles;
}