package com.digitalacademy.customer.controller;

import com.digitalacademy.customer.contorller.CustomerController;
import com.digitalacademy.customer.customer.CustomerSupportTest;
import com.digitalacademy.customer.model.Customer;
import com.digitalacademy.customer.service.CustomerService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customerController = new CustomerController(customerService);
        mvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
    @DisplayName("Test get customer info by id equals 1 should return loan information")
    @Test

    void  testGetCustomerByIdEquals1() throws  Exception{
        Long reqParam = 1L;

        Customer customer = CustomerSupportTest.getCreateCustomer();
        customer.setId(1L);
        customer.setFirst_name("Ryan");
        customer.setLast_name("Giggs");
        customer.setPhoneNo("66818884484");
        customer.setEmail("gique@gique.com");
        customer.setAge(32);

        when(customerService.getCustomerById(reqParam)).thenReturn(customer);
        MvcResult mcvResult =  mvc.perform(get("/customer/" + reqParam ))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();
        JSONObject resp = new JSONObject(mcvResult.getResponse().getContentAsString());
        JSONObject status = new JSONObject(resp.getString("status"));
        JSONObject data = new JSONObject(resp.getString("data"));

    }
}
