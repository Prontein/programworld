package ru.geekbrains.programworld.api.dtos;

import org.hibernate.validator.constraints.Length;


public class EditProfileDTO {
    private String email;

    @Length(min = 2, max = 255, message = "Введите корректное имя")
    private String firstName;


    @Length(min = 2, max = 255, message = "Введите корректную фамилию")
    private String lastName;

    public EditProfileDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public EditProfileDTO(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
