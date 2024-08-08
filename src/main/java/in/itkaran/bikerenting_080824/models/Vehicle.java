package in.itkaran.bikerenting_080824.models;

import in.itkaran.bikerenting_080824.dtos.VehicleResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Vehicle extends BaseModel {
    private String vehicleName;
    @Column(unique = true)
    private String registrationNumber;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    @Enumerated(EnumType.STRING)
    private SizeType sizeType;
    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;
    @Enumerated(EnumType.STRING)
    private VehicleStyleType vehicleStyleType;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    private int rate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle")
    List<Rental> rentals;

    public VehicleResponseDto toVehicleResponseDto() {
        VehicleResponseDto vehicleResponseDto = new VehicleResponseDto();
        vehicleResponseDto.setName(vehicleName);
        vehicleResponseDto.setRegistration(registrationNumber);
        vehicleResponseDto.setType(vehicleType);
        vehicleResponseDto.setStyle(vehicleStyleType);
        vehicleResponseDto.setSize(sizeType);
        vehicleResponseDto.setFuel(fuelType);
        vehicleResponseDto.setStatus(vehicleStatus);
        vehicleResponseDto.setRate(rate);

        return vehicleResponseDto;
    }

    public String toString() {
        return "Vehicle{" +
                "vehicleName='" + vehicleName + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", vehicleType=" + vehicleType +
                ", sizeType=" + sizeType +
                ", vehicleStatus=" + vehicleStatus +
                ", vehicleStyleType=" + vehicleStyleType +
                ", fuelType=" + fuelType +
                '}';
    }
}
