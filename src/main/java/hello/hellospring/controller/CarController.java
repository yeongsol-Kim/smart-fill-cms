package hello.hellospring.controller;

import hello.hellospring.domain.Car;
import hello.hellospring.domain.Member;
import hello.hellospring.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String carList(Model model) {
        List<Car> cars = carService.findCars();
        model.addAttribute("cars", cars);
        return "cars/carList";
    }

    @GetMapping("/cars/new")
    public String createForm(Model model) {
        return "cars/createCarForm";
    }

    @PostMapping("/cars/new")
    public String create(CarForm form) {
        Car car = new Car();
        car.setCar_number(form.getCar_number());
        car.setCar_type(form.getCar_type());
        car.setYears(form.getYears());
        car.setRegistration_number(form.getRegistration_number());

        carService.insert(car);

        return "redirect:/cars";
    }
}
