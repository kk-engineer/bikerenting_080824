package in.itkaran.bikerenting_080824.controllers;

import in.itkaran.bikerenting_080824.dtos.CustomerRequestDto;
import in.itkaran.bikerenting_080824.dtos.CustomerResponseDto;
import in.itkaran.bikerenting_080824.models.Customer;
import in.itkaran.bikerenting_080824.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {

        Customer customer = customerService.addCustomer(
                customerRequestDto.getName(),
                customerRequestDto.getEmail(),
                customerRequestDto.getPhone(),
                customerRequestDto.getSize()
        );
        CustomerResponseDto customerResponseDto = customer.toCustomerResponseDto();
        return new ResponseEntity<>(customerResponseDto, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerResponseDto> customerResponseDtos = new ArrayList<>();
        for (Customer customer : customers) {
            customerResponseDtos.add(customer.toCustomerResponseDto());
        }
        return new ResponseEntity<>(customerResponseDtos, HttpStatus.OK);
    }

}
