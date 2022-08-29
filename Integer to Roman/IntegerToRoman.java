class Solution {
    public String intToRoman(int num) {
        int curNum = num;
        StringBuilder builder = new StringBuilder();
        while(curNum > 0) {
            final int finalCurNum = curNum;
            RomanNumeralSymbol largestValidSymbol = Arrays.stream(RomanNumeralSymbol.values())
                .filter(symbol -> symbol.getValue() <= finalCurNum)
                .max(Comparator.comparing(RomanNumeralSymbol::getValue)).get();
            builder.append(largestValidSymbol.name());
            curNum -= largestValidSymbol.getValue();
        }
        return builder.toString();
    }
}

enum RomanNumeralSymbol {
    I(1),
    IV(4),
    V(5),
    IX(9),
    X(10),
    XL(40),
    L(50),
    XC(90),
    C(100),
    CD(400),
    D(500),
    CM(900),
    M(1000);
    
    private final int value;
    
    RomanNumeralSymbol(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
