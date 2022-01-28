package com.org.spring.gym.app.services;

import com.org.spring.gym.app.models.Machine;
import com.org.spring.gym.app.models.Product;
import com.org.spring.gym.app.models.Client;
import com.org.spring.gym.app.repository.ClientRepository;
import com.org.spring.gym.app.repository.MachineRepository;
import com.org.spring.gym.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServicesImp implements EmployeeServices {

    ClientRepository clientRepository;
    ProductRepository productRepository;
    MachineRepository machineRepository;

    @Autowired
    public EmployeeServicesImp(ClientRepository clientRepository, ProductRepository productRepository, MachineRepository machineRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.machineRepository = machineRepository;
    }


    @Override
    public Client addNewClient(Client client) {
        if (clientRepository.existsByCIN(client.getCIN())) {
            clientRepository.existsByEmail(client.getEmail());
        }
        return clientRepository.save(client);
    }

    @Override
    public Client getClient(String client_id) {
        return clientRepository.findById(client_id).orElseThrow(()->
                new RuntimeException("Error: Client is not found.")
        );
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(String client_id) {
        clientRepository.deleteById(client_id);
    }

    @Override
    public Page<Client> listAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Page<Client> listClientsByCIN(String CIN, Pageable pageable) {
        return clientRepository.findByCINContainingIgnoreCase(CIN,pageable);
    }

    @Override
    public Page<Client> listClientsByFirstname(String firstname, Pageable pageable) {
        return clientRepository.findByFirstnameContainingIgnoreCase(firstname,pageable);
    }

    @Override
    public Machine addNewMachine(Machine machine) {
        if (machineRepository.existsByRef(machine.getRef())) {
            return null;
        }
        return machineRepository.save(machine);
    }

    @Override
    public Machine getMachine(String machine_id) {
        return machineRepository.findById(machine_id).orElseThrow(()->
                new RuntimeException("Error: Machine is not found.")
        );
    }

    @Override
    public Machine updateMachine(Machine machine) {
        return machineRepository.save(machine);
    }

    @Override
    public void deleteMachine(String machine_id) {
        machineRepository.deleteById(machine_id);
    }

    @Override
    public Page<Machine> listAllMachines(Pageable pageable) {
        return machineRepository.findAll(pageable);
    }

    @Override
    public Page<Machine> listMachinesByRef(String ref, Pageable pageable) {
        return machineRepository.findByRefContainingIgnoreCase(ref,pageable);
    }

    @Override
    public Product addNewProduct(Product product) {
        if (productRepository.existsByRef(product.getRef())) {
            productRepository.existsByName(product.getName());
        }
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(String product_id) {
        return productRepository.findById(product_id).orElseThrow(()->
                new RuntimeException("Error: Machine is not found.")
        );
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(String product_id) {
        productRepository.deleteById(product_id);
    }

    @Override
    public Page<Product> listAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> listProductByCategory(String category, Pageable pageable) {
        return productRepository.findByCategoryContainingIgnoreCase(category,pageable);
    }

    @Override
    public Page<Product> listProductByName(String name, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase(name,pageable);
    }

    @Override
    public Page<Product> listProductByRef(String ref, Pageable pageable) {
        return productRepository.findByRefContainingIgnoreCase(ref,pageable);
    }
}
