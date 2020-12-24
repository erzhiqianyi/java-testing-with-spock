package top.erzhiqian.spock.sample.stamper;

public class Stamper {
    private final AddressDAO addressDAO;

    public Stamper(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public boolean isValid(Long addressID) {
        Address address = addressDAO.load(addressID);
        return null != address.getStreet() && null != address.getPostCode();
    }
}

