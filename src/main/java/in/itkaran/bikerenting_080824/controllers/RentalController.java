package in.itkaran.bikerenting_080824.controllers;

import in.itkaran.bikerenting_080824.dtos.BillResponseDto;
import in.itkaran.bikerenting_080824.dtos.RentalRequestDto;
import in.itkaran.bikerenting_080824.dtos.RentalResponseDto;
import in.itkaran.bikerenting_080824.models.Rental;
import in.itkaran.bikerenting_080824.services.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping()
    public ResponseEntity<RentalResponseDto> rentVehicle
            (@RequestBody RentalRequestDto rentalRequestDto) {
        RentalResponseDto rentalResponseDto = rentalService.rentVehicle(
                rentalRequestDto.getEmail(),
                rentalRequestDto.getName(),
                rentalRequestDto.getSize(),
                rentalRequestDto.getRegistration(),
                rentalRequestDto.getDays()
        ).toRentalResponseDto();

        return new ResponseEntity<>(rentalResponseDto, org.springframework.http.HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<RentalResponseDto>> getAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        List<RentalResponseDto> rentalResponseDtos = new ArrayList<>();
        for (Rental rental : rentals) {
            rentalResponseDtos.add(rental.toRentalResponseDto());
        }
        return new ResponseEntity<>(rentalResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/bill/{pnr}")
    public ResponseEntity<BillResponseDto> getBill(@PathVariable("pnr") String pnr) {
        BillResponseDto billResponseDto = rentalService.generateBill(pnr).toBillResponseDto();
        return new ResponseEntity<>(billResponseDto, HttpStatus.OK);
    }
}
