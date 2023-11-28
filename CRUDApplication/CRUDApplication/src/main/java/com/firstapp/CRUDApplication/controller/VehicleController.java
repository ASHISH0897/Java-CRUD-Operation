package com.firstapp.CRUDApplication.controller;


import com.firstapp.CRUDApplication.model.Vehicle;
import com.firstapp.CRUDApplication.repo.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class VehicleController {

    @Autowired
    private VehicleRepo vehicleRepo;

    @GetMapping("/getAllVehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        try {
            List<Vehicle> vehicleList = new ArrayList<>();
            vehicleRepo.findAll().forEach(vehicleList::add);

            if (vehicleList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(vehicleList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getVehicleById/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        Optional<Vehicle> vehicleData = vehicleRepo.findById(id);

        if (vehicleData.isPresent()) {
            return new ResponseEntity<>(vehicleData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addVehicle")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        Vehicle vehicleObj = vehicleRepo.save(vehicle);

        return new ResponseEntity<>(vehicleObj, HttpStatus.OK);

    }

    @PostMapping("/updateVehicleById/{id}")
    public ResponseEntity<Vehicle> updateVehicleById(@PathVariable Long id, @RequestBody Vehicle newVehicleData) {
        Optional<Vehicle> oldvehicleData = vehicleRepo.findById(id);

        if (oldvehicleData.isPresent()) {
            Vehicle updatedVehicleData = oldvehicleData.get();
            updatedVehicleData.setMake(newVehicleData.getMake());
            updatedVehicleData.setModel(newVehicleData.getModel());
//            updatedVehicleData.setCapacity(newVehicleData.getCapacity());
//            updatedVehicleData.setYear(newVehicleData.getYear());

            Vehicle vehicleObj = vehicleRepo.save(updatedVehicleData);
            return new  ResponseEntity<>(vehicleObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/deleteVehicleById/{id}")
    public ResponseEntity<HttpStatus> deleteVehicleById(@PathVariable Long id) {
        vehicleRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
