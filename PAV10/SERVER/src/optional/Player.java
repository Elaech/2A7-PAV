package optional;

/**
 * clasa ce descrie playerul ce participa la joc
 * aceasta este alcatuita din numarul de ordine si simbolul playerului
 */
public class Player {
    private int order;
    private char mark;
    public Player(int order,char mark) {
        this.order = order;
        this.mark = mark;
    }

    public int getOrder() {
        return order;
    }

    public char getMark() {
        return mark;
    }
}
