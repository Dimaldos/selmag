package ru.dimaldos.selmag.managerapp.sequrity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dimaldos.selmag.managerapp.SelmagUserRepository;
import ru.dimaldos.selmag.managerapp.entity.Authority;

@Service
@RequiredArgsConstructor
public class SelmagUserDetailService implements UserDetailsService {

    private final SelmagUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .map(user -> User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .authorities(user.getAuthorities().stream()
                                .map(Authority::getAuthority)
                                .map(SimpleGrantedAuthority::new)
                                .toList())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(username)));
    }
}
