package cycleSpring.cycleAuth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<User> authenticate(String username, String password) {
        Optional<User> optUser = userRepository.findByName(username);
        if (optUser.isEmpty()) {
            throw new CycleShopBusinessException("User not found");
        }
        if (!optUser.get().getPassword().equals(password)) {
            return Optional.empty();
        }
        return optUser;
    }

    public User create(User user) {
        user.setPassword("{bcrypt}" + passwordEncoder.encode(user.getPassword()));
        //user.setPassword("{noop}" + user.getPassword());
        return userRepository.save(user);
    }

    public Optional<User> getById(long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getByName(String name) {
        return userRepository.findByName(name);
    }

    
}
