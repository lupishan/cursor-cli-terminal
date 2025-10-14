# Release Preparation Guide

This guide helps you prepare a new release of the plugin.

## Pre-release Checklist

### 1. Update Version Numbers

- [ ] Update version in `build.gradle` (line 7)
- [ ] Update version in `src/main/resources/META-INF/plugin.xml` (line 4)
- [ ] Add new version to change notes in `plugin.xml` (around line 46)

### 2. Test the Plugin

```bash
# Build the plugin
./gradlew buildPlugin

# Run in sandbox
./gradlew runIde

# Verify plugin
./gradlew verifyPlugin
```

### 3. Update Documentation

- [ ] Update README.md if there are new features
- [ ] Update CONTRIBUTING.md if development process changed
- [ ] Update Marketplace.md for marketplace listing

### 4. Create Release

```bash
# Tag the release
git tag -a v1.0.x -m "Release version 1.0.x"
git push origin v1.0.x

# Build distribution
./gradlew buildPlugin

# Distribution will be in: build/distributions/cursor-agent-plugin-1.0.x.zip
```

### 5. Publish to GitHub

1. Go to GitHub → Releases → Create new release
2. Choose the tag you just created
3. Fill in release notes (copy from plugin.xml change-notes)
4. Attach the ZIP file from `build/distributions/`
5. Publish release

### 6. Publish to JetBrains Marketplace (Optional)

If you want to publish to the marketplace:

1. Set up your marketplace token:
   ```bash
   export PUBLISH_TOKEN=your_token_here
   ```

2. Publish:
   ```bash
   ./gradlew publishPlugin
   ```

For first-time setup:
- Create account at https://plugins.jetbrains.com
- Get your token from: https://plugins.jetbrains.com/author/me/tokens
- Never commit the token to git!

## Version Numbering

Follow semantic versioning:
- **Major** (X.0.0): Breaking changes
- **Minor** (1.X.0): New features, backwards compatible
- **Patch** (1.0.X): Bug fixes

## Release Notes Template

```markdown
## What's New in v1.0.x

### ✨ New Features
- Feature description

### 🐛 Bug Fixes
- Bug fix description

### 📝 Documentation
- Documentation updates

### 🔧 Internal
- Internal improvements
```

## Post-release

- [ ] Announce on relevant channels
- [ ] Update project website if any
- [ ] Close related issues and PRs
- [ ] Monitor for bug reports

