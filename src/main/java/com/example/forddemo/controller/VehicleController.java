package com.example.forddemo.controller;

import com.example.forddemo.data.repository.VehicleRepository;
import com.example.forddemo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class VehicleController {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/vehicleInformation/submitVehicle")
    @ResponseBody
    public VehiclePostResponse submitVehicle(@RequestBody List<Vehicle> vehicles){

        List<VehicleTable> tableEntries = vehicles.stream()
                .map(this::translateVehicleToVehicleTable)
                .collect(Collectors.toList());

        Iterable<VehicleTable> response = vehicleRepository.saveAll(tableEntries);

        List<Long> collectedIds = StreamSupport.stream(response.spliterator(), false)
                .map(VehicleTable::getVehicleId)
                .collect(Collectors.toList());
        return new VehiclePostResponse("OK",200,collectedIds.toString());

    }

    @GetMapping("/getVehicleInformation")
    @ResponseBody
    public ResponseEntity<VehicleResponse> getVehicleInformation() {
        List<VehicleTable> queryResult = StreamSupport.stream(vehicleRepository.findAll().spliterator(),false).collect(Collectors.toList());
        return ResponseEntity.ok(new VehicleResponse(queryResult.stream()
                .map(this::translateVehicleTableToVehicle)
                .collect(Collectors.toList())));
    }

    @GetMapping("/getVehicleModelName/{modelName}")
    @ResponseBody
    public ResponseEntity<VehicleResponse>  getVehiclesWithModel(@PathVariable("modelName") String modelName) {
        List<VehicleTable> queryResult = jdbcTemplate.query("Select * from vehicle_table where model = ?", new Object[]{modelName}, new BeanPropertyRowMapper<>(VehicleTable.class));
        return ResponseEntity.ok(new VehicleResponse(queryResult.stream()
                .map(this::translateVehicleTableToVehicle)
                .collect(Collectors.toList())));

    }


    @GetMapping("/getVehiclePrice/{from}/{to}")
    @ResponseBody
    public  ResponseEntity<VehicleResponse> getVehiclePrice(@PathVariable("from") String from,@PathVariable("to") String to) {
        List<VehicleTable> queryResult = jdbcTemplate.query("Select * from vehicle_table where finalprice between ? and ?", new Object[]{from,to}, new BeanPropertyRowMapper<>(VehicleTable.class));
        return ResponseEntity.ok(new VehicleResponse(queryResult.stream()
                .map(this::translateVehicleTableToVehicle)
                .collect(Collectors.toList())));
    }

    @GetMapping("/getVehicleByFeatures/{exterior}/{interior}")
    @ResponseBody
    public ResponseEntity<VehicleResponse> getVehicleByFeatures(@PathVariable("exterior") String exterior, @PathVariable("interior") String interior) {

        if(exterior.length()<=2 || interior.length()<=2){
            return ResponseEntity.status(400).body(new VehicleResponse("Fail", "Please Enter 3 or more Characters"));
        }
        List<VehicleTable> queryResult = jdbcTemplate.query("Select * from vehicle_table where exterior like CONCAT('%',?,'%') OR interior like CONCAT('%',?,'%') ", new Object[]{exterior, interior}, new BeanPropertyRowMapper<>(VehicleTable.class));
        return ResponseEntity.status(200).body(new VehicleResponse(queryResult.stream()
                .map(this::translateVehicleTableToVehicle)
                .collect(Collectors.toList())));
    }

    private Vehicle translateVehicleTableToVehicle(VehicleTable table) {
        long vehicleid = table.getVehicleId();
        VehiclePrice price = new VehiclePrice(table.getMsrp(), table.getSavings(), table.getFinalprice());
        VehicleFeature features = new VehicleFeature(table.getExterior().split(","), table.getInterior().split(","));
        VehicleDetails details = new VehicleDetails(table.getModel(), table.getModelYear(), table.getBodyStyle(), table.getBodyStyle(), table.getEngine(), table.getDrivetype(), table.getColor(), table.getMpg(), features);

        Vehicle vehicle = new Vehicle(vehicleid, details, price);
        return vehicle;
    }

    private VehicleTable translateVehicleToVehicleTable(Vehicle vehicle) {
        String[] exterior = vehicle.getVehicleDetails().getVehicleFeature().getExterior();
        String joinedExteriors = Arrays.asList(exterior)
                .stream()
                .collect(Collectors.joining(","));

        String[] interior = vehicle.getVehicleDetails().getVehicleFeature().getInterior();
        String joinedInteriors = Arrays.asList(exterior)
                .stream()
                .collect(Collectors.joining(","));

        VehicleTable table = new VehicleTable(vehicle.getVehicleId(), vehicle.getVehicleDetails().getMake(),
                vehicle.getVehicleDetails().getModel(), vehicle.getVehicleDetails().getModelYear(),
                vehicle.getVehicleDetails().getBodyStyle(), vehicle.getVehicleDetails().getEngine(),
                vehicle.getVehicleDetails().getDrivetype(), vehicle.getVehicleDetails().getColor(),
                vehicle.getVehicleDetails().getMpg(), joinedExteriors, joinedInteriors,
                vehicle.getVehiclePrice().getMsrp(),vehicle.getVehiclePrice().getSavings(),
                vehicle.getVehiclePrice().getFinalprice());
        return table;
    }



}
