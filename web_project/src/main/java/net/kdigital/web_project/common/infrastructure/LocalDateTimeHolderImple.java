package net.kdigital.web_project.common.infrastructure;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import net.kdigital.web_project.common.service.port.LocalDateTimeHolder;

@Component
public class LocalDateTimeHolderImple implements LocalDateTimeHolder {

    @Override
    public LocalDateTime setDateTime() {
        return LocalDateTime.now();
    }

}
