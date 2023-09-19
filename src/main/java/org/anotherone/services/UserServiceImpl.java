package org.anotherone.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherone.dto.AccountResponse;
import org.anotherone.dto.CreateAccountRequest;
import org.anotherone.dto.CreateUserRequest;
import org.anotherone.dto.UsersResponse;
import org.anotherone.entity.Account;
import org.anotherone.entity.Role;
import org.anotherone.entity.Users;
import org.anotherone.repository.AccountToRoleRepository;
import org.anotherone.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
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
    public UsersResponse createUser(@NonNull CreateUserRequest request) {
        Users user = buildUserCreate(request);
        return buildUserResponse(usersRepository.save(user));
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


    private UsersResponse buildUserResponse(@NonNull Users user) {
        log.info("USER: " + user.toString());
        return new UsersResponse()
                .setAccount(buildAccountResponse(user.getAccountId()))
                .setCreated(user.getCreated().toLocalDateTime())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setMiddleName(user.getMiddleName());
    }


    private AccountResponse buildAccountResponse (@NonNull Account account){
        log.info("ACCOUNT: " + account.toString());
        return new AccountResponse()
                .setEmail(account.getEmail())
                .setPhoneNum(account.getPhoneNum())
                .setRoles(getAllAccountRoles(account.getId()));
    }


    private List<String> getAllAccountRoles(@NonNull Long accountId) {
        log.info("account ID for role list: " + accountId);
        List<Role> entities = accountToRoleRepository.findAllRoleByAccountId(accountId);
        return entities.stream()
                .map(Role::getUserRole)
                .collect(Collectors.toList());
    }

    private Users buildUserCreate(@NonNull CreateUserRequest request) {
        return new Users()
                .setCreated(Timestamp.valueOf(LocalDateTime.now()))
                .setLastName(request.getLastName())
                .setFirstName(request.getFirstName())
                .setMiddleName(request.getMiddleName())
                .setAccountId(createAccount(request.getAccount()));
    }

    private Account createAccount(@NonNull CreateAccountRequest request) {
        return new Account().setCreated(Timestamp.valueOf(LocalDateTime.now()))
                .setPhoneNum(request.getPhoneNum())
                .setEmail(request.getEmail())
                .setPasswordHash(request.getPassword())
                ;
    }
}
