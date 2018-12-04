library(tidyverse)
library(ggplot2)
theme_set(theme_bw())

#setwd("/Concurrent-UFCG/java/Concurrent/src/fifth")

map_conc_sync <- read_csv("conc_x_sync_Map.csv")
map_conc_sync_get <- filter(map_conc_sync, map_conc_sync$get==1)
map_conc_sync_put <- filter(map_conc_sync, map_conc_sync$get==0)
map_conc <- filter(map_conc_sync, map_conc_sync$class=="class java.util.concurrent.ConcurrentHashMap")
map_sync <- filter(map_conc_sync, map_conc_sync$class=="class java.util.Collections$SynchronizedMap")
map_conc_get <- filter(map_conc_sync_get, map_conc_sync_get$class=="class java.util.concurrent.ConcurrentHashMap")
map_conc_put <- filter(map_conc_sync_put, map_conc_sync_put$class=="class java.util.concurrent.ConcurrentHashMap")
map_sync_get <- filter(map_conc_sync_get, map_conc_sync_get$class=="class java.util.Collections$SynchronizedMap")
map_sync_put <- filter(map_conc_sync_put, map_conc_sync_put$class=="class java.util.Collections$SynchronizedMap")

# ConcurrentHashMap x Collections.SynchronizedMap for get
(ggplot(map_conc_sync_get, aes(x=nThreads, y=totalTime, colour=class))
    +geom_point()
    +geom_line(stat="smooth", method="loess")
    +labs(title="ConcurrentHashMap x Collections.SynchronizedMap for get", x="Número de Threads", y="Tempo em ms")
)

# ConcurrentHashMap x Collections.SynchronizedMap for put
(ggplot(map_conc_sync_put, aes(x=nThreads, y=totalTime, colour=class))
    +geom_point()
    +geom_line(stat="smooth", method="loess")
    +labs(title="ConcurrentHashMap x Collections.SynchronizedMap for put", x="Número de Threads", y="Tempo em ms")
)


list_copy_sync <- read_csv("copy_x_sync_List.csv")
list_copy_sync_get <- filter(list_copy_sync, list_copy_sync$get==1)
list_copy_sync_put <- filter(list_copy_sync, list_copy_sync$get==0)
list_copy <- filter(list_copy_sync, list_copy_sync$class=="class java.util.concurrent.CopyOnWriteArrayList")
list_sync <- filter(list_copy_sync, list_copy_sync$class=="class java.util.Collections$SynchronizedRandomAccessList")
list_copy_get <- filter(list_copy_sync_get, list_copy_sync_get$class=="class java.util.concurrent.CopyOnWriteArrayList")
list_copy_put <- filter(list_copy_sync_put, list_copy_sync_put$class=="class java.util.concurrent.CopyOnWriteArrayList")
list_sync_get <- filter(list_copy_sync_get, list_copy_sync_get$class=="class java.util.Collections$SynchronizedRandomAccessList")
list_sync_put <- filter(list_copy_sync_put, list_copy_sync_put$class=="class java.util.Collections$SynchronizedRandomAccessList")

# CopyOnWriteArrayList x Collections.synchronizedList for get
(ggplot(list_copy_sync_get, aes(x=nThreads, y=totalTime, colour=class))
    +geom_point()
    +geom_line(stat="smooth", method="loess")
    +labs(title="CopyOnWriteArrayList x Collections.synchronizedList for get", x="Número de Threads", y="Tempo em ms")
)

# CopyOnWriteArrayList x Collections.synchronizedList for add
(ggplot(list_copy_sync_put, aes(x=nThreads, y=totalTime, colour=class))
    +geom_point()
    +geom_line(stat="smooth", method="loess")
    +labs(title="CopyOnWriteArrayList x Collections.synchronizedList for add", x="Número de Threads", y="Tempo em ms")
)
