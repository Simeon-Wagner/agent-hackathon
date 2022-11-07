package org.example;


public class Mediator {
    Supplier mSupplier;
    Customer mCustomer;
    int mJobsAmount;

    Mediator(Supplier supplier, Customer customer, int jobsAmount) {
        mCustomer = customer;
        mSupplier = supplier;
        mJobsAmount = jobsAmount;
    }


}
