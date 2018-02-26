package pers.mao.taobaoshop.ov;

import pers.mao.taobaoshop.pojo.Admin;

public class AdminVo {
    private Admin admin;
    private String autologin;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getAutologin() {
        return autologin;
    }

    public void setAutologin(String autologin) {
        this.autologin = autologin;
    }
}
