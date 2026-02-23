package cz.osu.swicko1backend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {
    @Value("${app.name}")
    private String appName;



    public String getAppName() {
        return appName;
    }

}
