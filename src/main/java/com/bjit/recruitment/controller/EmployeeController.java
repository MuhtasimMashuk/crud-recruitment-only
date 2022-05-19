package com.bjit.recruitment.controller;


import com.bjit.recruitment.dto.EmployeeDto;
import com.bjit.recruitment.dto.ResponseDto;
import com.bjit.recruitment.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * This is controller class
 * It will handle client request
 * @author Muhtasim-Ul-Alam
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * This method receive client request json and send it to service layer to create employee
     * Uri: http://localhost:8081/recruitment/api/v1/employee/save
     * @param employeeDto This is the only parameter which is DTO object, to receive json from client
     * @return responseDto with conformation and created employee data
     */
    @PostMapping("/save")
    public ResponseDto createEmp(@RequestBody EmployeeDto employeeDto) {
        log.info("Request received for createEmp()");
        ResponseDto responseDto = employeeService.createEmp(employeeDto);
        log.info("Response returned for createEmp()");
        return responseDto;
    }

    /**
     * This method receive request from client for get all data of employees from table
     * Uri: http://localhost:8081/recruitment/api/v1/employee/get-all
     * @return responseDto with all data
     */
    @GetMapping("/get-all")
    public ResponseDto getAll() {
        log.info("Request received for getAll()");
        ResponseDto responseDto = employeeService.getAll();
        log.info("Response returned for getAll()");
        return responseDto;
    }

    /**
     * This method receive request from client for retrieve data as a page
     * Uri:http://localhost:8081/recruitment/api/v1/employee/pageable?page=0&size=10
     * @param page this parameter from path variable , indicates page number
     * @param size this parameter indicates the number of data per page
     * @return responseDto with paginated data
     */
    @GetMapping("/pageable")
    public ResponseDto getAllPageable(@RequestParam int page, @RequestParam int size) {
        log.info("Request received for getAllPageable()");
        ResponseDto responseDto = employeeService.getAllPageable(page,size);
        log.info("Response returned for getAllPageable()");
        return responseDto;
    }

    /**
     * This method receive request from client for particular employee data using id
     * uri: http://localhost:8081/recruitment/api/v1/employee/get-by-id/23
     * @param id This is the only parameter which is id of an employee
     * @return response dto with an employee data
     */
    @GetMapping("/get-by-id/{id}")
    public ResponseDto getById(@PathVariable Long id) {
        log.info("Request received for getById()");
        ResponseDto responseDto = employeeService.getById(id);
        log.info("Response returned from getById()");
        return responseDto;
    }

    /**
     * This method receive request from client for update an employee info
     * uri: http://localhost:8081/recruitment/api/v1/employee/update/23
     * @param employeeDto, This is the first parameter which is DTO object, to receive json from client
     * @param id,  This is second parameter which is id of an employee
     * @return responseDto with confirmation with updated data
     */
    @PutMapping("/update/{id}")
    public ResponseDto update(@RequestBody EmployeeDto employeeDto, @PathVariable Long id) {
        log.info("Request received for update()");
        ResponseDto responseDto = employeeService.update(employeeDto, id);
        log.info("Response returned for update()");
        return responseDto;
    }

    /**
     * This method receive request from client for delete an employee info
     * uri:http://localhost:8081/recruitment/api/v1/employee/delete/25
     * @param id This is the only parameter which is id of an employee
     * @return responseDto with confirmation
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete( @PathVariable Long id) {
        log.info("Request received for delete()");
        ResponseDto responseDto = employeeService.delete(id);
        log.info("Response returned for delete()");
        return responseDto;
    }

    @PostMapping("/loop-insert/{number}")
    public ResponseDto loopInsert(@RequestBody EmployeeDto employeeDto, @PathVariable int number){
        log.info("Request received for loopInsert()");
        ResponseDto responseDto = employeeService.loopInsert( employeeDto,number);

        log.info("Response returned for loopInsert()");
        return responseDto;
    }

    @GetMapping("/range")
    public ResponseDto getAllBetweenRange(@RequestParam Long start, @RequestParam Long end) {
        log.info("Request received for getAllBetweenRange()");
        ResponseDto responseDto = employeeService.getAllBetweenRange(start,end);
        log.info("Response returned for getAllBetweenRange()");
        return responseDto;
    }
}
