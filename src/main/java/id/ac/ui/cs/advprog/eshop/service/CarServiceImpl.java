package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car create(Car car){
        if (car.getCarId() == null) {
            car.setCarId(UUID.randomUUID().toString());
        }
        carRepository.create(car);
        return car;
    }

    @Override
    public List<Car> findAll(){
        return carRepository.findAll();
    }

    @Override
    public Car findById(String carId){
        return carRepository.findById(carId);
    }

    @Override
    public void update(String carId, Car car){
        carRepository.update(carId, car);
    }

    @Override
    public void deleteCarById(String carId){
        carRepository.delete(carId);
    }
}