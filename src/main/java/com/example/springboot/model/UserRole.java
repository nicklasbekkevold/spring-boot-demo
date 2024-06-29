package com.example.springboot.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@SequenceGenerator(name="user_role_id_generator", initialValue=1001, allocationSize=100)
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_role_id_generator")
    private int id;

    private int version;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne
    @JoinColumn(name="unitId")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name="roleId")
    private Role role;

    @Temporal(TemporalType.TIMESTAMP)
    private Instant validFrom;

    @Temporal(TemporalType.TIMESTAMP)
    private Instant validTo;

    public UserRole(int version, User user, Unit unit, Role role, Instant validFrom, Instant validTo) {
        this.version = version;
        this.user = user;
        this.unit = unit;
        this.role = role;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public UserRole(User user, Unit unit, Role role, Instant validFrom, Instant validTo) {
        this.version = 1;
        this.user = user;
        this.unit = unit;
        this.role = role;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public UserRole(User user, Unit unit, Role role, Instant validFrom) {
        this.version = 1;
        this.user = user;
        this.unit = unit;
        this.role = role;
        this.validFrom = validFrom;
    }

    public UserRole(User user, Unit unit, Role role) {
        this.version = 1;
        this.user = user;
        this.unit = unit;
        this.role = role;
        this.validFrom = Instant.now();
    }

    @Override
    public String toString() {
        return "UserRole{id=%d, version=%d, user=%s, unit=%s, role=%s, validFrom=%s, validTo=%s}".formatted(id, version, user.getId(), unit.getId(), role.getId(), validFrom, validTo);
    }
}