package in.itkaran.bikerenting_080824.repositories;

import in.itkaran.bikerenting_080824.models.Vehicle;
import in.itkaran.bikerenting_080824.models.VehicleStatus;
import in.itkaran.bikerenting_080824.models.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle save(Vehicle vehicle);
    Optional<Vehicle> findByRegistrationNumber(String registrationNumber);
    Optional<Vehicle> findByIdIs(Long id);
    List<Vehicle> findByVehicleStatus(VehicleStatus vehicleStatus);
    List<Vehicle> findByVehicleType(VehicleType vehicleType);
}
