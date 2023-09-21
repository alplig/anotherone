package org.anotherone.repository;

import org.anotherone.entity.AccountToRoleEntity;
import org.anotherone.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountToRoleRepository extends JpaRepository<AccountToRoleEntity, Long> {

    @Query("select atr.roleId from AccountToRoleEntity atr where atr.accountId.id = :accountId")
    List<RoleEntity> findAllRoleByAccountId(Long accountId);
}
