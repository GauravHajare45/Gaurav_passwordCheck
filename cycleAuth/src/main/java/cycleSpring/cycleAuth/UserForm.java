package cycleSpring.cycleAuth;

import lombok.Data;

@Data
public class UserForm {
    private String name;
    private String password;
    private String passwordRepeat;
}
