package hello.rest.service;


import hello.rest.dto.CarDto;
import hello.rest.entity.Car;
import hello.rest.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional(readOnly = true)
    public Boolean isRegistrationCar(Long carNumber) {
        Car car = carRepository.findByCarNumber(carNumber);
        return car.getId() != null;
        //return UserDto.from(userRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }
}
