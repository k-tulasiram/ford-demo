package com.example.forddemo.data.repository;

import com.example.forddemo.entity.VehicleTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<VehicleTable,Long> {


}
