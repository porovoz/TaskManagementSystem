package com.bestapp.taskManagementSystem.security;

import com.bestapp.taskManagementSystem.exception.notFoundException.UserNotFoundException;
import com.bestapp.taskManagementSystem.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final SecurityUserMapper securityUserMapper;
    private final SecurityUser securityUser;

    public SecurityUserService(UserRepository userRepository, SecurityUserMapper securityUserMapper, SecurityUser securityUser) {
        this.userRepository = userRepository;
        this.securityUserMapper = securityUserMapper;
        this.securityUser = securityUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUserDto securityUserDto = userRepository.findByEmail(username)
                .map(securityUserMapper::toSecurityUserDto)
                .orElseThrow(() -> new UserNotFoundException(super.toString()));
        securityUser.setSecurityUserDto(securityUserDto);
        return securityUser;
    }

    public boolean userExists(String username) {
        return userRepository.existsByEmail(username);
    }
}
