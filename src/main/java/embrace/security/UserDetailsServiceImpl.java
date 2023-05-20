package embrace.security;

import embrace.Entities.Employee;
import embrace.Exceptions.NothingWasFoundException;
import embrace.Repos.EmployeeRepo;
import embrace.Service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final EmployeeService userService;

    public UserDetailsServiceImpl(EmployeeService userService) {
        this.userService = userService;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) {
        Long id = Long.parseLong(username);
        Employee user = null;
        try {
            user = userService.getById(id);
        } catch (NothingWasFoundException e) {
            throw new RuntimeException(e);
        }

        return new MyUserPrincipal(user);
    }
}
