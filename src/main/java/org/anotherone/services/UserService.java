package org.anotherone.services;

import lombok.NonNull;
import org.anotherone.dto.CreateUserRequest;
import org.anotherone.dto.UsersResponse;



import java.util.List;

public interface UserService {

    @NonNull
    List<UsersResponse> findAll();

    @NonNull
    UsersResponse findById(Long userId);

    @NonNull
    UsersResponse createUser( CreateUserRequest request);

    @NonNull
    UsersResponse update(Long userId,  CreateUserRequest request);

    void delete(Long userId);
}
