package in.itkaran.bikerenting_080824.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RentalRequestDto {
    private String email;
    private String name;
    private String size;
    private String registration;
    private int days;
}
