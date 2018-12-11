package main

import (
	"utils"
	"fmt"
	"time"
)

func closeCh(done chan interface{}) {
	randomNumb := utils.RandomInt(1, 40)
	time.Sleep(time.Duration(randomNumb) * time.Second)
	close(done)
}

func executeRealibleRequest(ch chan string, done chan interface{}) {
	for {
		ch := make(chan string, 3)
		go utils.ReliableRequest(ch)
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
