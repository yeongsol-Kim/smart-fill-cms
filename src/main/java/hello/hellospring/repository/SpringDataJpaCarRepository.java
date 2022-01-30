package hello.hellospring.repository;

import hello.hellospring.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaCarRepository extends JpaRepository<Car, Long> {

    @Override
    Optional<Car> findById(Long aLong);
}
