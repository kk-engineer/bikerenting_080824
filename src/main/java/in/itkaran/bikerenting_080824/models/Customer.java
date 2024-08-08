package in.itkaran.bikerenting_080824.models;

import in.itkaran.bikerenting_080824.dtos.CustomerResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Customer extends BaseModel{
    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    @Enumerated(EnumType.STRING)
    private SizeType sizeType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Rental> rentals;

    public CustomerResponseDto toCustomerResponseDto() {
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setName(name);
        customerResponseDto.setEmail(email);
        customerResponseDto.setPhone(phone);
        customerResponseDto.setSize(sizeType);
        return customerResponseDto;
    }

    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", sizeType=" + sizeType +
                '}';
    }
}
