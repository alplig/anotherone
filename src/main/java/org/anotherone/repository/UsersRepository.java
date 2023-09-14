package org.anotherone.repository;


import org.anotherone.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository <Users, Long> {
}
