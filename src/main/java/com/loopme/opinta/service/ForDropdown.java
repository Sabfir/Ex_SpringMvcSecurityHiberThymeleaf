package com.loopme.opinta.service;

import com.loopme.opinta.enums.PhoneType;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ManagedBean
public class ForDropdown {
    private List<PhoneType> phoneTypes;

    @ViewScoped
    public List<PhoneType> getPhoneTypes() {
        if (phoneTypes == null) {
            phoneTypes = new ArrayList<>();
            Collections.addAll(phoneTypes, PhoneType.values());
        }
        return phoneTypes;
    }

}
