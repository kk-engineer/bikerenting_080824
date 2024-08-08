package in.itkaran.bikerenting_080824.dtos;

import in.itkaran.bikerenting_080824.models.RentalStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RentalResponseDto {
    private String pnr;
    private String customerName;
    private String email;
    private String vehicleName;
    private String registration;
    private Date startTime;
    private Date endTime;
    private RentalStatus rentalStatus;
}
