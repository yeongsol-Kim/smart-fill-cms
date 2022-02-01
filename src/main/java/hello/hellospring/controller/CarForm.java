package hello.hellospring.controller;

public class CarForm {
    private Long car_number;
    private String car_type;
    private Long years;
    private Long registration_number;

    public Long getCar_number() {
        return car_number;
    }

    public void setCar_number(Long car_number) {
        this.car_number = car_number;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public Long getYears() {
        return years;
    }

    public void setYears(Long years) {
        this.years = years;
    }

    public Long getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(Long registration_number) {
        this.registration_number = registration_number;
    }
}
