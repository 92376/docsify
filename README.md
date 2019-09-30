# 启动静态web服务方式

> ## 1 执行已编译好的文件

* `（推荐）`

> linux: [./server下载](https://dev.tencent.com/u/wujing2417/p/docsify/git/raw/coding-pages/server)
windows: [server.exe下载](https://dev.tencent.com/u/wujing2417/p/docsify/git/raw/coding-pages/server.exe)

```shell
# linux
./server [8080]

# windows
server[.exe] [8080]
```

> ## 2 go

```shell
go run server.go [8080]
# 构建可执行文件
go build server.go
```

> ## 3 python3

```shell
python -m http.server [8000]
```

> ## 4 python2

```shell
python -m SimpleHTTPServer [8000]
```

> ## 5 nodejs

```shell
node server.js [8080]
```

> ## 6 nginx

* nignx-1.16.1/conf/nginx.conf配置
* 资源路径/src/static/

```conf
# 1.访问路径host:port/
location / {
    root   /src/static/;
    # index  index.html index.htm;
}
# 2.访问路径host:port/static/
location ^~ /static/ {
    root   /src/;
    # 如果没有首页,显示文件列表
    # autoindex on;
}
```