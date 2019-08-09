library(neuralnet)
library(hydroGOF)
library(arules)
library(leaps)

dados <- read.csv("~/Desktop/SRCR/Praticas/Ficha12/creditset.csv",header=TRUE,sep=",",dec=".")

head(dados)

treino <- dados[1:800,]

teste <- dados[801:2000,]

#------------------------------------------------------------------------------------------------
#Seleção de variaveis mais significativas

funcao01 <- default10yr ~ income+age+loan+LTI
selecao01 <- regsubsets(funcao01, dados, nvmax=1)
summary(selecao01)


#------------------------------------------------------------------------------------------------
#Seleção de variaveis mais significativas
funcao02 <- default10yr ~ clienteid+income+age+loan+LTI
selecao02 <- regsubsets(funcao02,dados,method="backward")
summary(selecao02)


#------------------------------------------------------------------------------------------------
# discretizacao de atributos
nomes <- c(1,2,3,4,5)
income <- discretize(dados$income,method = "frequency",categories = 5,labels = nomes )
dados$income <- as.numeric(income)

#income <- discretize(dados$income,method = "cluster",categories = 4, labels=c(1,2,3,4))
#dados$income <- as.numeric(income)
#------------------------------------------------------------------------------------------------


formula01 <- default10yr ~ LTI + age

formula02 <- default10yr ~ age+loan+LTI

rnacredito <- neuralnet(formula01, treino, hidden = c(4), lifesign = "full", threshold = 0.01)

plot(rnacredito, rep = "best")

teste.01 <- subset(teste, select=c("LTI","age"))

rnacredito.resultados <- compute(rnacredito,teste.01)

resultados <- data.frame(atual=teste$default10yr, previsao = rnacredito.resultados$net.result)

resultados$previsao <- round(resultados$previsao, digits = 0)

rmse(c(teste$default10yr), c(resultados$previsao))





