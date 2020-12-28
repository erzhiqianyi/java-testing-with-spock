package top.erzhiqian.spock.sample.eshop;

public class BillableBasket extends Basket {

    private CreditCardProcessor creditCardProcessor;

    public BillableBasket() {
        System.out.println("BillableBasket is starting");
    }


    public void setCreditCardProcessor(CreditCardProcessor creditCardProcessor) {
        this.creditCardProcessor = creditCardProcessor;
    }

    public void checkout() {
        if (null == creditCardProcessor) {
            return;
        }
        creditCardProcessor.charge(getCurrentPrice());
    }
}
