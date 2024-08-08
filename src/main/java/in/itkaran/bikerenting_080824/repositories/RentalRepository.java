package in.itkaran.bikerenting_080824.repositories;

import in.itkaran.bikerenting_080824.models.Customer;
import in.itkaran.bikerenting_080824.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    Rental save(Rental rental);
    Optional<Rental> findRentalByPnr(String pnr);
}
