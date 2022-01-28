package com.org.spring.gym.app.controllers;

import com.org.spring.gym.app.models.Machine;
import com.org.spring.gym.app.models.Product;
import com.org.spring.gym.app.services.EmployeeServices;
import com.org.spring.gym.app.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    @GetMapping("/clients")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<Map<String, Object>> ListAllClients(@RequestParam(required = false) String clientCIN, @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "3") int size) {
        List<Client> listClients;
        Page<Client> data;
        Pageable paging = PageRequest.of(page, size);
        if(clientCIN == null){
            data = employeeServices.listAllClients(paging);
        }else {
            data = employeeServices.listClientsByCIN(clientCIN,paging);
        }
        listClients = data.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("clients", listClients);
        response.put("currentPage", data.getNumber());
        response.put("totalItems", data.getTotalElements());
        response.put("totalPages", data.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/clients")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<Map<String, Object>> addNewClient(@RequestBody Client client) {
        Client data = employeeServices.addNewClient(client);
        Map<String, Object> response = new HashMap<>();
        response.put("client", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/clients/{id}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<Map<String,Object>> updateEmployee(@PathVariable(value = "id") String client_id, @RequestBody Client newClientData) {
        Optional<Client> oldClientData = Optional.ofNullable(employeeServices.getClient(client_id));
        if (oldClientData.isPresent()){
            Client client = oldClientData.get();

            client.setId(client_id);
            client.setCIN(newClientData.getCIN());
            client.setFirstname(newClientData.getFirstname());
            client.setLastname(newClientData.getLastname());
            client.setEmail(newClientData.getEmail());
            client.setPhone(newClientData.getPhone());
            client.setSubscription(newClientData.getSubscription());
            client.setStartAt(newClientData.getStartAt());
            client.setEndAt(newClientData.getEndAt());

            Client user = employeeServices.updateClient(client);
            Map<String,Object> response = new HashMap<>();
            response.put("client",user);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/clients/{id}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public  ResponseEntity deleteEmployee(@PathVariable(value = "id") String client_id){
        employeeServices.deleteClient(client_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<Map<String, Object>> ListAllProducts(@RequestParam(required = false) String productName,
                                                               @RequestParam(required = false) String productRef,
                                                               @RequestParam(required = false) String productCategory,
                                                               @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "3") int size) {
        List<Product> listProducts;
        Page<Product> data;
        Pageable paging = PageRequest.of(page, size);
        if(productCategory == null){
            data = employeeServices.listAllProduct(paging);
        }else {
            data = employeeServices.listProductByCategory(productCategory,paging);
        }

        listProducts = data.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("products", listProducts);
        response.put("currentPage", data.getNumber());
        response.put("totalItems", data.getTotalElements());
        response.put("totalPages", data.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/products")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<Map<String, Object>> addNewProduct(@RequestBody Product product) {
        Product data = employeeServices.addNewProduct(product);
        Map<String, Object> response = new HashMap<>();
        response.put("product", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<Map<String,Object>> updateProduct(@PathVariable(value = "id") String product_id, @RequestBody Product newProductData) {
        Optional<Product> oldProductData = Optional.ofNullable(employeeServices.getProduct(product_id));
        if (oldProductData.isPresent()){
            Product product = oldProductData.get();

            product.setId(product_id);
            product.setRef(newProductData.getRef());
            product.setName(newProductData.getName());
            product.setDesc(newProductData.getDesc());
            product.setCategory(newProductData.getCategory());
            product.setQuantity(newProductData.getQuantity());
            product.setPrice(newProductData.getPrice());

            Product data = employeeServices.updateProduct(product);
            Map<String,Object> response = new HashMap<>();
            response.put("product",data);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products/{id}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public  ResponseEntity deleteProduct(@PathVariable(value = "id") String product_id){
        employeeServices.deleteProduct(product_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/machines")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<Map<String, Object>> ListAllMachines(@RequestParam(required = false) String machineRef,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "3") int size) {
        List<Machine> listMachines;
        Page<Machine> data;
        Pageable paging = PageRequest.of(page, size);
        if(machineRef == null){
            data = employeeServices.listAllMachines(paging);
        }else {
            data = employeeServices.listMachinesByRef(machineRef,paging);
        }

        listMachines = data.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("machines", listMachines);
        response.put("currentPage", data.getNumber());
        response.put("totalItems", data.getTotalElements());
        response.put("totalPages", data.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/machines")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<Map<String, Object>> addNewMachine(@RequestBody Machine machine) {
        Machine data = employeeServices.addNewMachine(machine);
        Map<String, Object> response = new HashMap<>();
        response.put("machine", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/machines/{id}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<Map<String,Object>> updateMachine(@PathVariable(value = "id") String machine_id, @RequestBody Machine newMachineData) {
        Optional<Machine> oldMachineData = Optional.ofNullable(employeeServices.getMachine(machine_id));
        if (oldMachineData.isPresent()){
            Machine machine = oldMachineData.get();

            machine.setId(machine_id);
            machine.setName(newMachineData.getName());
            machine.setRef(newMachineData.getRef());
            machine.setCategory(newMachineData.getCategory());
            machine.setDesc(newMachineData.getDesc());
            machine.setDoesFunction(newMachineData.isDoesFunction());

            Machine data = employeeServices.updateMachine(machine);
            Map<String,Object> response = new HashMap<>();
            response.put("machine",data);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/machines/{id}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public  ResponseEntity deleteMachine(@PathVariable(value = "id") String machine_id){
        employeeServices.deleteMachine(machine_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
