package dev.patika.VetManagementSystem.service.concretes;

import dev.patika.VetManagementSystem.core.config.modelMapper.ModelMapper;
import dev.patika.VetManagementSystem.core.utilies.Msg;
import dev.patika.VetManagementSystem.dao.AnimalDao;
import dev.patika.VetManagementSystem.dao.VaccineDao;
import dev.patika.VetManagementSystem.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.VetManagementSystem.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.VetManagementSystem.dto.response.vaccine.VaccineResponse;
import dev.patika.VetManagementSystem.dto.response.vaccine.VaccineWithAnimalResponse;
import dev.patika.VetManagementSystem.entities.Animal;
import dev.patika.VetManagementSystem.entities.Vaccine;
import dev.patika.VetManagementSystem.exception.ConflictException;
import dev.patika.VetManagementSystem.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineService implements dev.patika.VetManagementSystem.service.abstracts.IVaccineService {

    private final VaccineDao vaccineDAO;
    private final AnimalDao animalDAO;
    private final ModelMapper modelMapper;

    public VaccineService(VaccineDao vaccineDAO, AnimalDao animalDAO, ModelMapper modelMapper) {
        this.vaccineDAO = vaccineDAO;
        this.animalDAO = animalDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public VaccineResponse save(VaccineSaveRequest vaccineSaveRequest) {

        Vaccine vaccine = modelMapper.forRequest().map(vaccineSaveRequest, Vaccine.class);

        vaccine.setId(null);

        Animal animal = animalDAO.findById(vaccineSaveRequest.getAnimalId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        boolean exists = animal.getVaccineList().stream()
                .anyMatch(existsVaccine ->
                          existsVaccine.getName().equalsIgnoreCase(vaccine.getName()) &&
                          existsVaccine.getCode().equalsIgnoreCase(vaccine.getCode()) &&
                          existsVaccine.getProtectionFinishDate() != null &&
                          existsVaccine.getProtectionFinishDate().isAfter(vaccine.getProtectionStartDate()));

        if(exists){
           throw new ConflictException(Msg.RESOURCE_ALREADY_EXISTS);
        }

        vaccine.setAnimal(animal);

        vaccineDAO.save(vaccine);

        return modelMapper.forResponse().map(vaccine, VaccineResponse.class);

    }

    @Override
    public VaccineResponse get(Long id) {
        Vaccine vaccine = vaccineDAO.findById(id).orElseThrow(
                () -> new NotFoundException(Msg.NOT_FOUND)
        );

        return modelMapper.forResponse().map(vaccine, VaccineResponse.class);

    }

    @Override
    public VaccineResponse update(VaccineUpdateRequest vaccineUpdateRequest) {

        Vaccine vaccine = modelMapper.forRequest().map(vaccineUpdateRequest, Vaccine.class);

        if (!vaccineDAO.existsById(vaccine.getId())) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }

        vaccineDAO.save(vaccine);

        return modelMapper.forResponse().map(vaccine, VaccineResponse.class);

    }

    @Override
    public Page<VaccineResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Vaccine> vaccinePage = vaccineDAO.findAll(pageable);
        return vaccinePage.map(vaccine -> modelMapper.forResponse().map(vaccine, VaccineResponse.class));

    }

    @Override
    public void delete(Long id) {
        if (!vaccineDAO.existsById(id)) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        vaccineDAO.deleteById(id);
    }


    public List<VaccineWithAnimalResponse> getUnexpiredVaccines(LocalDate start, LocalDate end){

        List<Vaccine> vaccines = vaccineDAO.findByProtectionFinishDateBetween(start, end);

        return vaccines.stream().map(vaccine -> {
            Animal animal = vaccine.getAnimal();
            return new VaccineWithAnimalResponse(
                    vaccine.getName(),
                    vaccine.getCode(),
                    vaccine.getProtectionStartDate(),
                    vaccine.getProtectionFinishDate(),

                    animal.getId(),
                    animal.getName(),
                    animal.getSpecies(),
                    animal.getBreed(),
                    animal.getGender(),
                    animal.getColour(),
                    animal.getDateOfBirth()
            );
        }).collect(Collectors.toList());
    }

    public Page<VaccineResponse> getVaccineByAnimalId(Long id, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Vaccine> vaccinePage = vaccineDAO.findByAnimalId(id,pageable);

        return vaccinePage.map(vaccine -> modelMapper.forResponse().map(vaccine, VaccineResponse.class));
    }
}
