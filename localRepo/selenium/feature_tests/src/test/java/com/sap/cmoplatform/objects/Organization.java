package com.sap.cmoplatform.objects;


import lombok.Data;

@Data
public class Organization {

    private String OrganizationPNID;

    private String Type;

    private String Role;

    private String OnboardingType;

    private String InterfaceType;

    private String ChangedOn;

    private String CreatedOn;

    private String Status;


    public String getSubPNID() {
        String s = this.getOrganizationPNID();

        return s.substring(s.indexOf('('));
    }

}
