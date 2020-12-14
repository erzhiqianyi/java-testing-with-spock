package top.erzhiqian.spock.invoice;

import java.util.Objects;

public class Invoice {
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Invoice invoice = (Invoice) o;
        return name.equals(invoice.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
