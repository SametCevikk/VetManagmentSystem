package dev.patika.VetManagementSystem.service.concretes;

import dev.patika.VetManagementSystem.core.config.modelMapper.ModelMapper;
import dev.patika.VetManagementSystem.core.utilies.Msg;
import dev.patika.VetManagementSystem.dao.AnimalDao;
import dev.patika.VetManagementSystem.dao.AppointmentDao;
import dev.patika.VetManagementSystem.dao.DoctorDao;
import dev.patika.VetManagementSystem.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.VetManagementSystem.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.VetManagementSystem.dto.response.appointment.AppointmentResponse;
import dev.patika.VetManagementSystem.entities.Animal;
import dev.patika.VetManagementSystem.entities.Appointment;
import dev.patika.VetManagementSystem.entities.Doctor;
import dev.patika.VetManagementSystem.exception.AppointmentAlreadyExistsException;
import dev.patika.VetManagementSystem.exception.AppointmentDateException;
import dev.patika.VetManagementSystem.exception.AppointmentTimeException;
import dev.patika.VetManagementSystem.exception.NotFoundException;
import dev.patika.VetManagementSystem.service.abstracts.IAppointmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {

    private final AppointmentDao appointmentDao;
    private final DoctorDao doctorDao;
    private final AnimalDao animalDao;
    private final ModelMapper modelMapper;

    public AppointmentService(AppointmentDao appointmentDao, DoctorDao doctorDao, AnimalDao animalDao, ModelMapper modelMapper) {
        this.appointmentDao = appointmentDao;
        this.doctorDao = doctorDao;
        this.animalDao = animalDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public AppointmentResponse save(AppointmentSaveRequest appointmentSaveRequest) {

        Appointment appointment = modelMapper.forRequest().map(appointmentSaveRequest,Appointment.class);

        Doctor doctor = doctorDao.findById(appointmentSaveRequest.getDoctorId()).orElseThrow(
                ()-> new NotFoundException(Msg.NOT_FOUND)
        );

        Animal animal = animalDao.findById(appointmentSaveRequest.getAnimalId()).orElseThrow(
                ()-> new NotFoundException(Msg.NOT_FOUND)
        );

        LocalDateTime appointmentDateTime = appointmentSaveRequest.getAppointmentDate();

        if (appointmentDateTime.getMinute() != 0 || appointmentDateTime.getSecond() != 0) {
            throw new AppointmentTimeException(Msg.INVALID_TIME_ERROR);
        }

        boolean isAvailableDate = doctor.getAvailableDateList().stream()
                .anyMatch(ad -> ad.getAvailableDate().isEqual(appointmentDateTime.toLocalDate()));

        if (!isAvailableDate) {
            throw new AppointmentDateException(Msg.UNAVAILABLE_DATE_ERROR);
        }

        boolean alreadyExists = appointmentDao.existsByDoctorIdAndAppointmentDate(
                doctor.getId(), appointmentDateTime
        );

        if (alreadyExists) {
            throw new AppointmentAlreadyExistsException(Msg.TIME_CONFLICT_ERROR);
        }


        appointment.setAnimal(animal);
        appointment.setDoctor(doctor);

        appointmentDao.save(appointment);

        return modelMapper.forResponse().map(appointment,AppointmentResponse.class);
    }

    @Override
    public AppointmentResponse get(Long id) {
        Appointment appointment = appointmentDao.findById(id).orElseThrow(
                ()-> new NotFoundException(Msg.NOT_FOUND)
        );

        return modelMapper.forResponse().map(appointment,AppointmentResponse.class);
    }

    @Override
    public AppointmentResponse update(AppointmentUpdateRequest appointmentUpdateRequest) {

       Appointment appointment = appointmentDao.findById(appointmentUpdateRequest.getId()).orElseThrow(
                ()-> new NotFoundException(Msg.NOT_FOUND)
        );

         modelMapper.forRequest().map(appointmentUpdateRequest,appointment);

        appointmentDao.save(appointment);

        return modelMapper.forResponse().map(appointment,AppointmentResponse.class);

    }

    @Override
    public Page<AppointmentResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Appointment> appointmentPage = appointmentDao.findAll(pageable);
        return  appointmentPage.map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class));
    }

    @Override
    public void delete(Long id) {
        if (!appointmentDao.existsById(id)) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        appointmentDao.deleteById(id);
    }


    public List<AppointmentResponse> searchAppointmentByDoctorId(Long doctorId, LocalDate startDate, LocalDate finishDate) {

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime finishDateTime = finishDate.atStartOfDay();
        List<Appointment> appointmentList = appointmentDao.findByDoctorIdAndAppointmentDateBetween(doctorId,startDateTime,finishDateTime);
        return appointmentList.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .toList();
    }

    public List<AppointmentResponse> searchAppointmentByAnimalId(Long animalId, LocalDate startDate, LocalDate finishDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime finishDateTime = finishDate.atStartOfDay();

        List<Appointment> appointmentList = appointmentDao.findByAnimalIdAndAppointmentDateBetween(animalId,startDateTime,finishDateTime);
        return appointmentList.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .toList();
    }

}
