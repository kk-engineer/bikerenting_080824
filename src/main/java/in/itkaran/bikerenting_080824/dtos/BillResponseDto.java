package in.itkaran.bikerenting_080824.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillResponseDto {
    private String pnr;
    private String customerName;
    private String vehicleRegNo;
    private String vehicleModel;
    private int amount;
}
