package top.erzhiqian.spock.sample.eshop;

public interface ICreditCardProcessor {

    CreditCardResult sale(int amount,Customer customer);

    CreditCardResult authorize(int amount,Customer customer);

    CreditCardResult capture(String token,Customer customer);

    void shutdown();

}
