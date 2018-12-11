package main

import (
	"fmt"
	"sync"
	"math/rand"
	"runtime"
	"time"
)

func producer(execs int, integers chan int, wg *sync.WaitGroup) {
	defer wg.Done()
	
	for i := 0; i < execs; i++ {
		value := rand.Intn(10) + 1
		integers <- value
	}
}

func consumer(execs int, integers <-chan int, wg *sync.WaitGroup) {
	defer wg.Done()
	
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
		
		var wg sync.WaitGroup
		wg.Add(2 * goroutines)
		
		for i := 0; i < goroutines; i++ {
			go producer(execs, integers, &wg)
			go consumer(execs, integers, &wg)
		}

		wg.Wait()

		var m runtime.MemStats
		runtime.ReadMemStats(&m)

		fmt.Printf("%vKB of memory used for %v goroutines\n", m.TotalAlloc / 1024, goroutines)
	
	}
}



