package dev.patika.VetManagementSystem.service.concretes;

import dev.patika.VetManagementSystem.core.config.modelMapper.ModelMapper;
import dev.patika.VetManagementSystem.core.utilies.Msg;
import dev.patika.VetManagementSystem.dao.AnimalDao;
import dev.patika.VetManagementSystem.dao.CustomerDao;
import dev.patika.VetManagementSystem.dto.request.animal.AnimalSaveRequest;
import dev.patika.VetManagementSystem.dto.request.animal.AnimalUpdateRequest;
import dev.patika.VetManagementSystem.dto.response.animal.AnimalResponse;
import dev.patika.VetManagementSystem.entities.Animal;
import dev.patika.VetManagementSystem.entities.Customer;
import dev.patika.VetManagementSystem.exception.NotFoundException;
import dev.patika.VetManagementSystem.service.abstracts.IAnimalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AnimalService implements IAnimalService {
    private final AnimalDao animalDao;
    private final CustomerDao customerDao;
    private final ModelMapper modelMapper;

    public AnimalService(AnimalDao animalDao, CustomerDao customerDao, ModelMapper modelMapper) {
        this.animalDao = animalDao;
        this.customerDao = customerDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public AnimalResponse save(AnimalSaveRequest animalSaveRequest) {

        Animal animal = modelMapper.forRequest().map(animalSaveRequest, Animal.class);

        Customer customer = customerDao.findById(animalSaveRequest.getCustomerId()).orElseThrow(
                () -> new NotFoundException(Msg.NOT_FOUND)
        );


        animal.setId(null);
        animal.setCustomer(customer);

        animalDao.save(animal);

        return modelMapper.forResponse().map(animal, AnimalResponse.class);

    }

    @Override
    public AnimalResponse get(Long id) {

        Animal animal = animalDao.findById(id).orElseThrow(
                () -> new NotFoundException(Msg.NOT_FOUND)
        );
        return modelMapper.forResponse().map(animal, AnimalResponse.class);
    }

    @Override
    public AnimalResponse update(AnimalUpdateRequest animalUpdateRequest) {

        Animal animal = modelMapper.forRequest().map(animalUpdateRequest, Animal.class);

        Customer customer = customerDao.findById(animalUpdateRequest.getCustomerId()).orElseThrow(
                () -> new NotFoundException(Msg.NOT_FOUND)
        );

        if (!animalDao.existsById(animal.getId())) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }

        animal.setCustomer(customer);

        animalDao.save(animal);

        return modelMapper.forResponse().map(animal, AnimalResponse.class);

    }

    @Override
    public Page<AnimalResponse> cursor(int page, int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Animal> animalPage = animalDao.findAll(pageable);

        return animalPage.map(animal -> modelMapper.forResponse().map(animal, AnimalResponse.class));

    }

    @Override
    public void delete(Long id) {
        if (!animalDao.existsById(id)) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        animalDao.deleteById(id);
    }

    public Page<AnimalResponse> getAnimalByName(String name, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("name").ascending());

        Page<Animal> animalPage = animalDao.findByNameContainingIgnoreCase(name, pageable);

        return animalPage.map(animal -> modelMapper.forResponse().map(animal, AnimalResponse.class));
    }

    public Page<AnimalResponse> getAnimalsByCustomerId(Long customerId,int page, int pageSize ) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Animal> animalsPage = animalDao.findByCustomerId(customerId, pageable);

        return animalsPage.map(animal ->
                modelMapper.forResponse().map(animal, AnimalResponse.class)
        );
    }
}
