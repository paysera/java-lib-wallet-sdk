package com.paysera.lib.wallet.entities.requests;

import java.util.List;

public class AppendContactsToContactBookRequest {
    private List<String> phones;
    private List<String> phoneHashes;
    private List<String> emails;
    private List<String> emailHashes;

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getPhoneHashes() {
        return phoneHashes;
    }

    public void setPhoneHashes(List<String> phoneHashes) {
        this.phoneHashes = phoneHashes;
    }

    public List<String> getEmailHashes() {
        return emailHashes;
    }

    public void setEmailHashes(List<String> emailHashes) {
        this.emailHashes = emailHashes;
    }
}