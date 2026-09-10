# Pi Review Test Repository

用于验证 Pi Repository Governance Agent 的 GitHub App → Webhook → PR Review 链路。

项目只有一个无依赖的购物车函数，运行测试：

```sh
npm test
```

仓库包含两个分支：

- `main`：干净基线。
- `demo/review-me`：先增加一个有意缺少路径边界检查的文件读取功能，再用新提交修复，用于验证 `synchronize` 复审。

上传到 GitHub 后安装待测 GitHub App，再从 `demo/review-me` 向 `main` 创建非 Draft PR。该演示分支只用于安全测试，不要把它合并到生产代码。

首次推送示例：

```sh
git remote add origin git@github.com:<owner>/<repository>.git
git push -u origin main
git push -u origin demo/review-me
```
