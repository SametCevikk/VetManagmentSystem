package dev.patika.VetManagementSystem.controller;

import dev.patika.VetManagementSystem.core.result.Result;
import dev.patika.VetManagementSystem.core.result.ResultData;
import dev.patika.VetManagementSystem.core.result.ResultHelper;
import dev.patika.VetManagementSystem.dto.request.customer.CustomerSaveRequest;
import dev.patika.VetManagementSystem.dto.request.customer.CustomerUpdateRequest;
import dev.patika.VetManagementSystem.dto.response.customer.CustomerResponse;
import dev.patika.VetManagementSystem.service.concretes.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest){

        CustomerResponse customerResponse = customerService.save(customerSaveRequest);

       return ResultHelper.success(customerResponse);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get(@PathVariable("id") Long id){
        CustomerResponse customerResponse = customerService.get(id);

        return ResultHelper.success(customerResponse);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest ){
        CustomerResponse customerResponse = customerService.update(customerUpdateRequest);

        return ResultHelper.success(customerResponse);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CustomerResponse> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize
    ){
        return customerService.cursor(page,pageSize);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        customerService.delete(id);
        return ResultHelper.success();

    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public Page<CustomerResponse> getCustomersByName(
     @RequestParam String name,
     @RequestParam(name = "page", required = false, defaultValue = "0") int page,
     @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize){
       return customerService.getCustomerByName(name,page,pageSize);
    }


}
