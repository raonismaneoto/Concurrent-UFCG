package utils

import "math/rand"
import "time"

func RandomInt(min int, max int) int {
	rand.Seed(time.Now().UnixNano())
	return min + rand.Intn(max-min)
}

func mirror(serverName string, ch chan<- string) {
	randomNumb := RandomInt(1, 5)
	time.Sleep(time.Duration(randomNumb) * time.Second)
	ch <- serverName
}

func ReliableRequest(ch chan string) {
	go mirror("mirror1.com", ch)
	go mirror("mirror2.br", ch)
	go mirror("mirror3.edu", ch)
}