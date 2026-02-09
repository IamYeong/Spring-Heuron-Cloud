package com.heuron.heuroncloud.global.jpa;

import org.springframework.data.domain.AuditorAware;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String updateBy = "";

        if (!StringUtils.hasText(updateBy)) {
            updateBy = "SERVER";
        }

        return Optional.of(updateBy);
    }

}
