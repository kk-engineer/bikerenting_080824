package in.itkaran.bikerenting_080824.repositories;

import in.itkaran.bikerenting_080824.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer save(Customer customer);
    Optional<Customer> findByEmail(String email);
}
