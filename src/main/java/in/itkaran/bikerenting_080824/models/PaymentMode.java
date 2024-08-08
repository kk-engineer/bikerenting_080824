package in.itkaran.bikerenting_080824.models;

import jakarta.persistence.Entity;


public enum PaymentMode {
    CREDIT_CARD,
    DEBIT_CARD,
    NET_BANKING,
    UPI,
}
