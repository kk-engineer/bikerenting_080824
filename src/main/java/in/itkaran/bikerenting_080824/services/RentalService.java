package in.itkaran.bikerenting_080824.services;

import in.itkaran.bikerenting_080824.exceptions.VehicleNotAvailableException;
import in.itkaran.bikerenting_080824.models.*;
import in.itkaran.bikerenting_080824.repositories.CustomerRepository;
import in.itkaran.bikerenting_080824.repositories.RentalRepository;
import in.itkaran.bikerenting_080824.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;


    public RentalService(RentalRepository rentalRepository,
                         VehicleRepository vehicleRepository,
                         CustomerRepository customerRepository) {
        this.rentalRepository = rentalRepository;
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
    }

    public Rental rentVehicle(
            String email,
            String name,
            String size,
            String registration,
            int days
    ) {
        Rental rental = new Rental();
        rental.setPnr(RandomStringGenerationService.generateRandomAlphanumericString());
        Calendar calendar = Calendar.getInstance();
        rental.setStartTime(calendar.getTime());
        calendar.add(Calendar.DATE, days);
        rental.setEndTime(calendar.getTime());
        rental.setRentalStatus(RentalStatus.ACTIVE);
        // set bill details
        Bill bill = new Bill();
        bill.setBillDate(calendar.getTime());
        bill.setRentalTicket(rental);
        bill.setBillStatus(BillStatus.PENDING);
        rental.setBill(bill);

        // set vehicle details
        Vehicle vehicle = vehicleRepository.findByRegistrationNumber(registration).orElseThrow();
        if (vehicle.getVehicleStatus() != VehicleStatus.AVAILABLE) {
            throw new VehicleNotAvailableException("Vehicle " + vehicle.getRegistrationNumber() +
                    " is currently not available for renting");
        }
        vehicle.setVehicleStatus(VehicleStatus.BOOKED);
        rental.setVehicle(vehicleRepository.save(vehicle));

        // set customer details
        Optional<Customer> customer = customerRepository.findByEmail(email);
        Customer newCustomer = null;
        if (customer.isEmpty()) {
            newCustomer = new Customer();
            newCustomer.setEmail(email);
            newCustomer.setName(name);
            newCustomer.setSizeType(SizeType.valueOf(size));
            customerRepository.save(newCustomer);
        } else {
            newCustomer = customer.get();
        }
        rental.setCustomer(newCustomer);
        return rentalRepository.save(rental);
    }

    public Bill generateBill(String pnr) {
        Rental rental = rentalRepository.findRentalByPnr(pnr).orElseThrow();
        rental.setRentalStatus(RentalStatus.ENDED);
        // make vehicle available again
        rental.getVehicle().setVehicleStatus(VehicleStatus.AVAILABLE);
        Bill bill = rental.getBill();
        // calculate bill amount
        int rate = rental.getVehicle().getRate();
        int days = (int) ((rental.getEndTime().getTime() - rental.getStartTime().getTime()) / (1000 * 60 * 60 * 24));
        bill.setAmount(rate * days);
        bill.setBillStatus(BillStatus.PENDING);
        rentalRepository.save(rental);
        return bill;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }
}
