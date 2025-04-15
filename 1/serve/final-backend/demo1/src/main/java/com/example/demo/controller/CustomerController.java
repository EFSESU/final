package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAll() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer get(@PathVariable String customerId) {
        return customerService.getCustomerById(customerId);
    }


    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

   // @PutMapping("/{customerid}") // ✅ 明确指定路径变量
   // public Customer update(@PathVariable("customerid") String customerid, @RequestBody Customer customer) {
     //   customer.setCustomerid(customerid);
     //   return customerService.saveCustomer(customer);
    //}
   // CustomerController.java 修正后的PUT方法
   @PutMapping("/{customerid}") // ✅ 明确路径变量
   public Customer update(
           @PathVariable("customerid") String customerid,
           @RequestBody Customer customer
   ) {
       customer.setCustomerid(customerid);
       return customerService.saveCustomer(customer);
   }

    @DeleteMapping
    public void delete(@PathVariable String customerid) {
        customerService.deleteCustomer(customerid);
    }
}
