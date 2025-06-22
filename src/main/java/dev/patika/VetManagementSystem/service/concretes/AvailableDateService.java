package dev.patika.VetManagementSystem.service.concretes;

import dev.patika.VetManagementSystem.core.config.modelMapper.ModelMapper;
import dev.patika.VetManagementSystem.core.utilies.Msg;
import dev.patika.VetManagementSystem.dao.AvailableDateDao;
import dev.patika.VetManagementSystem.dao.DoctorDao;
import dev.patika.VetManagementSystem.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.VetManagementSystem.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.VetManagementSystem.dto.response.availableDate.AvailableDateResponse;
import dev.patika.VetManagementSystem.entities.AvailableDate;
import dev.patika.VetManagementSystem.entities.Doctor;
import dev.patika.VetManagementSystem.exception.ConflictException;
import dev.patika.VetManagementSystem.exception.NotFoundException;
import dev.patika.VetManagementSystem.service.abstracts.IAvailableDateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AvailableDateService implements IAvailableDateService {

    private final AvailableDateDao availableDateDao;
    private final DoctorDao doctorDao;
    private final ModelMapper modelMapper;

    public AvailableDateService(AvailableDateDao availableDateDao, DoctorDao doctorDao, ModelMapper modelMapper) {
        this.availableDateDao = availableDateDao;
        this.doctorDao = doctorDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public AvailableDateResponse save(AvailableDateSaveRequest availableDateSaveRequest) {

        Doctor doctor = doctorDao.findById(availableDateSaveRequest.getDoctorId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        boolean exists = availableDateDao.existsByDoctorAndAvailableDate(doctor, availableDateSaveRequest.getAvailableDate());
        if (exists) {
            throw new ConflictException(Msg.RESOURCE_ALREADY_EXISTS);
        }

        AvailableDate availableDate = new AvailableDate();
        availableDate.setAvailableDate(availableDateSaveRequest.getAvailableDate());
        availableDate.setDoctor(doctor);

        availableDateDao.save(availableDate);

        return modelMapper.forResponse().map(availableDate, AvailableDateResponse.class);
    }

    @Override
    public AvailableDateResponse get(Long id) {

       AvailableDate availableDate = availableDateDao.findById(id).orElseThrow(
                ()-> new NotFoundException(Msg.NOT_FOUND)
        );

       return modelMapper.forResponse().map(availableDate, AvailableDateResponse.class);
    }

    @Override
    public AvailableDateResponse update(AvailableDateUpdateRequest availableDateUpdateRequest) {

        AvailableDate availableDate = modelMapper.forRequest().map(availableDateUpdateRequest,AvailableDate.class);

        if(!availableDateDao.existsById(availableDate.getId())){
            throw new NotFoundException(Msg.NOT_FOUND);
        }

        availableDateDao.save(availableDate);

        return modelMapper.forResponse().map(availableDate,AvailableDateResponse.class);
    }

    @Override
    public Page<AvailableDateResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<AvailableDate> availableDatePage = availableDateDao.findAll(pageable);
        Page<AvailableDateResponse> availableDateResponses = availableDatePage
                .map(availableDate -> modelMapper.forResponse().map(availableDate, AvailableDateResponse.class));
        return availableDateResponses;
    }

    @Override
    public void delete(Long id) {
        if (!availableDateDao.existsById(id)) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        availableDateDao.deleteById(id);
    }
}
