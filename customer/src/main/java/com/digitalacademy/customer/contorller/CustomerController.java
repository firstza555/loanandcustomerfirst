package com.digitalacademy.customer.contorller;

import com.digitalacademy.customer.model.Customer;
import com.digitalacademy.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public List<Customer> customerList() {
        return customerService.getCustomerList();
//        List<Customer> css = new ArrayList<>();

//    public ArrayList<Map> customerList() {
//        ArrayList<Map> customers = new ArrayList<>();
//        Map<String, String> customer = new HashMap<>();
//        customer.put("1","gig");
//        customer.put("2","net");
//        customer.put("3","first");
//        customers.add(customer);
//        return  customers;
//        Customer cs = new Customer();
//        cs.setId(1L);
//        cs.setFirstName("ryan");
//        cs.setLastName("Gig");
//        cs.setEmail("Firstza555@gmail");
//        cs.setAge(18);
//        css.add(cs);
//
//        cs = new Customer();
//        cs.setId(2L);
//        cs.setFirstName("wow");
//        cs.setLastName("good");
//        cs.setEmail("wow@gmail");
//        cs.setAge(22);
//        css.add(cs);
//
//        return css;
    }
    //get by id
    //localhost:8081/api/customer
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer body){
        Customer customer = customerService.createCustomer(body);
        return  ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id,
                                            @Valid @RequestBody Customer body){
        body.setId(id);
        Customer customer = customerService.updateCustomer(id,body);
        return  customer != null ? ResponseEntity.ok(customer)
                :ResponseEntity.notFound().build();
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        return customerService.deleteById(id) ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

}
