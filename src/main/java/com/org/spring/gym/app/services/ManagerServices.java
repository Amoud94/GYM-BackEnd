package com.org.spring.gym.app.services;


import com.org.spring.gym.app.models.User;
import com.org.spring.gym.app.payload.request.AddEmployeeRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Map;


public interface ManagerServices {
    ResponseEntity<Map<String, Object>> addNewEmployee(AddEmployeeRequest employee);
    User getEmployee(String employee_id);
    User updateEmployee(User employee);
    void deleteEmployee(String employee_id);
    Page<User> listAllEmployees(Pageable pageable);
    Page<User> listEmployeesByCIN(String CIN,Pageable pageable);
}
