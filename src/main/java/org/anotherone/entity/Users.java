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
@Table(name = Users.TABLE_NAME)
@Entity
public class Users {

    public static final String TABLE_NAME = "users";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created", nullable = false)
    private Timestamp created;

    @Column(name = "modified", nullable = true)
    private Timestamp modified;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;

    @Column(name = "middle_name", nullable = true, length = 255)
    private String middleName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "account_users_fk"))
    private Account accountId;
}
