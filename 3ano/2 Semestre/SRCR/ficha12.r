library( neuralnet )
library( hydroGOF )
library( leaps )
library( arules )

set.seed(1234567890)

dados <- read.csv("C:\\Users\\OEremita\\Desktop\\SRCR\\ficha12\\creditset.csv", header=TRUE, sep=",",dec=".")

# mostrar a "cabea" do dataset
head(trainset)

# dividir os dados iniciais em casos para treino...
treino <- dados[1:800, ]

# ... e casos para teste:
teste <- dados[801:2000, ]

# definição das camadas de entrada e saída da RNA
formula01 <- default10yr ~ LTI + age
#formula02 <- default10yr ~ age + loan + LTI

# treinar a rede neuronal para usar a variavel LTI e age como input e degault10y como output
rnacredito <- neuralnet( formula01, treino, hidden = c(4), lifesign = "full", linear.output = FALSE, threshold = 0.1)
#rede <- neuralnet(formula02,treino,hidden=c(3,2),threshold=0.1)

# desenhar rede neuronal
plot(rnacredito, rep = "best")
#plot(rede)

# definir variaveis de input para teste
teste.01 <- subset(teste, select = c("LTI", "age"))

# testar a rede com os novos casos
rnacredito.resultados <- compute(rnacredito, teste.01)

# imprimir resultados
resultados <- data.frame(atual = teste$default10yr, previsao = rnacredito.resultados$net.result)

# imprimir resultados arredondados
resultados$previsao <- round(resultados$previsao, digits=0)

# calcular o RMSE
rmse(c(teste$default10yr),c(resultados$previsao))
