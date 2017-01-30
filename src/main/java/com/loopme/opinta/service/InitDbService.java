//package com.loopme.opinta.service;
//
//import com.loopme.opinta.enums.PhoneType;
//import com.loopme.opinta.model.Client;
//import com.loopme.opinta.model.Phone;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class InitDbService {
//    @Autowired
//    private ClientService clientService;
//
//    @PostConstruct
//    public void init() {
//        populateClients();
//    }
//
//    public void populateClients() {
//        List<Client> clients = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Phone phone = new Phone();
//            phone.setNumber("" + i + "-" + i + "-" + i);
//            phone.setType(PhoneType.HOME);
//            phone.setDescription("Some desc " + i);
//            Client client = new Client();
//            client.setName("Name " + i);
//            client.setLastName("Lastname " + i);
//            client.setPhone(phone);
//            clients.add(client);
//        }
//        clients.stream().forEach(clientService::create);
//    }
//}
