var url = require("url"),
    fs = require("fs"),
    http = require("http"),
    path = require("path");
    port = 8080;
    if (process.argv.length>2) {
        port = process.argv[2];
    }
http.createServer(function (req, res) {

    var pathname = __dirname + url.parse(req.url).pathname;
    if (pathname.charAt(pathname.length - 1) == "/") {
        pathname += "index.html";
    }
	// 中文路径
    // pathname = decodeURIComponent(pathname);
    fs.exists(pathname, function (exists) {
        if (exists) {
            fs.readFile(pathname, function (err, data) {
                res.end(data);
            });
        } else {
            res.writeHead(404, {
                "Content-Type": "text/html"
            });
            res.end("<h1>404 Not Found</h1>");
        }
    });
}).listen(port);
console.log("listen: ", port);