package in.itkaran.bikerenting_080824.models;

import in.itkaran.bikerenting_080824.dtos.BillResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Bill extends BaseModel{
    private int amount;
    private Date billDate;
    @OneToOne
    private Rental rentalTicket;
    @Enumerated(EnumType.STRING)
    private BillStatus billStatus;
    @OneToMany (mappedBy = "bill")
    private List<Payment> payments; // partial payment supported

    public BillResponseDto toBillResponseDto() {
        BillResponseDto billResponseDto = new BillResponseDto();
        billResponseDto.setPnr(this.rentalTicket.getPnr());
        billResponseDto.setCustomerName(this.rentalTicket.getCustomer().getName());
        billResponseDto.setVehicleRegNo(this.rentalTicket.getVehicle().getRegistrationNumber());
        billResponseDto.setVehicleModel(this.rentalTicket.getVehicle().getVehicleName());
        billResponseDto.setAmount(this.amount);
        return billResponseDto;
    }
    public String toString() {
        return "Bill{" +
                "amount=" + amount +
                ", billDate=" + billDate +
                ", rentalTicket=" + rentalTicket +
                ", billStatus=" + billStatus +
                ", payments=" + payments +
                '}';
    }
}
