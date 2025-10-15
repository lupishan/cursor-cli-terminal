# Cursor CLI Terminal

[English](#english) | [中文](#中文)

---

## English

A JetBrains IDE plugin that runs `cursor-agent` (Cursor AI CLI) directly inside your IDE via an integrated terminal window.

### ✨ Features

- **One-click access**: Opens in a dedicated tool window on the right side
- **Auto-start**: Automatically launches `cursor-agent` when you open the tool window
- **Seamless integration**: Works like a native IDE terminal
- **Smart detection**: Automatically finds `cursor-agent` in your PATH
- **Focus & Restart**: Quick shortcuts to focus or restart the agent (hold Shift to clean restart)
- **Privacy-first**: No network calls, no data collection

### 🌍 Community Builds

- [Claude Code Terminal](https://github.com/hamdiwanis/claude-terminal-intelija-plugin) — Built by [@hamdiwanis](https://github.com/hamdiwanis), adapted from this project to support Claude CLI.

### 📋 Requirements

- IntelliJ IDEA 2023.2+ (or any JetBrains IDE: PyCharm, WebStorm, PhpStorm, etc.)
- `cursor-agent` CLI installed and available in your PATH

### 📦 Installation

**From JetBrains Marketplace:**
1. Open IDE → Settings → Plugins → Marketplace
2. Search for "Cursor CLI Terminal"
3. Click Install

**From Source:**
1. Clone this repository
2. Run `./gradlew buildPlugin`
3. Install the generated ZIP from `build/distributions/` via Settings → Plugins → Install from Disk

### 🚀 Usage

1. Open the "Cursor CLI Terminal" tool window from the right sidebar
2. The `cursor-agent` will start automatically if detected in your PATH
3. Use **Tools → Focus / Restart Cursor CLI Terminal** (`Ctrl+Shift+A` on Windows/Linux, `Cmd+Shift+A` on Mac) to:
   - Focus the tool window (normal click)
   - Restart the session (hold Shift while clicking)

### 🛠️ Development

**Build the plugin:**
```bash
./gradlew buildPlugin
```

**Run in IDE sandbox:**
```bash
./gradlew runIde
```

**Project Structure:**
- `src/main/java/cn/lupishan/agent/` - Plugin source code
  - `CursorToolWindowFactory.java` - Creates the tool window and terminal widget
  - `StartCursorAgentAction.java` - Handles focus/restart actions
  - `CursorAgentUtils.java` - Shared utility methods
- `src/main/resources/` - Plugin resources (icons, plugin.xml)
- `build.gradle` - Build configuration

**Tech Stack:**
- Java 17
- IntelliJ Platform SDK
- Gradle with IntelliJ Plugin

### 🤝 Contributing

Contributions are welcome! Please feel free to submit issues or pull requests.

This plugin can serve as a reference for building similar integrations with other CLI tools (like Claude Code, etc.).

### ❓ FAQ

**Q: "cursor-agent not detected" error**  
A: Make sure `cursor-agent` is installed and in your PATH. Restart the IDE after installation.

**Q: First-time trust/login prompts**  
A: Follow the interactive prompts in the terminal window.

**Q: Does this work with other JetBrains IDEs?**  
A: Yes! Works with PyCharm, WebStorm, PhpStorm, and all IntelliJ-based IDEs.

### 🔒 Privacy

- No network calls
- No data collection
- All commands execute in the IDE's built-in terminal (fully transparent)

### 📄 License

MIT License - see [LICENSE](LICENSE) file for details.

### ⚠️ Disclaimer

This plugin is an independent project and is **not affiliated with or endorsed by Cursor** or any other third-party vendors. It does not bundle, download, or install `cursor-agent`.

---

## 中文

在 IntelliJ IDEA 右侧工具窗内，以交互式终端运行本地 `cursor-agent`。打开工具窗即可进入，直接在里面操作即可。

### ✨ 功能亮点

- **一键聚焦/重启**: 用快捷键快速切换终端；按住 Shift 即可重建会话
- **自动启动**: PATH 中检测到 `cursor-agent` 即自动运行，无需额外配置
- **界面简洁**: 独立右侧 ToolWindow，隐藏底部 Terminal，避免重复窗口
- **智能提示**: 未检测到 CLI 时自动气泡提醒

### 🌍 社区构建

- [Claude Code Terminal](https://github.com/hamdiwanis/claude-terminal-intelija-plugin) — 由 [@hamdiwanis](https://github.com/hamdiwanis) 基于本项目改编，支持 Claude CLI。

### 📋 要求

- IntelliJ IDEA 2023.2+（或任何 JetBrains IDE：PyCharm、WebStorm、PhpStorm 等）
- 终端中可执行 `cursor-agent`（需自行安装并加入 PATH）

### 📦 安装

**从 Marketplace 安装：**
1. 打开 IDE → Settings → Plugins → Marketplace
2. 搜索 "Cursor CLI Terminal"
3. 点击安装

**从源码安装：**
1. 克隆本仓库
2. 运行 `./gradlew buildPlugin`
3. 在 Settings → Plugins → Install from Disk 中选择 `build/distributions/` 下生成的 ZIP

### 🚀 用法

1. 打开右侧 "Cursor CLI Terminal" 工具窗
2. 若已安装 `cursor-agent` 将自动启动
3. 使用 **Tools → Focus / Restart Cursor CLI Terminal**（`Ctrl+Shift+A`）：
   - 普通点击：聚焦工具窗
   - 按住 Shift：重启会话

### 🛠️ 开发

**构建插件：**
```bash
./gradlew buildPlugin
```

**在沙盒中运行：**
```bash
./gradlew runIde
```

### ❓ 常见问题

**提示未检测到 cursor-agent？**  
请按你系统/团队方式安装，并确保 `cursor-agent` 在 PATH 中；重开工具窗或重启 IDE 后重试。

**首次弹出信任/登录界面？**  
在该终端内按提示键操作即可。

### 🔒 隐私

不联网，不收集任何数据；所有命令都在 IDE 内置终端中可见。

### 📄 许可

MIT License - 详见 [LICENSE](LICENSE) 文件

### ⚠️ 免责声明

本插件为独立项目，与 Cursor 或任何第三方供应商无关；不打包/下载/安装 `cursor-agent`，也不收集数据。