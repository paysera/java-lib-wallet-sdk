package com.paysera.lib.wallet.filters;

import java.util.List;

public class WalletsFilter extends BaseFilter {
    private List<String> emailList;
    private List<String> phoneList;
    private List<String> emailHashList;
    private List<String> phoneHashList;

    public List<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<String> emailList) {
        this.emailList = emailList;
    }

    public List<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<String> phoneList) {
        this.phoneList = phoneList;
    }

    public List<String> getEmailHashList() {
        return emailHashList;
    }

    public void setEmailHashList(List<String> emailHashList) {
        this.emailHashList = emailHashList;
    }

    public List<String> getPhoneHashList() {
        return phoneHashList;
    }

    public void setPhoneHashList(List<String> phoneHashList) {
        this.phoneHashList = phoneHashList;
    }
}