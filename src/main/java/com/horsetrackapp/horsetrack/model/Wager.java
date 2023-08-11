package com.horsetrackapp.horsetrack.model;
public class Wager {
    private int denomination;
    private int billCount;

    public Wager() {
    }

    public Wager(int denomination, int billCount) {
        this.denomination = denomination;
        this.billCount = billCount;
    }

    public int getDenomination() {
        return denomination;
    }

    public int getBillCount() {
        return billCount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Wager{");
        sb.append(", denomination=").append(denomination);
        sb.append(", billCount=").append(billCount);
        sb.append('}');
        return sb.toString();
    }

}