package org.anotherone.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.anotherone.entity.Role;

import java.util.List;

@Data
@Accessors(chain = true)
public class CreateAccountRequest {

    private String email;
    private String phoneNum;
    private Long password;
    private List<Role> role;
}
