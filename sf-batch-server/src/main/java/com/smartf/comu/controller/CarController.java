package com.smartf.comu.controller;

import com.smartf.comu.domain.Branch;
import com.smartf.comu.domain.Car;
import com.smartf.comu.dto.BranchDto;
import com.smartf.comu.dto.CarDto;
import com.smartf.comu.service.CarService;
import com.smartf.comu.service.CompanyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarController {

    private final CarService carService;
    private final CompanyAdminService companyAdminService;

    @Autowired
    public CarController(CarService carService, CompanyAdminService companyAdminService) {
        this.carService = carService;
        this.companyAdminService = companyAdminService;
    }


    @GetMapping("/cars")
    @PreAuthorize("hasAnyRole('BRANCH', 'ADMIN')")
    public String carList(Model model, Authentication authentication) {
        if (authentication.getAuthorities().toString().equals("[ROLE_BRANCH]")) {
            List<Car> cars = carService.getMyBranchCarList();
            model.addAttribute("cars", cars);
            return "cars/carList";
        } else if (authentication.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
            List<BranchDto> branches = companyAdminService.getMyBranches();
            model.addAttribute("branches", branches);
            return "cars/carListSelect";
        }
        return "redirect:/";
    }

    @GetMapping("/carDelete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String carDelete(@PathVariable Long id) {
        carService.deleteCar(id);
        return "redirect:/cars";
    }

    @GetMapping("/cars/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String createForm(Model model) {
        List<BranchDto> branchDtoList = companyAdminService.getMyBranches();
        model.addAttribute("branches", branchDtoList);
        model.addAttribute("req", "new");
        model.addAttribute("car", Car.builder().build());
        return "cars/createCarSelectForm";
    }

    @PostMapping("/cars/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String create(CarDto carDto) {
        carService.addCar(carDto);

        return "redirect:/cars";
    }


    @GetMapping("/cars/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateForm(Model model, @PathVariable Long id) throws Exception {
        try {
            Car car = carService.getEditCarInfo(id);

            model.addAttribute("req", "update");
            model.addAttribute("id", id);
            model.addAttribute("car", car);

            return "cars/createCarForm";
        } catch (Exception e) {
            return "redirect:/cars";
        }

    }

    @PostMapping("/cars/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateCar(CarDto carDto) throws Exception {
        try {
            carService.updateCar(carDto);
            return "redirect:/cars";
        } catch (Exception e) {
            return "redirect:/cars";
        }
    }


    // 자동차 목록
    @ResponseBody
    @GetMapping("/cars/branch/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Car> getCarsByBranchId(@PathVariable Long id) {
        return carService.getBranchCarList(id);
    }

}
