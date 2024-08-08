package in.itkaran.bikerenting_080824.dtos;

import in.itkaran.bikerenting_080824.models.SizeType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDto {
    private String name;
    private String email;
    private String phone;
    private SizeType size;
}
