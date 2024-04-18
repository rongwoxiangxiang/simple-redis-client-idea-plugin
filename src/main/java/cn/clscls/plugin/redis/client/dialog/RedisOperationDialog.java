package cn.clscls.plugin.redis.client.dialog;

import cn.clscls.plugin.redis.client.ui.RedisOperation;
import cn.clscls.plugin.redis.client.util.JRedisClientUtil;
import cn.clscls.plugin.redis.client.util.NotifyHandler;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.params.SetParams;

import javax.swing.*;
import java.awt.event.ActionListener;


public class RedisOperationDialog extends DialogWrapper {

    public RedisOperationDialog(Project project) {
        super(project);
        setTitle("Simple Redis Client");
        init();
    }

    @Override
    protected JComponent createSouthPanel() {
        return null;
    }

    @Override
    protected JComponent createCenterPanel() {
        RedisOperation redisOperation = new RedisOperation();
        redisOperation.getQuery().addActionListener(queryActionListener(redisOperation));
        redisOperation.getSet().addActionListener(setActionListener(redisOperation));
        redisOperation.getGetTtl().addActionListener(ttlActionListener(redisOperation));
        return redisOperation.getMainPanel();
    }


    private ActionListener setActionListener(RedisOperation redisOperation) {
        return e -> {
            try {
                JedisCluster instance = JRedisClientUtil.getInstance(redisOperation.getInputRedisAddr());

                String key = redisOperation.getInputKey();
                String val = redisOperation.getInputValue();
                String ttl = redisOperation.getInputTtl();

                String result = instance.set(key, val, SetParams.setParams().ex(Long.parseLong(ttl)));
                redisOperation.setOutput(result);
            } catch (Exception exception) {
                NotifyHandler.notifyError(exception.getMessage());
            }

        };
    }

    private ActionListener queryActionListener(RedisOperation redisOperation) {
        return e -> {
            try {

                JedisCluster instance = JRedisClientUtil.getInstance(redisOperation.getInputRedisAddr());
                String cache = instance.get(redisOperation.getInputKey());
                if (cache == null) {
                    cache = "no cache found";
                }
                redisOperation.setOutput(cache);
            } catch (Exception exception) {
                NotifyHandler.notifyError(exception.getMessage());
            }

        };
    }

    private ActionListener ttlActionListener(RedisOperation redisOperation) {
        return e -> {
            try {
                JedisCluster instance = JRedisClientUtil.getInstance(redisOperation.getInputRedisAddr());

                String key = redisOperation.getInputKey();
                if (!instance.exists(key)) {
                    redisOperation.setOutput("no cache found or expired");
                } else {
                    long ttl = instance.ttl(key);
                    redisOperation.setOutput("ttl: " + ttl + " s");
                }
            } catch (Exception exception) {
                NotifyHandler.notifyError(exception.getMessage());
            }
        };
    }


}
