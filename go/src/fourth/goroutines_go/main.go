package main

import (
	"fmt"
	"sync"
	"math/rand"
	"runtime"
	"time"
)

func producer(execs int, integers chan int, barr *sync.WaitGroup) {
	defer barr.Done()
	
	for i := 0; i < execs; i++ {
		value := rand.Intn(10) + 1
		integers <- value
	}
}

func consumer(execs int, integers <-chan int, barr *sync.WaitGroup) {
	defer barr.Done()
	
	for i := 0; i < execs; i++ {
		n := <- integers
		if n != 0 {
			fmt.Printf("")
		} else {
			fmt.Printf("")
		}
	}
}

func main() {
	rand.Seed(time.Now().UnixNano())

	execs := 100
	for goroutines := 1; goroutines <= 131072; goroutines*=2 {
	
		integers := make(chan int)
		
		var barr sync.WaitGroup
		barr.Add(2 * goroutines)
		
		for i := 0; i < goroutines; i++ {
			go producer(execs, integers, &barr)
			go consumer(execs, integers, &barr)
		}

		barr.Wait()

		var m runtime.MemStats
		runtime.ReadMemStats(&m)

		fmt.Printf("%vKB of memory used for %v goroutines\n", m.TotalAlloc / 1024, goroutines)
	
	}
}



