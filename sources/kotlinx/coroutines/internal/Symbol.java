package kotlinx.coroutines.internal;

/* loaded from: classes.dex */
public final class Symbol {
    public final String symbol;

    public Symbol(String str) {
        this.symbol = str;
    }

    public String toString() {
        return '<' + this.symbol + '>';
    }
}
