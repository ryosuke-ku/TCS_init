    public int compareTo(Money money) {
        Money first = this.convertToBase();
        Money second = money.convertToBase();
        return Double.compare(first.amount, second.amount);
    }
