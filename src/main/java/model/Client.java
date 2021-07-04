package model;


public class Client {
    private int id;
    private String firstname;
    private String lastname;
    private String email;

    public Client(int id, String firstname, String lastname, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public Client()
    {}

    public String getEmail() {
        return email;
    }

    public String getName()
    {
        return firstname + " " + lastname;
    }

    public String getFirstName() { return firstname;
    }

    public String getLastName() {
        return lastname;
    }
}
