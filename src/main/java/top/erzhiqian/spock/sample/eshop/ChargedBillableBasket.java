package top.erzhiqian.spock.sample.eshop;

import java.util.Date;

public class ChargedBillableBasket extends Basket {
    private ICreditCardProcessor creditCardProcessor;

    public ChargedBillableBasket() {
        this.creditCardProcessor = creditCardProcessor;
    }

    public void checkout(Customer customer) {
        creditCardProcessor.sale(findOrderPrice(), customer);
        creditCardProcessor.shutdown();
    }

    public int findOrderPrice() {
        return contents.entrySet()
                .stream()
                .mapToInt(entry -> countProductPrice(entry.getKey(), entry.getValue()))
                .sum();
    }

    public void setCreditCardProcessor(ICreditCardProcessor creditCardProcessor) {
        this.creditCardProcessor = creditCardProcessor;
    }

    public boolean fullCheckout(Customer customer) {
        CreditCardResult auth = creditCardProcessor.authorize(findOrderPrice(), customer);
        if (!CreditCardResult.isAuthed(auth)) {
            return false;
        }
        boolean canShip = canShipCompletely();
        if (!canShip) {
            return false;
        }
        CreditCardResult capture = creditCardProcessor.capture(new Date().toString() + auth.getToken(), customer);
        if (CreditCardResult.inValidCard(capture)) {
            return false;
        }

        return true;
    }
}
