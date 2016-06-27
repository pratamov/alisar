package com.dual.ideaction.alisar.service;

import com.dual.ideaction.alisar.exception.GeneralException;
import com.dual.ideaction.alisar.exception.LoginException;
import com.dual.ideaction.alisar.object.Consumer;
import com.dual.ideaction.alisar.object.Statistics;
import com.dual.ideaction.alisar.object.Terminal;

import java.util.Date;
import java.util.HashMap;


public interface ServiceSync {
	
    void setConsumerName(String consumerName, String consumerId);
    void setConsumerSwitch(boolean consumerSwitch, String consumerId);
    void setConsumerOn(Date on, String consumerId);
    void setConsumerOff(Date off, String consumerId);
    Date getConsumerOn(String consumerId);
    boolean getConsumerSwitch(String consumerId);
    Date getConsumerOff(String consumerId);
	void setTerminalName(String terminalName, String terminalId, String userId);
    void setConsumptionBound(double terminalConsumption, String terminalId);
    Statistics getStatistics(String terminalId);
    HashMap<String, Consumer> getConsumers(String terminalId);
    String getUserId(String userName, String userPassword) throws LoginException, GeneralException;
    void setUserPhone(String userPhone, String userId);
    void setUserEmail(String userEmail, String userId);
    String getUserPhone(String userId);
    String getUserEmail(String userId);
    boolean addTerminal(String terminalName, String terminalId, String userId);
    HashMap<String, Terminal> getTerminals(String userId);
    
}
