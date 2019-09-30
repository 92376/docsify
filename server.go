package main

import (
	"log"
	"net/http"
	"os"
)

func main() {

	port := "8080"
	if len(os.Args)>1 {
		port = os.Args[1]
	}
	//dir, _ := os.Getwd()
	//http.Handle("/", http.FileServer(http.Dir(dir)))
	http.Handle("/", http.FileServer(http.Dir(".")))
	log.Print("listen: ", port)
	log.Fatal(http.ListenAndServe(":" + port, nil))

}