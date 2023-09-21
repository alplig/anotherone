package org.anotherone.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.anotherone.entity.RoleEntity;

import java.util.List;

@Data
@Accessors(chain = true)
public class CreateAccountRequest {

    private String email;
    private String phoneNum;
    private Long password;
    private List<RoleEntity> roleEntity;
}
