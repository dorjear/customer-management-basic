package com.dorjear.study.customer.services;


import com.dorjear.study.customer.domain.Customer;

public interface CustomerService {
    Iterable<Customer> listAllCustomers();

    Customer getCustomerById(Integer id);

    Customer saveCustomer(Customer customer);

    void deleteCustomer(Integer id);
}
