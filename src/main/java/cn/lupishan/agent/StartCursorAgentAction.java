package cn.lupishan.agent;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import org.jetbrains.annotations.NotNull;

import java.awt.event.InputEvent;

public class StartCursorAgentAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) return;

        InputEvent ie = e.getInputEvent();
        boolean forceRestart = ie != null && (ie.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) != 0;

        ApplicationManager.getApplication().invokeLater(() -> {
            try {
                ToolWindow tw = ToolWindowManager.getInstance(project)
                        .getToolWindow(CursorToolWindowFactory.TOOL_WINDOW_ID);
                if (tw == null) {
                    CursorAgentUtils.notify(project, "未找到工具窗：" + CursorToolWindowFactory.TOOL_WINDOW_ID, NotificationType.ERROR);
                    return;
                }
                // 打开并聚焦
                tw.activate(null, true);

                var cm = tw.getContentManager();

                // 没内容就创建一次（createToolWindowContent 内部会 autorun）
                if (cm.getContentCount() == 0) {
                    new CursorToolWindowFactory().createToolWindowContent(project, tw);
                    return; // autorun 会负责启动
                }

                // Shift = 强制重启：销毁并重建内容（历史清零，autorun 再次启动）
                if (forceRestart) {
                    // 移除已选内容（或全部移除也行）
                    Content selected = cm.getSelectedContent();
                    if (selected != null) {
                        cm.removeContent(selected, true);
                    } else {
                        // 兜底：把所有内容移除
                        for (Content c : cm.getContents()) {
                            cm.removeContent(c, true);
                        }
                    }
                    new CursorToolWindowFactory().createToolWindowContent(project, tw);
                    return; // 不要在这里执行任何命令
                }

                // 非重启：什么都不做（会话已在 autorun 中启动过），仅保持聚焦
            } catch (Throwable ex) {
                CursorAgentUtils.notify(project, "执行失败：" + ex.getMessage(), NotificationType.ERROR);
            }
        });
    }
}