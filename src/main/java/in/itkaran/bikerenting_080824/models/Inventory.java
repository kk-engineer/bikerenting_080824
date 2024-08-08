package in.itkaran.bikerenting_080824.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Inventory extends BaseModel {
    Map<String, Vehicle> allVehicles;
    Map<String, Integer> availableVehicles;
    Map<String, Integer> rentedVehicles;
}
