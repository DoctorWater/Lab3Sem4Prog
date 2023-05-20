package embrace.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.security.Permission;
import java.util.Set;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {

    public enum RoleEnum {
        ADMIN, USER
    }

    @Id
    @GeneratedValue
    @Column(name="role_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private RoleEnum status;

    public Role(RoleEnum status) {
        this.status = status;
    }

    public SimpleGrantedAuthority getAuthority(){
        return new SimpleGrantedAuthority(this.status.toString());
    }
}