package embrace.Service;

import embrace.Entities.Employee;
import embrace.Entities.Role;
import embrace.Exceptions.NothingWasFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final EmployeeService employeeService;

    public UserDetailsService(EmployeeService employeeService)   {
        this.employeeService = employeeService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        try {
            Employee employee = employeeService.getById(Long.parseLong(id));
            Set<GrantedAuthority> authorities = new HashSet<>();
            for (Role role: employee.getRoles()){
                authorities.add(new SimpleGrantedAuthority(role.name()));
            }
            return new User(employee.getId().toString(), employee.getPassword(), authorities);
        } catch (NothingWasFoundException e) {
            throw new UsernameNotFoundException("No employee with this id was found.");
        }
    }
}
