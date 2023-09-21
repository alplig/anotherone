package org.anotherone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = AccountEntity.TABLE_NAME)
@Entity
public class AccountEntity {

    public static final String TABLE_NAME = "account";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created", nullable = false)
    private Timestamp created;

    @Column(name = "modified", nullable = true)
    private Timestamp modified;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "email", nullable = true, length = 255)
    private String email;

    @Column(name = "phone_num", nullable = true, length = 255)
    private String phoneNum;

    @Column(name = "new_password_hash", nullable = true)
    private Long newPasswordHash;

    @Column(name = "password_hash", nullable = false)
    private Long passwordHash;
}
