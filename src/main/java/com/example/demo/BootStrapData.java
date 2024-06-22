package com.example.demo;

import com.example.demo.dao.CountryRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;
    private final CountryRepository countryRepository;

    public BootStrapData(CustomerRepository customerRepository,
                         DivisionRepository divisionRepository,
                         CountryRepository countryRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
        this.countryRepository = countryRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        //if statement checks if there is only one customer before running the script to add 5 customers
        if (customerRepository.count() == 1){

            Customer customer1 = new Customer();
            Customer customer2 = new Customer();
            Customer customer3 = new Customer();
            Customer customer4 = new Customer();
            Customer customer5 = new Customer();

            customer1.setFirstName("Josh");
            customer1.setLastName("Maserin");
            customer1.setAddress("5892 Banana Ct");
            customer1.setPostal_code("654165");
            customer1.setPhone("459184563");

            customer2.setFirstName("Sophia");
            customer2.setLastName("Lin");
            customer2.setAddress("5789 Maple Ct");
            customer2.setPostal_code("052489");
            customer2.setPhone("4439876205");

            customer3.setFirstName("Bob");
            customer3.setLastName("Smith");
            customer3.setAddress("21 Martin St");
            customer3.setPostal_code("985981");
            customer3.setPhone("6512309875");

            customer4.setFirstName("Eun");
            customer4.setLastName("Shin");
            customer4.setAddress("842 Fredericko Ave");
            customer4.setPostal_code("981630");
            customer4.setPhone("1598793858");

            customer5.setFirstName("Theo");
            customer5.setLastName("Taylor");
            customer5.setAddress("1234 Washington Ct");
            customer5.setPostal_code("897456");
            customer5.setPhone("4102365478");

            customerRepository.save(customer1);
            customerRepository.save(customer2);
            customerRepository.save(customer3);
            customerRepository.save(customer4);
            customerRepository.save(customer5);

            System.out.println("Started in Bootstrap");
            System.out.println("Number of customers:" + customerRepository.count());

        }
        else {
            return;
        }

    }
}
