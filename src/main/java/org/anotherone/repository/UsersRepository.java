package org.anotherone.repository;


import org.anotherone.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository <Users, Long> {
}
