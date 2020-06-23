package com.kleer.dojo.configuration.audit;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class AuditorAwareImpl implements org.springframework.data.domain.AuditorAware<UUID> {
    @Override
    public Optional<UUID> getCurrentAuditor() {
        if(SecurityContextHolder.getContext().getAuthentication() == null)
            return Optional.empty();
        else
            return Optional.of(UUID.fromString("254dbb10-b4e9-11ea-b3de-0242ac130004"));

    }
}
