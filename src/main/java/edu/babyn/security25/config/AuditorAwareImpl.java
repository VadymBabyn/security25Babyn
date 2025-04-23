package edu.babyn.security25.config;

/*
@author   vadim
@project   security25
@class  AuditorAwareImpl
@version  1.0.0
@since 23.04.2025 - 14.53
*/

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(System.getProperty("user.name"));
    }
}
