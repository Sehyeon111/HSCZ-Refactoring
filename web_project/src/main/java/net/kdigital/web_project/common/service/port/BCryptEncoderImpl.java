package net.kdigital.web_project.common.service.port;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import net.kdigital.web_project.common.infrastructure.BCryptEncoder;

@RequiredArgsConstructor
@Component
public class BCryptEncoderImpl implements BCryptEncoder {

    public final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String encoding(String userPwd) {
        return bCryptPasswordEncoder.encode(userPwd);
    }

}
