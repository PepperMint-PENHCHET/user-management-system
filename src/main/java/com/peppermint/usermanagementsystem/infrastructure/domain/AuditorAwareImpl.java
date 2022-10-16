package com.peppermint.usermanagementsystem.infrastructure.domain;

import com.peppermint.usermanagementsystem.domain.entity.AppUser;
import com.peppermint.usermanagementsystem.repository.AppUserRepository;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<AppUser> {

    private final AppUserRepository userRepository;

    public AuditorAwareImpl(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<AppUser> getCurrentAuditor() {
        Optional<AppUser> currentUser;
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            final Authentication authentication = securityContext.getAuthentication();
            try {
                if (authentication != null) {
                    currentUser = Optional.ofNullable((AppUser) authentication.getPrincipal());
                } else {
                    currentUser = retrieveSuperUser();
                }
            } catch (Exception ex) {
                currentUser = retrieveSuperUser();
            }
        } else {
            currentUser = retrieveSuperUser();
        }
        return currentUser;
    }

    private Optional<AppUser> retrieveSuperUser() {

        return Optional.of(userRepository.findById(1L).orElseThrow());
    }
}
