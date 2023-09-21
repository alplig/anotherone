package org.anotherone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = Role.TABLE_NAME)
@Entity
public class Role {

    public static final String TABLE_NAME = "role";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "user_role", nullable = false, length = 20)
    private String userRole;
}
