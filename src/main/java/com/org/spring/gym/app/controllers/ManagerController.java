package com.org.spring.gym.app.controllers;

import com.org.spring.gym.app.models.User;
import com.org.spring.gym.app.payload.request.AddEmployeeRequest;
import com.org.spring.gym.app.payload.request.UpdateEmployeeRequest;
import com.org.spring.gym.app.services.ManagerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired
    private ManagerServices managerServices;

    @GetMapping("/")
    public String hello(){
        return "hello manager";
    }

    @GetMapping("/employees")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Map<String, Object>> ListAllEmployees(@RequestParam(required = false) String employeeCIN, @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "3") int size) {
        List<User> listEmployees;
        Page<User> data;
        Pageable paging = PageRequest.of(page, size);
        if(employeeCIN == null){
            data = managerServices.listAllEmployees(paging);
        }else {
            data = managerServices.listEmployeesByCIN(employeeCIN,paging);
        }
        listEmployees = data.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("employees", listEmployees);
        response.put("currentPage", data.getNumber());
        response.put("totalItems", data.getTotalElements());
        response.put("totalPages", data.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/employees")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Map<String, Object>> addNewEmployee(@RequestBody AddEmployeeRequest employee) {
        ResponseEntity<Map<String, Object>> data = managerServices.addNewEmployee(employee);
        User emp = (User) data.getBody().get("employee");
        Map<String, Object> response = new HashMap<>();
        response.put("employees", emp);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/employees/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Map<String,Object>> updateEmployee(@PathVariable(value = "id") String employee_id, @RequestBody UpdateEmployeeRequest employee) {
        Optional<User> userData = Optional.ofNullable(managerServices.getEmployee(employee_id));
        if (userData.isPresent()){
            User emp = userData.get();

            emp.setId(employee_id);
            emp.setCIN(employee.getCIN());
            emp.setFirstname(employee.getFirstname());
            emp.setLastname(employee.getLastname());
            emp.setPhone(employee.getPhone());
            emp.setPosition(employee.getPosition());
            emp.setSpeciality(employee.getSpeciality());
            emp.setUsername(employee.getUsername());
            emp.setEmail(employee.getEmail());

            User user = managerServices.updateEmployee(emp);
            Map<String,Object> response = new HashMap<>();
            response.put("employee",user);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employees/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public  ResponseEntity deleteEmployee(@PathVariable(value = "id") String employee_id){
        managerServices.deleteEmployee(employee_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
