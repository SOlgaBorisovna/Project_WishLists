package dto;

import com.github.javafaker.Faker;

public class User {
    private Faker faker = new Faker();
    private String name = faker.name().firstName();
    private String email = faker.internet().emailAddress();
    private String password = faker.internet().password();

    public User()
    {
        do {
            name = faker.name().firstName();
        } while (name.length() != 10);
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
}
