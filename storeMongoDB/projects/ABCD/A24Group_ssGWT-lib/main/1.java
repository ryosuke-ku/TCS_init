    public SSDate getRestDate(SSDate restDate) {
        restDate.setHours(this.defaultSelectedDate.getHours());
        restDate.setMinutes(this.defaultSelectedDate.getMinutes());
        restDate.setSeconds(00);
        return restDate;
    }
