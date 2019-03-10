package main.controller.entities;

import main.model.entities.Contract;
import main.model.entities.ForeignCompany;
import main.model.entities.NationalCompany;
import main.model.entities.Trade;

public class FullTrade {

    private Contract contract;
    private Trade trade;
    private NationalCompany nationalCompany;
    private ForeignCompany foreignCompany;

    public FullTrade(Contract contract, Trade trade,
                     NationalCompany nationalCompany, ForeignCompany foreignCompany) {
        this.contract = contract;
        this.trade = trade;
        this.nationalCompany = nationalCompany;
        this.foreignCompany = foreignCompany;
    }

    public Contract getContract() {
        return contract;
    }

    public Trade getTrade() {
        return trade;
    }

    public NationalCompany getNationalCompany() {
        return nationalCompany;
    }

    public ForeignCompany getForeignCompany() {
        return foreignCompany;
    }
}
