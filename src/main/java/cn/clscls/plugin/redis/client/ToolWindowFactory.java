package cn.clscls.plugin.redis.client;

import cn.clscls.plugin.redis.client.dialog.RedisOperationDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class ToolWindowFactory implements com.intellij.openapi.wm.ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content redisContent = contentFactory.createContent(new RedisOperationDialog(project).getContentPanel(), "Redis", false);

        toolWindow.getContentManager().addContent(redisContent);

    }
}
