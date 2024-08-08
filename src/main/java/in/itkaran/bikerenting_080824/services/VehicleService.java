package in.itkaran.bikerenting_080824.services;

import in.itkaran.bikerenting_080824.exceptions.InvalidVehicleStatusException;
import in.itkaran.bikerenting_080824.exceptions.InvalidVehicleTypeException;
import in.itkaran.bikerenting_080824.models.*;
import in.itkaran.bikerenting_080824.repositories.VehicleRepository;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle addProduct(
            String vehicleName,
            String registrationNumber,
            VehicleType vehicleType,
            VehicleStyleType vehicleStyleType,
            SizeType sizeType,
            FuelType fuelType,
            int rate) throws SQLIntegrityConstraintViolationException {

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleName(vehicleName);
        vehicle.setRegistrationNumber(registrationNumber);
        vehicle.setVehicleType(vehicleType);
        vehicle.setVehicleStyleType(vehicleStyleType);
        vehicle.setSizeType(sizeType);
        vehicle.setFuelType(fuelType);
        vehicle.setRate(rate);
        vehicle.setVehicleStatus(VehicleStatus.AVAILABLE);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return savedVehicle;
    }

    public Vehicle updateVehicleByRegistrationNumber(
            String registrationNumber,
            String vehicleName,
            VehicleType vehicleType,
            VehicleStyleType vehicleStyleType,
            SizeType sizeType,
            FuelType fuelType,
            int rate,
            VehicleStatus vehicleStatus) {
        Vehicle vehicle = vehicleRepository.findByRegistrationNumber(registrationNumber).orElseThrow();
        if (vehicleName != null) vehicle.setVehicleName(vehicleName);
        if (vehicleType != null) vehicle.setVehicleType(vehicleType);
        if (vehicleStyleType != null) vehicle.setVehicleStyleType(vehicleStyleType);
        if (sizeType != null) vehicle.setSizeType(sizeType);
        if (fuelType != null) vehicle.setFuelType(fuelType);
        if (rate != 0) vehicle.setRate(rate);
        if (vehicleStatus != null) vehicle.setVehicleStatus(vehicleStatus);

        return vehicleRepository.save(vehicle);
    }

    public Vehicle deleteVehicleByRegistrationNumber(String registrationNumber) {
        Vehicle vehicle = vehicleRepository.findByRegistrationNumber(registrationNumber).orElseThrow();
        vehicle.setDeleted(true);
        return vehicleRepository.save(vehicle);
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findByIdIs(id).orElseThrow();
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public List<Vehicle> getVehiclesByStatus(String status) throws InvalidVehicleStatusException{
        VehicleStatus vehicleStatus = null;
        try {
            vehicleStatus = VehicleStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidVehicleStatusException("Invalid vehicle status" +
                    " status should be one of [AVAILABLE, RENTED, MAINTENANCE]");
        }

        return vehicleRepository.findByVehicleStatus(vehicleStatus);
    }

    public List<Vehicle> getVehiclesByType(String type)
    throws InvalidVehicleTypeException {
        VehicleType vehicleType = null;
        try {
            vehicleType = VehicleType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidVehicleTypeException("Invalid vehicle type" +
                    " type should be one of [BIKE, SCOOTER, CAR]");
        }

        return vehicleRepository.findByVehicleType(vehicleType);
    }

    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findByVehicleStatus(VehicleStatus.AVAILABLE);
    }
}
