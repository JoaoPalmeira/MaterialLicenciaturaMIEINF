set.seed(1234567890)
library( neuralnet )
library( hydroGOF )
library( leaps )
library( arules )
library(clusterSim)

dados <- read.csv("C:\\Users\\pc\\Desktop\\cadeiras\\2? semestre\\SRCR\\trabalho3\\winequality-white.csv",sep=";")

normalize1 <- function(df, cols) {
  result <- df # make a copy of the input data frame
  
  for (j in cols) { # each specified col
    m <- mean(df[,j]) # column mean
    std <- sd(df[,j]) # column (sample) sd
    
    for (i in 1:nrow(result)) { # each row of cur col
      result[i,j] <- (result[i,j] - m) / std
    }
  }
  return(result)
}

normalize <- function(df, cols) {
  result <- df # make a copy of the input data frame
  
  for (j in cols) { # each specified col
    s <- sum(df[,j]) # column (sample) sd
    
    for (i in 1:nrow(result)) { # each row of cur col
      result[i,j] <- (result[i,j])/ s
    }
  }
  return(result)
}

cols <- c(1,2,3,4,5,6,7,8,9,10,11,12)
dados1 <- normalize(dados, cols)
dados <- dados1

dados <- dados[sample(nrow(dados)), ]

# dividir os dados iniciais em casos para treino...
treino <- dados[1:2449, ]

# ... e casos para teste:
teste <- dados[2450:4898, ]

# defini??o das camadas de entrada e sa?da da RNA
formula01 <- quality ~ volatile.acidity + residual.sugar + density + pH + alcohol

# sele??o das vari?veis mais significativas
regg1<-regsubsets(quality ~ fixed.acidity + volatile.acidity + citric.acid + residual.sugar + chlorides + free.sulfur.dioxide + total.sulfur.dioxide + density + pH + sulphates + alcohol, dados)
summary(regg1)

# treinar a rede neuronal para usar as vari?veis volatile.acidity, residual.sugar, density, pH e alcohol como input e quality como output
rna <- neuralnet( formula01, treino, hidden = c(4), lifesign = "full", linear.output=FALSE, threshold = 0.1, stepmax= 1e6)

# desenhar rede neuronal
plot(rna, rep = "best")

# definir variaveis de input para teste
teste.01 <- subset(teste, select = c("volatile.acidity","residual.sugar","density","pH","alcohol"))

# testar a rede com os novos casosclear
rna.resultados <- compute(rna, teste.01)

# imprimir resultados
resultados <- data.frame(atual = teste$quality, previsao = rna.resultados$net.result)

# imprimir resultados arredondados
resultados$previsao <- round(resultados$previsao, digits=0)

# calcular o RMSE
rmse(c(teste$quality),c(resultados$previsao))

