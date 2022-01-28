package hello.hellospring.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class CarController {

    @GetMapping("car")
    public String carList() {
        return "cars/carList";
    }
}
