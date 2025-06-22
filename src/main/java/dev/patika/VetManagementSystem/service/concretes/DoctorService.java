package dev.patika.VetManagementSystem.service.concretes;

import dev.patika.VetManagementSystem.core.config.modelMapper.ModelMapper;
import dev.patika.VetManagementSystem.core.utilies.Msg;
import dev.patika.VetManagementSystem.dao.DoctorDao;
import dev.patika.VetManagementSystem.dto.request.doctor.DoctorSaveRequest;
import dev.patika.VetManagementSystem.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.VetManagementSystem.dto.response.doctor.DoctorResponse;
import dev.patika.VetManagementSystem.entities.Doctor;
import dev.patika.VetManagementSystem.exception.ConflictException;
import dev.patika.VetManagementSystem.exception.NotFoundException;
import dev.patika.VetManagementSystem.service.abstracts.IDoctorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorService implements IDoctorService {

    private final DoctorDao doctorDao;
    private final ModelMapper modelMapper;

    public DoctorService(DoctorDao doctorDao, ModelMapper modelMapper) {
        this.doctorDao = doctorDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public DoctorResponse save(DoctorSaveRequest doctorSaveRequest) {
        Doctor doctor = modelMapper.forRequest().map(doctorSaveRequest,Doctor.class);

        if(doctorDao.existsByMail(doctor.getMail())){
            throw new ConflictException(Msg.RESOURCE_ALREADY_EXISTS);
        }

        doctorDao.save(doctor);

        return modelMapper.forResponse().map(doctor,DoctorResponse.class);

    }

    @Override
    public DoctorResponse get(Long id) {

       Doctor doctor = doctorDao.findById(id).orElseThrow(
                ()-> new NotFoundException(Msg.NOT_FOUND)
        );

        return modelMapper.forResponse().map(doctor,DoctorResponse.class);
    }

    @Override
    public DoctorResponse update(DoctorUpdateRequest doctorUpdateRequest) {


        Doctor doctor = doctorDao.findById(doctorUpdateRequest.getId()).orElseThrow(
                ()-> new NotFoundException(Msg.NOT_FOUND)
        );

        modelMapper.forRequest().map(doctorUpdateRequest,doctor);
        doctorDao.save(doctor);

        return modelMapper.forResponse().map(doctor,DoctorResponse.class);

    }

    @Override
    public Page<DoctorResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Doctor> doctorPage = doctorDao.findAll(pageable);
       return  doctorPage.map(doctor -> modelMapper.forResponse().map(doctor, DoctorResponse.class));
    }

    @Override
    public void delete(Long id) {
        if (!doctorDao.existsById(id)) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        doctorDao.deleteById(id);
    }
}
