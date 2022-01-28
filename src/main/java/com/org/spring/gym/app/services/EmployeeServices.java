package com.org.spring.gym.app.services;

import com.org.spring.gym.app.models.Machine;
import com.org.spring.gym.app.models.Product;
import com.org.spring.gym.app.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface EmployeeServices {

    Client addNewClient(Client client);
    Client getClient(String client_id);
    Client updateClient(Client client);
    void deleteClient(String client_id);
    Page<Client> listAllClients(Pageable pageable);
    Page<Client> listClientsByCIN(String CIN, Pageable pageable);
    Page<Client> listClientsByFirstname(String firstname, Pageable pageable);


    Machine addNewMachine(Machine machine);
    Machine getMachine(String machine_id);
    Machine updateMachine(Machine machine);
    void deleteMachine(String machine_id);
    Page<Machine> listAllMachines(Pageable pageable);
    Page<Machine> listMachinesByRef(String ref, Pageable pageable);


    Product addNewProduct(Product product);
    Product getProduct(String product_id);
    Product updateProduct(Product product);
    void deleteProduct(String product_id);
    Page<Product> listAllProduct(Pageable pageable);
    Page<Product> listProductByCategory(String category,Pageable pageable);
    Page<Product> listProductByName(String name,Pageable pageable);
    Page<Product> listProductByRef(String ref,Pageable pageable);
}
