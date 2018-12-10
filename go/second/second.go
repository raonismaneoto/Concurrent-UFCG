package main

import "fmt"
import "time"
import "math/rand"

func randomInt(min int, max int) int {
	return min + rand.Intn(max-min)
}

func mirror(serverName string, ch chan<- string) {
	randomNumb := randomInt(1, 6)
	time.Sleep(time.Duration(randomNumb) * time.Second)
	ch <- serverName
}

func main() {
	rand.Seed(time.Now().UnixNano())
	ch := make(chan string)

	go mirror("mirror1.com", ch)
	go mirror("mirror2.br", ch)
	go mirror("mirror3.edu", ch)

	fmt.Printf("Content came from <%s>\n", <-ch)
}
