package top.erzhiqian.spock.invoice;

public class Customer {
    private String email;

    public void hasEmail(String emailAddress){
        setEmail(emailAddress);
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
