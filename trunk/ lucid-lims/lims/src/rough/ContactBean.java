package rough;
import java.util.List;

public class ContactBean {

    private String firstName;
    private String lastName;
    private List addresses;
    private List phones;

    public List getAddresses() {
        return addresses;
    }

    public void setAddresses(List addresses) {
        this.addresses = addresses;
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

    public List getPhones() {
        return phones;
    }

    public void setPhones(List phones) {
        this.phones = phones;
    }
}