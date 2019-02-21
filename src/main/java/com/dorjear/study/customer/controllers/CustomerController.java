package com.dorjear.study.customer.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dorjear.study.customer.domain.Customer;
import com.dorjear.study.customer.services.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/customer")
@Api(value = "customer-management", description = "Operations pertaining to customers")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setcustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "View a list of available customers")
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public List<Customer> list(Model model) {
        Iterable<Customer> customerList = customerService.listAllCustomers();
        return StreamSupport.stream(customerList.spliterator(), false).collect(Collectors.toList());
    }

    @ApiOperation(value = "Search a customer with an ID", response = Customer.class)
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
    public Customer showcustomer(@PathVariable Integer id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        return customer;
    }

    @ApiOperation(value = "Add a customer")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> saveCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return new ResponseEntity<String>("Customer saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a customer")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<String> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        Customer storedCustomer = customerService.getCustomerById(id);
        storedCustomer.setFirstName(customer.getFirstName());
        storedCustomer.setLastName(customer.getLastName());
        storedCustomer.setDateOfBirth(customer.getDateOfBirth());
        customerService.saveCustomer(storedCustomer);
        return new ResponseEntity<String>("Customer updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a customer")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<String>("Customer deleted successfully", HttpStatus.OK);

    }

}
