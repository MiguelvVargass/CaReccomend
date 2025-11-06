package com.recomendacionCarros.demo.controller;

import com.recomendacionCarros.demo.model.Car;
import com.recomendacionCarros.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/carros")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
}
