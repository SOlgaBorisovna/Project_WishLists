package dto;

import com.github.javafaker.Faker;

import java.time.LocalDateTime;

public class User {
    private Faker faker = new Faker();
    private String name = faker.name().firstName();
    private String email = faker.internet().emailAddress();
    private String password = faker.internet().password();
    private String food = faker.food().fruit();
    private String color = faker.color().name();

    public User()
    {
        name = name + LocalDateTime.now().toString();
    }

    public Faker getFaker() {
        return faker;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getWish() { return food; }

    public String getText() { return color; }
}
