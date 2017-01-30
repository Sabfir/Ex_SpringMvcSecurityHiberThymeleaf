//package com.loopme.opinta.controller;
//
//import com.loopme.opinta.model.Client;
//import com.loopme.opinta.service.ClientService;
//import lombok.Getter;
//import lombok.Setter;
//import org.primefaces.component.datatable.DataTable;
//import org.primefaces.event.CellEditEvent;
//
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.ViewScoped;
//import javax.faces.context.FacesContext;
//import java.io.Serializable;
//import java.util.List;
//
//@ManagedBean
//@ViewScoped
//@Getter
//@Setter
//public class ClientController implements Serializable{
//	@ManagedProperty("#{clientServiceImpl}")
//	private ClientService clientService;
//
//    private List<Client> clients;
//
//    @PostConstruct
//    public void loadClients() {
//        clients = clientService.getAll();
//    }
//
//    public void onCellEdit(CellEditEvent event) {}
//
//    public void save() {
//        clients.forEach(clientService::update);
//        loadClients();
//        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved", "");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//}
