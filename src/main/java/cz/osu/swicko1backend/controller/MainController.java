package cz.osu.swicko1backend.controller;

import cz.osu.swicko1backend.model.entity.AppUser;
import cz.osu.swicko1backend.model.entity.Role;
import cz.osu.swicko1backend.model.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

@RestController
public class MainController {
    @Value("${app.name}")
    private String appName;
    private final AppUserRepository appUserRepository;

    public MainController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping(value = "/appName") // localhost:8081/appName
    public String getAppName() {
        return appName;
    }

    @GetMapping("/name") // localhost:8081/name?value=Radim
    public String getName(@RequestParam("value") String name) {
        return "Inserted name is: " + name;
    }

    @GetMapping("/age") // localhost:8081/age?date=1996-04-21    -> 29
    public int getAge(@RequestParam("date") String date) {
        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        int age;
        try {
            age = Period.between(sdf.parse(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
            LocalDate.now()).getYears();
        } catch (ParseException e) {
            age = -1;
        }
        return age;
    }

    @GetMapping("/add")
    public String addUser() {
        AppUser appUser = new AppUser();
        appUser.setUsername("Radim");
        appUser.setPassword("heslo");
        appUser.setRole(Role.ADMIN);
        appUserRepository.save(appUser);
        return "Successfully added user";
    }
}
//ahoj