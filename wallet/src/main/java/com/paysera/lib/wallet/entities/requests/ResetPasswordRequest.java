package com.paysera.lib.wallet.entities.requests;

public class ResetPasswordRequest {
    private String email;
    private String phone;
    private Identity identity;
    private Parameters parameters;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public static class Identity {
        private String code;

        public Identity(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public static class Parameters {
        private String phoneLink;
        private String emailLink;

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