package com.firstapp.CRUDApplication.repo;

import com.firstapp.CRUDApplication.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
}
