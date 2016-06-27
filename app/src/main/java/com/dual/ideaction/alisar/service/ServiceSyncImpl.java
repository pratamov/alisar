package com.dual.ideaction.alisar.service;

import com.dual.ideaction.alisar.exception.ConnectionException;
import com.dual.ideaction.alisar.exception.GeneralException;
import com.dual.ideaction.alisar.exception.LoginException;
import com.dual.ideaction.alisar.object.Consumer;
import com.dual.ideaction.alisar.object.Statistics;
import com.dual.ideaction.alisar.object.Terminal;
import com.dual.ideaction.alisar.utils.AeSimpleSHA1;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;
import com.lambdaworks.redis.RedisConnectionException;
import com.lambdaworks.redis.RedisURI;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ServiceSyncImpl implements ServiceSync {

    public final static String HOST = "216.126.192.36";
    public final static int PORT = 6379;
    public final static String PASSWORD = "89afa2244677e0a0f380991526111eb57f3cfd51";
    public final static int DB = 0;

    public static ServiceSyncImpl instance;

    RedisConnection<String, String> commands;

    public static ServiceSyncImpl getInstance() throws ConnectionException {

        if (instance == null)
            instance = new ServiceSyncImpl();
        return instance;

    }

    public ServiceSyncImpl() throws ConnectionException {
        try {
            RedisURI redisUri = RedisURI.Builder.redis(HOST, PORT).withPassword(PASSWORD).withDatabase(0).build();
            RedisClient client = RedisClient.create(redisUri);
            commands = client.connect();
        } catch (RedisConnectionException e) {
            throw new ConnectionException(ConnectionException.MESSAGE);
        }
    }

    @Override
    public void setConsumerName(String consumerName, String consumerId) {

        commands.set("consumer:" + consumerId + ":name", consumerName);

    }

    @Override
    public void setConsumerSwitch(boolean consumerSwitch, String consumerId) {
        String value = consumerSwitch ? "1" : "0";
        commands.set("consumer:" + consumerId + ":switch", value);

    }

    @Override
    public void setConsumerOn(Date on, String consumerId) {

        commands.set("consumer:" + consumerId + ":on", String.valueOf((int) (on.getTime() / 1000)));

    }

    @Override
    public void setConsumerOff(Date off, String consumerId) {

        commands.set("consumer:" + consumerId + ":off", String.valueOf((int) (off.getTime() / 1000)));

    }

    @Override
    public Date getConsumerOn(String consumerId) {

        String data = commands.get("consumer:" + consumerId + ":on");
        return new Date(Long.valueOf(data) * 1000);

    }

    @Override
    public boolean getConsumerSwitch(String consumerId) {

        String data = commands.get("consumer:" + consumerId + ":switch");
        return Boolean.valueOf(data);
    }

    @Override
    public Date getConsumerOff(String consumerId) {

        String data = commands.get("consumer:" + consumerId + ":off");
        return new Date(Long.valueOf(data) * 1000);

    }

    @Override
    public void setTerminalName(String terminalName, String terminalId, String userId) {

        commands.set("terminal:" + terminalId + ":name", terminalName);

    }

    @Override
    public void setConsumptionBound(double terminalConsumption, String terminalId) {

        commands.set("terminal:" + terminalId + ":consumption_bound", String.valueOf(terminalConsumption));

    }

    @Override
    public Statistics getStatistics(String terminalId) {

        Map<String, String> data = commands.hgetall("terminal:" + terminalId + ":statistics");
        return new Statistics(data);

    }

    @Override
    public HashMap<String, Consumer> getConsumers(String terminalId) {

        HashMap<String, Consumer> consumers = new HashMap<>();
        Map<String, String> data = commands.hgetall("terminal:" + terminalId + ":consumers");
        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String consumerId = (String) pair.getKey();
            String consumerName = (String) pair.getValue();
            boolean consumerSwitch = Boolean.valueOf(commands.get("consumer:"+consumerId+":switch"));
            Consumer consumer = new Consumer();
            consumer.setConsumerId(consumerId);
            consumer.setConsumerName(consumerName);
            consumer.setConsumerSwitch(consumerSwitch);
            consumers.put(consumerId, consumer);
            it.remove();
        }
        return consumers;
    }

    @Override
    public String getUserId(String userName, String userPassword) throws LoginException, GeneralException {

        try {
            String userId = commands.get(AeSimpleSHA1.SHA1(userName + userPassword));
            if (userId == null || userId.equals(""))
                throw new LoginException(LoginException.MESSAGE);
            return userId;
        } catch (NoSuchAlgorithmException e) {
            throw new GeneralException(e);
        } catch (UnsupportedEncodingException e) {
            throw new GeneralException(e);
        }
    }

    @Override
    public void setUserPhone(String userPhone, String userId) {

        commands.set("user:" + userId + ":phone", userPhone);

    }

    @Override
    public void setUserEmail(String userEmail, String userId) {

        commands.set("user:" + userId + ":email", userEmail);

    }

    @Override
    public String getUserPhone(String userId) {

        return commands.get("user:" + userId + ":phone");

    }

    @Override
    public String getUserEmail(String userId) {
        return commands.get("user:" + userId + ":email");
    }

    @Override
    public boolean addTerminal(String terminalName, String terminalId, String userId) {
        commands.hset("user:" + userId + ":terminals", terminalId, terminalName);
        return true;
    }

    @Override
    public HashMap<String, Terminal> getTerminals(String userId) {

        HashMap<String, Terminal> terminals = new HashMap<>();
        Map<String, String> data = commands.hgetall("user:" + userId + ":terminals");
        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String terminalId = (String) pair.getKey();
            String terminalName = (String) pair.getValue();
            System.out.println("ALISAR{terminalId}" + terminalId);
            System.out.println("ALISAR{terminalName}" + terminalName);
            HashMap<String, Consumer> consumers = getConsumers(terminalId);
            Terminal terminal = new Terminal();
            terminal.setTerminalId(terminalId);
            terminal.setTerminalName(terminalName);
            terminal.setConsumers(consumers);
            terminals.put(terminalId, terminal);
            it.remove();
        }

        return terminals;

    }

}
