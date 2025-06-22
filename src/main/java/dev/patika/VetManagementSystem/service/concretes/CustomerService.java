package dev.patika.VetManagementSystem.service.concretes;

import dev.patika.VetManagementSystem.core.config.modelMapper.ModelMapper;
import dev.patika.VetManagementSystem.core.utilies.Msg;
import dev.patika.VetManagementSystem.dao.CustomerDao;
import dev.patika.VetManagementSystem.dto.request.customer.CustomerSaveRequest;
import dev.patika.VetManagementSystem.dto.request.customer.CustomerUpdateRequest;
import dev.patika.VetManagementSystem.dto.response.customer.CustomerResponse;
import dev.patika.VetManagementSystem.entities.Customer;
import dev.patika.VetManagementSystem.exception.ConflictException;
import dev.patika.VetManagementSystem.exception.NotFoundException;
import dev.patika.VetManagementSystem.service.abstracts.ICustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerDao customerDao;
    private final ModelMapper modelMapper;

    public CustomerService(CustomerDao customerDao, ModelMapper modelMapper) {
        this.customerDao = customerDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerResponse save(CustomerSaveRequest customerSaveRequest) {
        Customer customer = modelMapper.forRequest().map(customerSaveRequest,Customer.class);

        if(customerDao.existsByMail(customer.getMail())){
            throw new ConflictException(Msg.RESOURCE_ALREADY_EXISTS);
        }

        customerDao.save(customer);

        return modelMapper.forResponse().map(customer,CustomerResponse.class);
    }

    @Override
    public CustomerResponse get(Long id) {

        Customer customer = customerDao.findById(id).orElseThrow(
                ()-> new NotFoundException(Msg.NOT_FOUND)
        );

        return modelMapper.forResponse().map(customer,CustomerResponse.class);
    }

    @Override
    public CustomerResponse update(CustomerUpdateRequest customerUpdateRequest) {

       Customer customer= customerDao.findById(customerUpdateRequest.getId()).orElseThrow(
                ()-> new NotFoundException(Msg.NOT_FOUND)
        );

        modelMapper.forRequest().map(customerUpdateRequest,customer);

        customerDao.save(customer);

        return modelMapper.forResponse().map(customer,CustomerResponse.class);
    }

    @Override
    public Page<CustomerResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Customer> customerPage = customerDao.findAll(pageable);
        return  customerPage.map(customer -> modelMapper.forResponse().map(customer, CustomerResponse.class));
    }

    @Override
    public void delete(Long id) {
        if (!customerDao.existsById(id)) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        customerDao.deleteById(id);
    }

    public Page<CustomerResponse> getCustomerByName(String name, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("name").ascending());

        Page<Customer> customerPage = customerDao.findByNameContainingIgnoreCase(name, pageable);

        return  customerPage.map(customer -> modelMapper.forResponse().map(customer, CustomerResponse.class));
    }


}
