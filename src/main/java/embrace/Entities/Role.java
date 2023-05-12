package embrace.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="role")
public enum Role {
    USER,
    ADMIN;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
}