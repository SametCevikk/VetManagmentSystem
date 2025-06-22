package dev.patika.VetManagementSystem.service.abstracts;

import dev.patika.VetManagementSystem.dto.request.customer.CustomerSaveRequest;
import dev.patika.VetManagementSystem.dto.request.customer.CustomerUpdateRequest;
import dev.patika.VetManagementSystem.dto.response.customer.CustomerResponse;
import org.springframework.data.domain.Page;

public interface ICustomerService {
    CustomerResponse save(CustomerSaveRequest customerSaveRequest);
    CustomerResponse get(Long id);
    CustomerResponse update(CustomerUpdateRequest customerUpdateRequest);
    Page<CustomerResponse> cursor(int page, int pageSize);
    void delete(Long id);
}
