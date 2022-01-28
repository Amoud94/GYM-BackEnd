package com.org.spring.gym.app.services;

import com.org.spring.gym.app.models.ERole;
import com.org.spring.gym.app.models.Role;
import com.org.spring.gym.app.models.User;
import com.org.spring.gym.app.payload.request.AddEmployeeRequest;
import com.org.spring.gym.app.repository.ClientRepository;
import com.org.spring.gym.app.repository.RoleRepository;
import com.org.spring.gym.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ManagerServicesImp implements ManagerServices{

    UserRepository userRepository;
    ClientRepository clientRepository;
    RoleRepository roleRepository;
    PasswordEncoder encoder;

    @Autowired
    public ManagerServicesImp(PasswordEncoder encoder,UserRepository userRepository, ClientRepository clientRepository,RoleRepository roleRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> addNewEmployee(AddEmployeeRequest addEmployeeRequest) {
        Map<String,Object> response = new HashMap<>();
        if (userRepository.existsByUsername(addEmployeeRequest.getUsername())) {
            response.put("message","Username already exist");
            response.put("HttpStatus",HttpStatus.BAD_REQUEST);
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }
        if (userRepository.existsByCIN(addEmployeeRequest.getCIN())) {
            response.put("message","CIN already exist");
            response.put("HttpStatus",HttpStatus.BAD_REQUEST);
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }
        if (userRepository.existsByEmail(addEmployeeRequest.getEmail())) {
            response.put("message","Email already exist");
            response.put("HttpStatus",HttpStatus.BAD_REQUEST);
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }
        User emp_data = new User(
                addEmployeeRequest.getCIN(),
                addEmployeeRequest.getFirstname(),
                addEmployeeRequest.getLastname(),
                addEmployeeRequest.getPhone(),
                addEmployeeRequest.getPosition(),
                addEmployeeRequest.getSpeciality(),
                addEmployeeRequest.getEmail(),
                addEmployeeRequest.getUsername(),
                encoder.encode(addEmployeeRequest.getPassword())
        );

        Set<String> autorities = addEmployeeRequest.getRoles();

        Set<Role> roles = new HashSet<>();

        if (autorities == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            autorities.forEach(role -> {
                switch (role) {
                    case "manager":
                        Role managerRole = roleRepository.findByName(ERole.ROLE_MANAGER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(managerRole);

                        break;
                    default:
                        Role employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(employeeRole);
                }
            });
        }

        emp_data.setRoles(roles);
        response.put("employee",userRepository.save(emp_data));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public User getEmployee(String employee_id) {
        return userRepository.findById(employee_id).orElseThrow(
                ()-> new RuntimeException("Error: Client is not found."));
    }

    @Override
    public User updateEmployee(User employee) {
        return userRepository.save(employee);
    }

    @Override
    public void deleteEmployee(String employee_id) {
        userRepository.deleteById(employee_id);
    }

    @Override
    public Page<User> listAllEmployees(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> listEmployeesByCIN(String CIN, Pageable pageable) {
        return userRepository.findByCINContainingIgnoreCase(CIN,pageable);
    }
}
