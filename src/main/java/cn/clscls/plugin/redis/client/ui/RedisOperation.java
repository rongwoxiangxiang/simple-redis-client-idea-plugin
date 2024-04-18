package cn.clscls.plugin.redis.client.ui;


import org.apache.commons.lang3.StringUtils;

import javax.swing.*;

public class RedisOperation {


    private JPanel mainPanel;
    private JTextField inputRedisAddr;
    private JTextField inputKey;
    private JButton query;
    private JButton set;
    private JTextField inputTtl;
    private JTextArea output;
    private JButton getTtl;
    private JTextArea inputValue;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getQuery() {
        return query;
    }

    public JButton getSet() {
        return set;
    }

    public JButton getGetTtl() {
        return getTtl;
    }

    public String getInputRedisAddr() {
        return StringUtils.trim(inputRedisAddr.getText());

    }

    public String getInputKey() {
        return StringUtils.trim(inputKey.getText());
    }

    public String getInputValue() {
        return StringUtils.trim(inputValue.getText());
    }

    public String getInputTtl() {
        return StringUtils.trim(inputTtl.getText());
    }

    public void setOutput(String result) {
        output.setText(result);
    }
}
