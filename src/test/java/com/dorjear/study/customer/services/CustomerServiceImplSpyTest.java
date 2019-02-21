package com.dorjear.study.customer.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.dorjear.study.customer.domain.Customer;
import com.dorjear.study.customer.repositories.CustomertRepository;
import com.dorjear.study.customer.services.CustomerServiceImpl;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplSpyTest {
    @Spy
    private CustomerServiceImpl prodServiceSpy;
    @Mock
    private CustomertRepository customerRepository;
    @Mock
    private Customer customer;

    @Test(expected=NullPointerException.class)
    public void shouldThrowNullPointerException_whenGetCustomerByIdIsCalledWithoutContext() throws Exception {
        //Act
        Customer retrievedCustomer = prodServiceSpy.getCustomerById(5);
        //Assert
        assertThat(retrievedCustomer, is(equalTo(customer)));
    }

    public void shouldThrowNullPointerException_whenSaveCustomerIsCalledWithoutContext() throws Exception {
        //Arrange
        Mockito.doReturn(customer).when(customerRepository).save(customer);
        //Act
        Customer savedCustomer = prodServiceSpy.saveCustomer(customer);
        //Assert
        assertThat(savedCustomer, is(equalTo(customer)));
    }

    @Test
    public void shouldVerifyThatGetCustomerByIdIsCalled() throws Exception {
        //Arrange
        Mockito.doReturn(customer).when(prodServiceSpy).getCustomerById(5);
        //Act
        Customer retrievedCustomer = prodServiceSpy.getCustomerById(5);
        //Assert
        Mockito.verify(prodServiceSpy).getCustomerById(5);
    }
    @Test
    public void shouldVerifyThatSaveCustomerIsNotCalled() throws Exception {
        //Arrange
        Mockito.doReturn(customer).when(prodServiceSpy).getCustomerById(5);
        //Act
        Customer retrievedCustomer = prodServiceSpy.getCustomerById(5);
        //Assert
        Mockito.verify(prodServiceSpy,never()).saveCustomer(customer);
    }
}