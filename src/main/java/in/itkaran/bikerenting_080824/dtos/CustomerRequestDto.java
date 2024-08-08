package in.itkaran.bikerenting_080824.dtos;

import in.itkaran.bikerenting_080824.models.SizeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDto {
    private String name;
    private String email;
    private String phone;
    private SizeType size;
}
