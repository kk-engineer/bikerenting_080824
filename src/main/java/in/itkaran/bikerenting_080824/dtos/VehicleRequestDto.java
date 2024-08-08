package in.itkaran.bikerenting_080824.dtos;

import in.itkaran.bikerenting_080824.models.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class VehicleRequestDto {
    private String name;
    private String registration;
    private VehicleType type;
    private VehicleStyleType style;
    private VehicleStatus status;
    private SizeType size;
    private FuelType fuel;
    private int rate;
}
