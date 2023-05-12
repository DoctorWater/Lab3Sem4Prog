package embrace.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Security;

public class SecurityServiceImpl implements SecurityService{
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    public SecurityServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if(userDetails instanceof UserDetails){
            return ((UserDetails) userDetails).getUsername();
        }
        return null;
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        if(authenticationToken.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }
}
