package com.radhika.corecart.security.user;


import com.radhika.corecart.model.User;
import com.radhika.corecart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopUserDetailsService  implements UserDetailsService {
    private  final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(()->new UsernameNotFoundException("User not found!"));

        return ShopUserDetails.buildUserDetails(user);
    }
}