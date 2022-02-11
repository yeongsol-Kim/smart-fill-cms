package hello.rest.controller;

import hello.rest.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/cars/numbe")
    public ResponseEntity<String> isCarss() {
        boolean isCar = true;
        if (isCar) {
            return ResponseEntity.ok("true");
        } else {
            return ResponseEntity.ok("false");
        }

    }

    @GetMapping("/cars/number/{number}")
    public ResponseEntity<String> isCar(@PathVariable Long number) {
        boolean isCar = carService.isRegistrationCar(number);
        if (isCar) {
            return ResponseEntity.ok("true");
        } else {
            return ResponseEntity.ok("false");
        }

    }


    @GetMapping("/cars/hello")
    public ResponseEntity<String> hello() {
        boolean isCar = true;
        if (isCar) {
            return ResponseEntity.ok("true");
        } else {
            return ResponseEntity.ok("false");
        }
    }


}
