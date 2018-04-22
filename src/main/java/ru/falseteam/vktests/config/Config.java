package ru.falseteam.vktests.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Priority;
import java.util.TimeZone;

@Configuration()
@Priority(999)
public class Config {
    //TODO разобаться с этим моментом
    @Autowired
    void config() {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));
    }
}
