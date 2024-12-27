package com.auth.service.domain.model;

public class Owner {
    private Long id; 
    private PersonalInfo personalInfo; 
    private ContactInfo contactInfo; 
    private AccountInfo accountInfo;

    public Owner(Long id, PersonalInfo personalInfo, ContactInfo contactInfo, AccountInfo accountInfo) {
        this.id = id;
        this.personalInfo = personalInfo;
        this.contactInfo = contactInfo;
        this.accountInfo = accountInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public boolean isAdult() {
        return personalInfo.isAdult();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Owner owner = (Owner) o;
        return id != null ? id.equals(owner.id) : owner.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

        @Override
        public String toString() {
            return "Owner id=" + id + 
                   ", personalInfo=" + personalInfo + 
                   ", contactInfo=" + contactInfo +
                    ", accountInfo=" + accountInfo;
        } 
         
}
