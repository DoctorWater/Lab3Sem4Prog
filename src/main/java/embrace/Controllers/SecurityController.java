package embrace.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout){
        if (error!=null){
            model.addAttribute("error", "Username or password is incorrect");
        }

        if(logout != null){
            model.addAttribute("message", "Logged out successfully");
        }
        return "login";
    }
}
