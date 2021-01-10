package top.erzhiqian.spock.sample.eshop;

public enum CreditCardResult {
    OK,
    INVALID_CARD,
    NOT_ENOUGH_FUNDS;

    private String token;

    public static boolean isAuthed(CreditCardResult auth) {
        return OK == auth;
    }

    public static boolean inValidCard(CreditCardResult capture) {
        return INVALID_CARD == capture;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
