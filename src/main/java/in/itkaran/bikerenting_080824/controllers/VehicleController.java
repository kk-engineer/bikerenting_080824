package in.itkaran.bikerenting_080824.controllers;

import in.itkaran.bikerenting_080824.dtos.VehicleRequestDto;
import in.itkaran.bikerenting_080824.dtos.VehicleResponseDto;
import in.itkaran.bikerenting_080824.exceptions.InvalidVehicleStatusException;
import in.itkaran.bikerenting_080824.exceptions.InvalidVehicleTypeException;
import in.itkaran.bikerenting_080824.models.Vehicle;
import in.itkaran.bikerenting_080824.services.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping()
    ResponseEntity<VehicleResponseDto> addVehicle
            (@RequestBody VehicleRequestDto vehicleRequestDto)
            throws SQLIntegrityConstraintViolationException {
        Vehicle vehicle = vehicleService.addProduct(
                vehicleRequestDto.getName(),
                vehicleRequestDto.getRegistration(),
                vehicleRequestDto.getType(),
                vehicleRequestDto.getStyle(),
                vehicleRequestDto.getSize(),
                vehicleRequestDto.getFuel(),
                vehicleRequestDto.getRate()
        );
        VehicleResponseDto vehicleResponseDto = vehicle.toVehicleResponseDto();
        return new ResponseEntity<>(vehicleResponseDto, HttpStatus.CREATED);
    }

    @PatchMapping("{reg}")
    ResponseEntity<VehicleResponseDto> updateVehicle(@PathVariable("reg") String registrationNumber,
                                                     @RequestBody VehicleRequestDto vehicleRequestDto) {
        Vehicle vehicle = vehicleService.updateVehicleByRegistrationNumber(
                registrationNumber,
                vehicleRequestDto.getName(),
                vehicleRequestDto.getType(),
                vehicleRequestDto.getStyle(),
                vehicleRequestDto.getSize(),
                vehicleRequestDto.getFuel(),
                vehicleRequestDto.getRate(),
                vehicleRequestDto.getStatus()
        );
        VehicleResponseDto vehicleResponseDto = vehicle.toVehicleResponseDto();
        return new ResponseEntity<>(vehicleResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("{reg}")
    ResponseEntity<VehicleResponseDto> deleteVehicle(@PathVariable("reg") String registrationNumber) {
        Vehicle vehicle = vehicleService.deleteVehicleByRegistrationNumber(registrationNumber);
        VehicleResponseDto vehicleResponseDto = vehicle.toVehicleResponseDto();
        return new ResponseEntity<>(vehicleResponseDto, HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<List<VehicleResponseDto>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        List<VehicleResponseDto> vehicleResponseDtos = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleResponseDtos.add(vehicle.toVehicleResponseDto());
        }
        return new ResponseEntity<>(vehicleResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/available")
    ResponseEntity<List<VehicleResponseDto>> getAvailableVehicles() {
        List<Vehicle> vehicles = vehicleService.getAvailableVehicles();
        List<VehicleResponseDto> vehicleResponseDtos = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleResponseDtos.add(vehicle.toVehicleResponseDto());
        }
        return new ResponseEntity<>(vehicleResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    ResponseEntity<List<VehicleResponseDto>> getVehiclesByStatus
            (@PathVariable("status") String status)
            throws InvalidVehicleStatusException {
        List<Vehicle> vehicles = vehicleService.getVehiclesByStatus(status);
        List<VehicleResponseDto> vehicleResponseDtos = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleResponseDtos.add(vehicle.toVehicleResponseDto());
        }
        return new ResponseEntity<>(vehicleResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    ResponseEntity<List<VehicleResponseDto>> getVehiclesByType
            (@PathVariable("type") String type)
            throws InvalidVehicleTypeException {
        List<Vehicle> vehicles = vehicleService.getVehiclesByType(type);
        List<VehicleResponseDto> vehicleResponseDtos = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleResponseDtos.add(vehicle.toVehicleResponseDto());
        }
        return new ResponseEntity<>(vehicleResponseDtos, HttpStatus.OK);
    }
}
