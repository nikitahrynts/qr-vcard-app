package by.nces.qr_vcard_app.model;

import by.nces.qr_vcard_app.validation.CustomPattern;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Employee {

    private static final String PHONE_NUMBER_PATTERN = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$";

    @NotBlank(message = "Введите имя!")
    private String firstName;

    @NotBlank(message = "Введите фамилию!")
    private String lastName;

    @Email
    @NotBlank(message = "Введите адрес электронной почты!")
    private String email;

    @CustomPattern(regexp = "^[а-яА-ЯёЁ]+$", message = "Поле должно содержать русские буквы!")
    private String role;

    @Pattern(regexp = PHONE_NUMBER_PATTERN, message = "Номер телефона должен соответствовать следующему примеру: +375291234567")
    @NotBlank(message = "Введите номер телефона!")
    private String mobileNumber;

    @CustomPattern(regexp = PHONE_NUMBER_PATTERN, message = "Номер телефона должен соответствовать следующему примеру: +375291234567")
    private String workNumber;

    @CustomPattern(regexp = "\\d{3}", message = "Номер телефона должен соответствовать следующему примеру: 123")
    private String altWorkNumber;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email, String role, String mobileNumber, String workNumber, String altWorkNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.mobileNumber = mobileNumber;
        this.workNumber = workNumber;
        this.altWorkNumber = altWorkNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getAltWorkNumber() {
        return altWorkNumber;
    }

    public void setAltWorkNumber(String altWorkNumber) {
        this.altWorkNumber = altWorkNumber;
    }
}
