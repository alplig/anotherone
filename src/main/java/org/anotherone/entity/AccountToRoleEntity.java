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
@Table(name = "account_to_role", schema = "public", catalog = "postgres")
@Entity
public class AccountToRoleEntity {

    public static final String TABLE_NAME = "account_to_role";

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

    @ManyToOne
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "fk_account_accounttorole"))
    private AccountEntity accountEntityId;

    @ManyToOne
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_account_role"))
    private RoleEntity roleEntityId;

    @Column(name = "start_date", nullable = false)
    private Timestamp startDate;

    @Column(name = "end_date", nullable = true)
    private Timestamp endDate;
}
