package in.itkaran.bikerenting_080824.dtos;

import in.itkaran.bikerenting_080824.models.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleResponseDto {
    private String name;
    private String registration;
    private VehicleType type;
    private VehicleStyleType style;
    private VehicleStatus status;
    private SizeType size;
    private FuelType fuel;
    private int rate;
}
