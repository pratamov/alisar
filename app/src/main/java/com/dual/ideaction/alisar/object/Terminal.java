package com.dual.ideaction.alisar.object;

import java.util.HashMap;

public class Terminal {
    private String terminalName;
    private String terminalId;
    //consumerId, Consumer
    private HashMap<String, Consumer> consumers;

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public HashMap<String, Consumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(HashMap<String, Consumer> consumers) {
        this.consumers = consumers;
    }
}