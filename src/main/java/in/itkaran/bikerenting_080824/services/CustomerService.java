package in.itkaran.bikerenting_080824.services;

import in.itkaran.bikerenting_080824.models.Customer;
import in.itkaran.bikerenting_080824.models.SizeType;
import in.itkaran.bikerenting_080824.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(
            String name,
            String email,
            String phone,
            SizeType size
    ) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setSizeType(size);
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
