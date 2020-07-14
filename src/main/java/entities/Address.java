package entities;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;

    private String City;

    public Address() {
    }

    public Address(String street, String city) {
        this.street = street;
        City = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
}
