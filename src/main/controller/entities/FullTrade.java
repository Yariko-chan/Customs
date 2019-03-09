package main.controller.entities;

import main.model.entities.Contract;
import main.model.entities.Trade;

public class FullTrade {

    private Contract contract;
    private Trade trade;

    public FullTrade(Trade trade, Contract contract) {
        this.contract = contract;
        this.trade = trade;
    }

    public Contract getContract() {
        return contract;
    }

    public Trade getTrade() {
        return trade;
    }
}
