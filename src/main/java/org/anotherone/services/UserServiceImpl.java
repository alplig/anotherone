package org.anotherone.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherone.dto.AccountResponse;
import org.anotherone.dto.CreateAccountRequest;
import org.anotherone.dto.CreateUserRequest;
import org.anotherone.dto.UsersResponse;
import org.anotherone.entity.AccountEntity;
import org.anotherone.entity.RoleEntity;
import org.anotherone.entity.UsersEntity;
import org.anotherone.repository.AccountToRoleRepository;
import org.anotherone.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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
                .toList();
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
        UsersEntity user = buildUserCreate(request);
        return buildUserResponse(usersRepository.save(user));
    }

    @NonNull
    @Override
    @Transactional
    public UsersResponse update(Long userId, CreateUserRequest request) {
        return new UsersResponse();
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        // В процессе реализации.
    }


    private UsersResponse buildUserResponse(@NonNull UsersEntity user) {
        log.info("USER: " + user.toString());
        return new UsersResponse()
                .setAccount(buildAccountResponse(user.getAccountEntityId()))
                .setCreated(user.getCreated().toLocalDateTime())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setMiddleName(user.getMiddleName());
    }


    private AccountResponse buildAccountResponse (@NonNull AccountEntity accountEntity){
        log.info("ACCOUNT: " + accountEntity.toString());
        return new AccountResponse()
                .setEmail(accountEntity.getEmail())
                .setPhoneNum(accountEntity.getPhoneNum())
                .setRoles(getAllAccountRoles(accountEntity.getId()));
    }


    private List<String> getAllAccountRoles(@NonNull Long accountId) {
        log.info("account ID for role list: " + accountId);
        List<RoleEntity> entities = accountToRoleRepository.findAllRoleByAccountId(accountId);
        return entities.stream()
                .map(RoleEntity::getUserRole)
                .toList();
    }

    private UsersEntity buildUserCreate(@NonNull CreateUserRequest request) {
        return new UsersEntity()
                .setCreated(Timestamp.valueOf(LocalDateTime.now()))
                .setLastName(request.getLastName())
                .setFirstName(request.getFirstName())
                .setMiddleName(request.getMiddleName())
                .setAccountEntityId(createAccount(request.getAccount()));
    }

    private AccountEntity createAccount(@NonNull CreateAccountRequest request) {
        return new AccountEntity().setCreated(Timestamp.valueOf(LocalDateTime.now()))
                .setPhoneNum(request.getPhoneNum())
                .setEmail(request.getEmail())
                .setPasswordHash(request.getPassword());
    }
}
