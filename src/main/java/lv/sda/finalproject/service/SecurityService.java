package lv.sda.finalproject.service;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String username, String password);
}
