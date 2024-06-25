package net.kdigital.web_project.mock;

import lombok.RequiredArgsConstructor;
import net.kdigital.web_project.common.infrastructure.BCryptEncoder;

@RequiredArgsConstructor
public class FakeBCryptEncoder implements BCryptEncoder {

    public final String encoded;

    @Override
    public String encoding(String userPwd) {
        return encoded;
    }
}
