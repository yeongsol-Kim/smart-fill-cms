package hello.hellospring.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class CarController {

    @GetMapping("cars")
    public String carList() {
        return "cars/carList";
    }
}
