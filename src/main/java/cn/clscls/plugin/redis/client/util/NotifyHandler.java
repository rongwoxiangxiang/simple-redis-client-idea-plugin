package cn.clscls.plugin.redis.client.util;

import com.intellij.ide.impl.ProjectUtil;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotifyHandler {

    private static final Logger log = LoggerFactory.getLogger(NotifyHandler.class);


    public static void notifyError(String content) {
        notifyError(content, ProjectUtil.getActiveProject());
    }

    public static void notifyError(String content, Project project) {
        notify(content, NotificationType.ERROR, project);
    }


    public static void notify(String content, NotificationType type, Project project) {
        try {
            NotificationGroup notificationGroup =
                    NotificationGroupManager.getInstance().getNotificationGroup("NotificationGroupId");
            ApplicationManager.getApplication()
                    .invokeLater(() -> notificationGroup.createNotification(content, type).notify(project));
        } catch (Exception e) {
            log.error("NotifyHandler|notify ", e);
        }
    }
}
