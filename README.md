# docsify

>  两个仓库两个不同分支代码同步提交

```shell
# 查看ssh公钥
vi ~/.ssh/id_rsa.pub

git clone git@git.dev.tencent.com:wujing2417/docsify.git
cd docsify/

# 切换并创建分支
git checkout -b coding-pages

git remote add github git@github.com:92376/docsify.git

git add .
git commit -m 'init.'

# 先添加ssh公钥
git push github master

# 文档分支（两份）
git push origin coding-pages
git push github coding-pages:gh-pages
```

