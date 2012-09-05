/**
 * 
 */
package rough;

/**
 * @author Muralidhar Yaragalla
 *
 */

	import java.util.Arrays;
	import java.util.List;

	public class ContactFactory {

	    public static List create() {
	        ContactBean stub = new ContactBean();
	        stub.setFirstName("John");
	        stub.setLastName("Smith");

	        AddressBean address1 = new AddressBean();
	        address1.setType("Home");
	        address1.setAddress("123 Fake St\nFaketown\nFK 12345");
	        AddressBean address2 = new AddressBean();
	        address2.setType("Work");
	        address2.setAddress("321 Bogus St\nFaketown\nFK 12345");
	        stub.setAddresses(Arrays.asList(address1, address2));

	        PhoneBean phone1 = new PhoneBean();
	        phone1.setType("Home");
	        phone1.setNumber("03 9876 1234");
	        PhoneBean phone2 = new PhoneBean();
	        phone2.setType("Work");
	        phone2.setNumber("03 1234 9876");
	        PhoneBean phone3 = new PhoneBean();
	        phone3.setType("Mobile");
	        phone3.setNumber("0432 123 456");
	        stub.setPhones(Arrays.asList(phone1, phone2, phone3));

	        return Arrays.asList(stub);
	    }
	}
