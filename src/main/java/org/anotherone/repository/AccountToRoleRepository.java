package org.anotherone.repository;

import org.anotherone.entity.AccountToRole;
import org.anotherone.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountToRoleRepository extends JpaRepository<AccountToRole, Long> {

    @Query("select atr.roleId from AccountToRole atr where atr.accountId.id = :accountId")
    List<Role> findAllRoleByAccountId(Long accountId);
}
