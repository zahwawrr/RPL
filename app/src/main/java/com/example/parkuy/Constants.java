package com.example.parkuy;

public class Constants {
    public static final String URL = "https://agile-brook-20072.herokuapp.com/";
    public static final String HOME = URL + "api/";
    public static final String LOGIN = HOME + "login";
    public static final String REGISTER = HOME + "register";
    public static final String LOGOUT = HOME + "logout";
    public static final String UPDATE_PROFILE = HOME +"update";
    public static final String getPhoto = HOME + "getphoto";
    public static final String SEND_ID_QR_CODE_PARKIR = HOME + "tempat_parkir/read";
    public static final String MULAI_PARKIR = HOME +"receipt_parkir/create";
    public static final String AKHIRI_PARKIR = HOME + "receipt_parkir/update";
}
