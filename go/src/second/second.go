package main

import (
	"fmt"
	"time"
	"utils"
)

func main() {
	ch := make(chan string)
	utils.ReliableRequest(ch)

	time.Sleep(2 * time.Second)

	select {
	case msg := <-ch:
		fmt.Println(msg)
	default:
		fmt.Println("Time out!")
	}

}
