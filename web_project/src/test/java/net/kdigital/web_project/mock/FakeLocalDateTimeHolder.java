package net.kdigital.web_project.mock;

import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import net.kdigital.web_project.common.service.port.LocalDateTimeHolder;

@RequiredArgsConstructor
public class FakeLocalDateTimeHolder implements LocalDateTimeHolder {

    public final LocalDateTime localDateTime;

    @Override
    public LocalDateTime setDateTime() {
        return localDateTime;
    }

}
