package org.anotherone.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.anotherone.dto.AccountResponse;
import org.anotherone.dto.CreateUserRequest;
import org.anotherone.dto.UsersResponse;
import org.anotherone.entity.Account;
import org.anotherone.entity.AccountToRole;
import org.anotherone.entity.Role;
import org.anotherone.entity.Users;
import org.anotherone.repository.AccountToRoleRepository;
import org.anotherone.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UsersRepository usersRepository;
    private final AccountToRoleRepository accountToRoleRepository;


    @NonNull
    @Override
    @Transactional(readOnly = true)
    public List<UsersResponse> findAll() {
        return usersRepository.findAll()
                .stream()
                .map(this::buildUserResponse)
                .collect(Collectors.toList());
    }

    private UsersResponse buildUserResponse(@NonNull Users user) {
        return new UsersResponse()
                .setAccount(buildAccountResponse(user.getAccountId()))
                .setCreated(user.getCreated().toLocalDateTime())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setMiddleName(user.getMiddleName());
    }

    private AccountResponse buildAccountResponse (@NonNull Account account){
        return new AccountResponse()
                .setEmail(account.getEmail())
                .setPhoneNum(account.getPhoneNum())
                .setRoles(getAllAccountRoles(account.getId()));
    }

    private List<String> getAllAccountRoles(Long accountId) {
        return accountToRoleRepository.findById(accountId)
                .stream()
                .map(AccountToRole::getRoleId)
                .map(Role::getUserRole)
                .collect(Collectors.toList());
    }

    @NonNull
    @Override
    @Transactional(readOnly = true)
    public UsersResponse findById(@NonNull Long userId) {
        return usersRepository.findById(userId)
                .map(this::buildUserResponse)
                .orElseThrow(() -> new EntityNotFoundException("User: " + userId + " not found"));
    }

    @NonNull
    @Override
    @Transactional
    public UsersResponse createUser(CreateUserRequest request) {
        return null;
    }

    @NonNull
    @Override
    @Transactional
    public UsersResponse update(Long userId, CreateUserRequest request) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Long userId) {
    }
}
