package in.itkaran.bikerenting_080824.models;

import in.itkaran.bikerenting_080824.dtos.RentalResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@Entity
public class Rental extends BaseModel{
    @Column(unique = true)
    private String pnr;
    private Date startTime;
    private Date endTime;
    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;
    @ManyToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "rentalTicket")
    private Bill bill;

    public RentalResponseDto toRentalResponseDto() {
        RentalResponseDto rentalResponseDto = new RentalResponseDto();
        rentalResponseDto.setPnr(this.pnr);
        rentalResponseDto.setCustomerName(this.customer.getName());
        rentalResponseDto.setEmail(this.customer.getEmail());
        rentalResponseDto.setVehicleName(this.vehicle.getVehicleName());
        rentalResponseDto.setRegistration(this.vehicle.getRegistrationNumber());
        rentalResponseDto.setStartTime(this.startTime);
        rentalResponseDto.setEndTime(this.endTime);
        rentalResponseDto.setRentalStatus(this.rentalStatus);
        return rentalResponseDto;
    }

    public String toString() {
        return "Rental{" +
                "pnr='" + pnr + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", rentalStatus=" + rentalStatus +
                ", vehicle=" + vehicle +
                ", customer=" + customer +
                '}';
    }
}
