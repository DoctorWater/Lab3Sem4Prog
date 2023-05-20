package embrace.security;

import embrace.Entities.Employee;
import embrace.Entities.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserPrincipal implements UserDetails {
    private final Employee employee;

    public MyUserPrincipal(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return employee.takeAuthorities();
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return employee.getStatus().equals(Status.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return employee.getStatus().equals(Status.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return employee.getStatus().equals(Status.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return employee.getStatus().equals(Status.ACTIVE);
    }
}
