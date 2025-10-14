# Contributing to Cursor CLI Terminal

Thank you for your interest in contributing! This document provides guidelines for contributing to this project.

## 🎯 Project Goals

This plugin aims to:
- Provide seamless integration of `cursor-agent` into JetBrains IDEs
- Serve as a reference implementation for similar CLI tool integrations
- Maintain simplicity and minimal dependencies
- Respect user privacy (no data collection, no network calls)

## 🚀 Getting Started

### Prerequisites

- JDK 17 or later
- Gradle (wrapper included)
- IntelliJ IDEA (Community or Ultimate)

### Setting Up Development Environment

1. **Clone the repository**
   ```bash
   git clone https://github.com/YOUR_USERNAME/cursor-agent-plugin.git
   cd cursor-agent-plugin
   ```

2. **Open in IntelliJ IDEA**
   - File → Open → select the project directory
   - IDEA will automatically detect it's a Gradle project

3. **Build the plugin**
   ```bash
   ./gradlew buildPlugin
   ```

4. **Run in sandbox**
   ```bash
   ./gradlew runIde
   ```
   This will start a new IDE instance with the plugin installed.

## 🏗️ Project Structure

```
src/main/java/cn/lupishan/agent/
├── CursorToolWindowFactory.java  # Creates the tool window and terminal
├── StartCursorAgentAction.java   # Handles menu actions (focus/restart)
└── CursorAgentUtils.java         # Shared utilities

src/main/resources/
├── icons/                         # Plugin icons
└── META-INF/
    └── plugin.xml                 # Plugin configuration

build.gradle                       # Build configuration
```

## 📝 Code Style

- Follow standard Java conventions
- Use meaningful variable and method names
- Add comments for non-obvious logic
- Keep methods focused and concise
- Use IntelliJ IDEA's built-in formatter (Ctrl+Alt+L)

## 🧪 Testing

Before submitting changes:

1. **Manual Testing**
   - Run `./gradlew runIde`
   - Test your changes in the sandbox IDE
   - Verify both normal and edge cases

2. **Build Verification**
   ```bash
   ./gradlew buildPlugin
   ```
   Ensure the build completes without errors.

## 🔧 Making Changes

### For Bug Fixes

1. Create an issue describing the bug
2. Fork the repository
3. Create a branch: `git checkout -b fix/description-of-fix`
4. Make your changes
5. Test thoroughly
6. Submit a pull request

### For New Features

1. Create an issue to discuss the feature first
2. Wait for feedback/approval
3. Fork and create a branch: `git checkout -b feature/description`
4. Implement the feature
5. Update documentation if needed
6. Submit a pull request

### For Documentation

- Documentation improvements are always welcome
- Fix typos, improve clarity, add examples
- Update both English and Chinese sections in README.md

## 🎨 Adapting for Other CLI Tools

This plugin can serve as a template for other CLI integrations (like Claude Code). Key areas to modify:

1. **Plugin Configuration** (`plugin.xml`)
   - Change plugin ID, name, description
   - Update vendor information
   - Modify action names and shortcuts

2. **Command Execution** (`CursorToolWindowFactory.java`)
   - Update `autorun()` method to call your CLI tool
   - Modify path resolution in `CursorAgentUtils.resolveAgentAbsolutePath()`

3. **Branding**
   - Replace icons in `src/main/resources/icons/`
   - Update notification group IDs
   - Change tool window title

4. **Build Configuration** (`build.gradle`)
   - Update `group` and `version`
   - Modify `publishPlugin` settings if publishing to marketplace

## 📋 Pull Request Guidelines

- **Title**: Clear and descriptive (e.g., "Fix terminal not starting on macOS")
- **Description**: Explain what changes were made and why
- **Testing**: Describe how you tested the changes
- **Screenshots**: Include for UI changes
- **Commits**: Keep commits atomic and well-described

## 🐛 Reporting Issues

When reporting issues, please include:

- IDE version (Help → About)
- Plugin version
- Operating system
- Steps to reproduce
- Expected vs actual behavior
- Screenshots if applicable
- Relevant log snippets (Help → Show Log in Finder/Explorer)

## 💡 Questions?

Feel free to:
- Open an issue for questions
- Start a discussion
- Email the maintainer (see plugin.xml for contact)

## 📜 License

By contributing, you agree that your contributions will be licensed under the MIT License.

---

Thank you for contributing! 🎉

