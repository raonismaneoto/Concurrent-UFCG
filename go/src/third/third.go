package main

import (
	"utils"
	"fmt"
	"time"
)

func closeCh(done chan interface{}) {
	randomNumb := utils.RandomInt(1, 15)
	time.Sleep(time.Duration(randomNumb) * time.Second)
	close(done)
}

func executeRealibleRequest(ch chan string, done chan interface{}) {
	go utils.ReliableRequest(ch)
	for {
		select {
		case <-done:
			fmt.Println("Its over")
			return
		case msg := <-ch:
			fmt.Println(msg)
		}
	}
}

func main() {
	ch := make(chan string)
	done := make(chan interface{})

	go closeCh(done)
	executeRealibleRequest(ch, done)
}
