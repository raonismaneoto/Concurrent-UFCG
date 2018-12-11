library(tidyverse)
library(ggplot2)
theme_set(theme_bw())

setwd("/Concurrent-UFCG/src/go/fourth")

go <- read_csv("go.csv")
java <- read_csv("java.csv")

# ConcurrentHashMap x Collections.SynchronizedMap for get
(ggplot(go, aes(x=go$n, y=go$memory))
  +geom_point()
  +geom_line(stat="smooth", method="loess")
  +labs(title="Análise de Memória para Gorotines em Go", x="Número de Gorotines", y="Memória em Kb")
)

(ggplot(java, aes(x=java$n, y=java$memory))
  +geom_point()
  +geom_line(stat="smooth", method="loess")
  +labs(title="Análise de Memória para Threads em Java", x="Número de Threads", y="Memória em Kb")
)
