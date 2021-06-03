package com.paysera.lib.wallet.entities.requests;

import com.paysera.lib.wallet.entities.UserIdentity;

import java.util.ArrayList;
import java.util.List;

public class UserRegistrationRequest {

    private String phone;
    private String email;
    private String locale;
    private UserIdentity identity;
    private Credentials credentials;
    private Parameters parameters;

    public UserRegistrationRequest(
        String phone,
        String email,
        String locale,
        UserIdentity identity,
        Credentials credentials,
        Parameters parameters) {
        this.phone = phone;
        this.email = email;
        this.locale = locale;
        this.identity = identity;
        this.credentials = credentials;
        this.parameters = parameters;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public UserIdentity getIdentity() {
        return identity;
    }

    public void setIdentity(UserIdentity identity) {
        this.identity = identity;
    }

    public static class Credentials {
        public static final String TYPE_MAIN_PASSWORD = "main-password";

        private String type;
        private String password;

        public Credentials(String type, String password) {
            this.type = type;
            this.password = password;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class Parameters {
        private List<String> scopes = new ArrayList<>();
        private String phoneLink;
        private String emailLink;

        public Parameters(List<String> scopes) {
            this.scopes = scopes;
        }

        public List<String> getScopes() {
            return scopes;
        }

        public void setScopes(List<String> scopes) {
            this.scopes = scopes;
        }

        public String getPhoneLink() {
            return phoneLink;
        }

        public void setPhoneLink(String phoneLink) {
            this.phoneLink = phoneLink;
        }

        public String getEmailLink() {
            return emailLink;
        }

        public void setEmailLink(String emailLink) {
            this.emailLink = emailLink;
        }
    }
}