package embrace.validator;

import embrace.Entities.Employee;
import embrace.Service.EmployeeService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class userValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (employee.getPassword().length() < 8) {
            errors.rejectValue("password", "Size.userForm.password");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
    }
}
