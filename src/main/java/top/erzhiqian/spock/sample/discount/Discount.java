package top.erzhiqian.spock.sample.discount;

public class Discount {

    private static final Integer ONE_HUNDRED_PERCENT_BOUNDS_POINTS = 20000;

    private static final Integer SEVENTY_FIVE_PERCENT_BOUNDS_POINTS = 4000;


    private static final Integer ONE_HUNDRED_PERCENT_DISCOUNT = 100;

    private static final Integer SEVENTY_FIVE_PERCENT_DISCOUNT = 75;

    private static final Integer FIFTY_PERCENT_DISCOUNT = 50;

    private static final Integer TEN_PERCENT_DISCOUNT = 10;

    private static final Integer ORDER_DISCOUNT_MIN_PRICE = 200;

    private static final Integer VIP_MAX_DISCOUNT = 15;
    private final Customer customer;

    public Discount(Customer customer) {
        this.customer = customer;
    }


    public int calculateDiscountPercentFor(Product product, Order ofOrder) {
        if (customer.getBonusPoints() > ONE_HUNDRED_PERCENT_BOUNDS_POINTS) {
            return ONE_HUNDRED_PERCENT_DISCOUNT;
        }
        if (customer.getBonusPoints() > SEVENTY_FIVE_PERCENT_BOUNDS_POINTS) {
            return SEVENTY_FIVE_PERCENT_DISCOUNT;
        }
        if (product.isDailyDeal()) {
            return FIFTY_PERCENT_DISCOUNT;
        }
        int orderDiscount = 0;
        if (ofOrder.getTotalPrice() >= ORDER_DISCOUNT_MIN_PRICE) {
            orderDiscount = TEN_PERCENT_DISCOUNT;
        }
        int actualDiscount = Math.max(orderDiscount, product.getDiscountPercent());

        if (customer.isVip()) {
            return Math.max(VIP_MAX_DISCOUNT, actualDiscount);
        }

        // Normal case
        return actualDiscount;
    }
}
