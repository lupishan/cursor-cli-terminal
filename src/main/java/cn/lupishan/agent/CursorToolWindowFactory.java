package cn.lupishan.agent;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.terminal.ShellTerminalWidget;
import org.jetbrains.plugins.terminal.TerminalToolWindowManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CursorToolWindowFactory implements ToolWindowFactory {
    public static final String TOOL_WINDOW_ID = "Cursor CLI Terminal";
    public static final com.intellij.openapi.util.Key<ShellTerminalWidget> WIDGET_KEY =
            com.intellij.openapi.util.Key.create("CURSOR_AGENT_WIDGET");
    private static final com.intellij.openapi.util.Key<Boolean> AUTORUN_DONE_KEY =
            com.intellij.openapi.util.Key.create("CURSOR_AGENT_AUTORUN_DONE");

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        JPanel panel = new JPanel(new BorderLayout());
        String workDir = project.getBasePath() != null ? project.getBasePath() : System.getProperty("user.home");

        // 仍用 TerminalToolWindowManager 创建 widget（最兼容），但随后马上隐藏底部 Terminal 工具窗
        ShellTerminalWidget widget = TerminalToolWindowManager.getInstance(project)
                .createLocalShellWidget(workDir, TOOL_WINDOW_ID);

        panel.add(widget.getComponent(), BorderLayout.CENTER);

        Content content = ContentFactory.getInstance().createContent(panel, "", false);
        content.putUserData(WIDGET_KEY, widget);
        toolWindow.getContentManager().addContent(content);

        // 关键：把底部 Terminal 工具窗收起，避免用户看到两个终端
        ToolWindow term = ToolWindowManager.getInstance(project).getToolWindow("Terminal");
        if (term != null && term.isVisible()) {
            term.hide(null);
        }

        if (Boolean.TRUE.equals(content.getUserData(AUTORUN_DONE_KEY))) return;
        content.putUserData(AUTORUN_DONE_KEY, true);

        ApplicationManager.getApplication().invokeLater(() -> autorun(project, widget, workDir));
    }

    private void autorun(Project project, ShellTerminalWidget widget, String workDir) {
        String agent = CursorAgentUtils.resolveAgentAbsolutePath(new File(workDir));
        if (agent == null) {
            // 不再往终端 echo；用 Balloon 更干净
            CursorAgentUtils.notify(project, "未检测到 'cursor-agent' (CLI)。请先安装并确保其在 PATH 中可用。", NotificationType.WARNING);
            return;
        }
        // 只执行 agent，一行即可；不要 cd/clear/echo，避免被 TUI 记录为历史
        CursorAgentUtils.exec(project, widget, "cursor-agent");
    }
}