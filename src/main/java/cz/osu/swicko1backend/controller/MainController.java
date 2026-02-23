package cz.osu.swicko1backend.controller;

import cz.osu.swicko1backend.model.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {
    @Value("${app.name}")
    private String appName;
    private final AppUserRepository appUserRepository;

    public MainController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping(Value = "/appname")
    public String getAppName() {
        return appName;
    }

//DODĚLAT, z pešova githubu, postlat jméno mu na teams!!!
}
