package com.bjit.recruitment.service;


import com.bjit.recruitment.dto.EmployeeDto;
import com.bjit.recruitment.dto.ResponseDto;
import com.bjit.recruitment.entity.Employee;
import com.bjit.recruitment.enumeration.Response;
import com.bjit.recruitment.repo.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Class for service layer
 * It contains business logics
 * @author Muhtasim-Ul-Alam
 */
@Slf4j
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    /**
     * This method is used for handle  business logic of  create employee.
     * @param employeeDto this the only parameter, which will hold the data.
     * @return responseDto with conformation and created employee data
     */
    public ResponseDto createEmp(EmployeeDto employeeDto) {
        log.info("createEmp() executed in service");
        ResponseDto responseDto = new ResponseDto();
        try {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDto, employee);
            Employee employee2 = employeeRepo.save(employee);
            BeanUtils.copyProperties(employee2, employeeDto);

            return new ResponseDto(Response.CREATED, "EMPLOYEE CREATED", employeeDto);
        }
        catch (Exception e) {
            log.error("Error occurred in execution of createEmp()");
            return new ResponseDto(Response.ERROR, "ERROR OCCURRED", e);
        }
    }

    /**
     * This method is used for handle business logic of get employee list
     * @return responseDto with all data
     */
    public ResponseDto getAll() {
        log.info("getAll() executed in service");
        try {
            List<Employee> employees = employeeRepo.findAll();
            List<EmployeeDto> employeeDtos = new ArrayList<>();

            for (Employee emp : employees) {
                EmployeeDto employeeDto = new EmployeeDto();
                BeanUtils.copyProperties(emp, employeeDto);
                employeeDtos.add(employeeDto);
            }
            return new ResponseDto(Response.SUCCESS, "List Retrieved", employeeDtos);
        }
        catch (Exception e) {
            log.error("Error occurred in execution of getAll()");
            return new ResponseDto(Response.ERROR, "ERROR OCCURRED", e);
        }
    }

    /**
     *  This method is used for handle business logic of get employee list with pagination.
     * @param page this parameter indicates page number
     * @param size this parameter indicates number of data each page
     * @return responseDto with paginated data.
     */
    public ResponseDto getAllPageable(int page, int size) {
        log.info(" getAllPageable() executed in service");
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Employee> employeePage = employeeRepo.findAll(pageable);
            List<EmployeeDto> employeeDtoList = new ArrayList(pageable.getPageSize());

            for (Employee emp : employeePage.getContent()) {
                EmployeeDto employeeDto = new EmployeeDto();
                BeanUtils.copyProperties(emp, employeeDto);
                employeeDtoList.add(employeeDto);
            }
            Page<EmployeeDto> employeeDtoPage = new PageImpl(employeeDtoList, pageable, employeePage.getTotalElements());
            return new ResponseDto(Response.SUCCESS, "Data Retrieved", employeeDtoPage);
        }
        catch (Exception e) {
            log.error("Error occurred in execution of getAllPageable()");
            return new ResponseDto(Response.ERROR, "Data Retrieved", e);
        }
    }

    /**
     * This method is used for handle business logic of get employee using id
     * @param id This is the only parameter which is id of an employee
     * @return responseDto with an employee data.
     */
    public ResponseDto getById(Long id) {
        log.info(" getById() executed in service");
        try {
            if (employeeRepo.existsById(id)) {
                Optional<Employee> empOp = employeeRepo.findById(id);
                Employee emp = empOp.get();
                EmployeeDto employeeDto = new EmployeeDto();
                BeanUtils.copyProperties(emp, employeeDto);

                return new ResponseDto(Response.SUCCESS, "Data Retrived", employeeDto);
            }
            else {
                return new ResponseDto(Response.NotFound, "Id is not valid", id);
            }
        } catch (Exception e) {
            log.error("Error occurred in execution of getById()");
            return new ResponseDto(Response.ERROR, "Error Occured", e);
        }
    }

    /**
     * This method is used for handle business logic of update employee info
     * @param employeeDto This is the first parameter which will hold the updated data.
     * @param id This is second parameter which is id of an employee
     * @return responseDto with confirmation and updated data.
     */
    public ResponseDto update(EmployeeDto employeeDto, Long id) {
        log.info(" update() executed in service");
        try {
            if (employeeRepo.existsById(id)) {
                Optional<Employee> empOp = employeeRepo.findById(id);
                Employee emp = empOp.get();
                BeanUtils.copyProperties(employeeDto, emp,"id");
                Employee emp2 = employeeRepo.saveAndFlush(emp);

                EmployeeDto employeeDto2 = new EmployeeDto();
                BeanUtils.copyProperties(emp2, employeeDto2);
                return new ResponseDto(Response.UPDATED, "Data Updated", employeeDto2);
            }
            else {
                return new ResponseDto(Response.NotFound, "Id is not valid", id);
            }
        }
        catch (Exception e) {
            log.error("Error occurred in execution of update()");
            return new ResponseDto(Response.ERROR, "ERROR OCCURED", e);
        }
    }

    /**
     * This method handle business logic of delete employee info
     * @param id This is the only parameter which is id of an employee
     * @return responseDto with confirmation
     */
    public ResponseDto delete(Long id) {
        log.info(" delete() executed in service");
        try {
            if (employeeRepo.existsById(id)) {
                employeeRepo.deleteById(id);
                return new ResponseDto(Response.DELETED, "successfully deleted", id);
            }
            else {
                return new ResponseDto(Response.NotFound, "Id is not valid", id);
            }
        }
        catch (Exception e) {
            log.error("Error occurred in execution of delete()");
            return new ResponseDto(Response.ERROR, "Id is not valid", e);
        }
    }
    public ResponseDto loopInsert(EmployeeDto employeeDto, int number) {

        int i = 0;
        while (i < number) {
            Employee employee = new Employee();
            employee.setName(employeeDto.getName() + "i");
            employee.setFatherName(employeeDto.getFatherName() + i);
            employee.setMotherName(employeeDto.getMotherName() + i);
            employee.setName(employeeDto.getName() + i);
            employee.setDob(employeeDto.getDob());
            employee.setDoj(employeeDto.getDoj());
            employee.setGender(employeeDto.getGender());
            employeeRepo.save(employee);

            i++;
        }
        return new ResponseDto(Response.SUCCESS, "Data Inserted using looping", number);
    }

    public ResponseDto getAllBetweenRange(Long start, Long end) {

        log.info("getAllBetweenRange(int start, int end) executed in service");
        try {
            List<Employee> employees = employeeRepo.findByIdBetween(start,end);
            List<EmployeeDto> employeeDtos = new ArrayList<>();

            for (Employee emp : employees) {
                EmployeeDto employeeDto = new EmployeeDto();
                BeanUtils.copyProperties(emp, employeeDto);
                employeeDtos.add(employeeDto);
            }
            return new ResponseDto(Response.SUCCESS, "List Retrieved", employeeDtos);
        } catch (Exception e) {
            log.error("getAllBetweenRange(int start, int end)");
            return new ResponseDto(Response.ERROR, "ERROR OCCURRED", e);
        }
    }
}
