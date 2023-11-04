package zadatak;

import java.util.Objects;

public class FiksniBroj extends TelefonskiBroj{
    private Grad grad;

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

    public FiksniBroj(Grad grad, String broj) {
        super(broj);
        this.grad = grad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FiksniBroj that = (FiksniBroj) o;
        return grad == that.grad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(grad);
    }

    @Override
    public String ispisi() {
        String pozivni_br = new String();
        switch(grad.toString().toUpperCase()) {
            case "TRAVNIK":
                pozivni_br = "030";
            break;
            case "ODZAK":
                pozivni_br = "031";
            break;
            case "ZENICA":
                pozivni_br = "032";
            case "SARAJEVO":
                pozivni_br = "033";
            break;
            case "LIVNO":
                pozivni_br = "034";
            break;
            case "TUZLA":
                pozivni_br = "035";
            break;
            case "MOSTAR":
                pozivni_br = "036";
            break;
            case "BIHAC":
                pozivni_br = "037";
            break;
            case "GORAZDE":
                pozivni_br = "038";
            break;
            case "SIROKI_BRIJEG":
                pozivni_br = "039";
            break;
            case "BRCKO":
                pozivni_br = "049";
            break;
        }
        StringBuilder s = new StringBuilder(pozivni_br);
        s.append("/");
        s.append(getBroj());

        return s.toString();
    }
}
