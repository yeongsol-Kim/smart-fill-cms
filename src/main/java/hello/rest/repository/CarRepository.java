package hello.rest.repository;

import hello.rest.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByCarNumber(Long carNumber);
}
