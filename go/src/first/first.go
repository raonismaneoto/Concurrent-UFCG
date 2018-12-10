package main

import "fmt"
import "utils"

func main() {
	ch := make(chan string)
	utils.ReliableRequest(ch)
	fmt.Printf("Content came from <%s>\n", <-ch)
}
