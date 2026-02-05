package com.radhika.corecart.data;
import com.radhika.corecart.model.Role;
import com.radhika.corecart.model.User;
import com.radhika.corecart.repository.RoleRepository;
import com.radhika.corecart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));


        String adminEmail = "admin@corecart.com";

        User existingAdmin = userRepository.findByEmail(adminEmail);

        if (existingAdmin == null) {

            User admin = new User();
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("Admin@12345"));

            admin.getRoles().add(adminRole);
            admin.getRoles().add(userRole);

            userRepository.save(admin);
        }

    }
}