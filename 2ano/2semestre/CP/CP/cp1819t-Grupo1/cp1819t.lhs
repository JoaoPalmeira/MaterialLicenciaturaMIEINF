\documentclass[a4paper]{article}
\usepackage[a4paper,left=3cm,right=2cm,top=2.5cm,bottom=2.5cm]{geometry}
\usepackage{palatino}
\usepackage[colorlinks=true,linkcolor=blue,citecolor=blue]{hyperref}
\usepackage{graphicx}
\usepackage{cp1819t}
\usepackage{subcaption}
\usepackage{adjustbox}
\usepackage{color}
\usepackage[T1]{fontenc}
\usepackage{float}
\usepackage{subcaption}
%================= local x=====================================================%
\def\getGif#1{\includegraphics[width=0.3\textwidth]{cp1819t_media/#1.png}}
\let\uk=\emph
\def\aspas#1{``#1"}
%================= lhs2tex=====================================================%
%include polycode.fmt 
%format (cata g) = "\cata{" g "}"
%format (ana g) = "\ana{" g "}"
%format (hylo h g) = "\hylo{" h g "}"
%format (cataExpr g) = "\cata{" g "}"
%format (anaExpr g) = "\ana{" g "}"
%format (hyloExpr g) = "\cata{" g "}"
%format (cataL2D g) = "\cata{" g "}"
%format (anaL2D g) = "\ana{" g "}"
%format (hyloL2D g) = "\cata{" g "}"
%format (cataFS g) = "\cata{" g "}"
%format (anaFS g) = "\ana{" g "}"
%format (hyloFS g) = "\cata{" g "}"
%format (div (x)(y)) = x "\div " y
%format succ = "\succ "
%format ==> = "\Longrightarrow "
%format map = "\map "
%format length = "\length "
%format fst = "\p1"
%format p1  = "\p1"
%format snd = "\p2"
%format p2  = "\p2"
%format Left = "i_1"
%format Right = "i_2"
%format i1 = "i_1"
%format i2 = "i_2"
%format >< = "\times"
%format >|<  = "\bowtie "
%format |-> = "\mapsto"
%format . = "\comp "
%format (kcomp (f)(g)) = f "\kcomp " g
%format -|- = "+"
%format conc = "\mathsf{conc}"
%format summation = "{\sum}"
%format (either (a) (b)) = "\alt{" a "}{" b "}"
%format (frac (a) (b)) = "\frac{" a "}{" b "}"
%format (uncurry f) = "\uncurry{" f "}"
%format (const f) = "\underline{" f "}"
%format TLTree = "\mathsf{TLTree}"
%format (lcbr (x)(y)) = "\begin{lcbr}" x "\\" y "\end{lcbr}"
%format (split (x) (y)) = "\conj{" x "}{" y "}"
%format (for (f) (i)) = "\for{" f "}\ {" i "}"
%format B_tree = "\mathsf{B}\mbox{-}\mathsf{tree} "
\def\ana#1{\mathopen{[\!(}#1\mathclose{)\!]}}
%format (cataA (f) (g)) = "\cata{" f "~" g "}_A"
%format (anaA (f) (g)) = "\ana{" f "~" g "}_A"
%format (cataB (f) (g)) = "\cata{" f "~" g "}_B"
%format (anaB (f) (g)) = "\ana{" f "~" g "}_B"
%format Either a b = a "+" b 
%format fmap = "\mathsf{fmap}"
%format NA   = "\textsc{na}"
%format NB   = "\textsc{nb}"
%format inT = "\mathsf{in}"
%format outT = "\mathsf{out}"
%format Null = "1"
%format (Prod (a) (b)) = a >< b
%format fF = "\fun F "
%format e1 = "e_1 "
%format e2 = "e_2 "
%format Dist = "\fun{Dist}"
%format IO = "\fun{IO}"
%format BTree = "\fun{BTree} "
%format LTree = "\mathsf{LTree}"
%format inNat = "\mathsf{in}"
%format (cataNat (g)) = "\cata{" g "}"
%format Nat0 = "\N_0"
%format muB = "\mu "
%format (frac (n)(m)) = "\frac{" n "}{" m "}"
%format (fac (n)) = "{" n "!}"
%format (underbrace (t) (p)) = "\underbrace{" t "}_{" p "}"
%format matrix = "matrix"
%format (bin (n) (k)) = "\Big(\vcenter{\xymatrix@R=1pt{" n "\\" k "}}\Big)"
%format `ominus` = "\mathbin{\ominus}"
%format % = "\mathbin{/}"
%format <-> = "{\,\leftrightarrow\,}"
%format <|> = "{\,\updownarrow\,}"
%format `minusNat`= "\mathbin{-}"
%format ==> = "\Rightarrow"
%format .==>. = "\Rightarrow"
%format .<==>. = "\Leftrightarrow"
%format .==. = "\equiv"
%format .<=. = "\leq"
%format .&&&. = "\wedge"
%format cdots = "\cdots "
%format pi = "\pi "

%---------------------------------------------------------------------------

\title{
       	    Cálculo de Programas
\\
       	Trabalho Prático
\\
       	MiEI+LCC --- 2018/19
}

\author{
       	\dium
\\
       	Universidade do Minho
}


\date\mydate

\makeindex
\newcommand{\rn}[1]{\textcolor{red}{#1}}
\begin{document}

\maketitle

\begin{center}\large
\begin{tabular}{ll}
\textbf{Grupo} nr. & 1
\\\hline
a70565 & Bruno Manuel Borlido Arieira
\\
a73864 & João Miguel Freitas Palmeira  
\\
a74264 &  Rafael Machado da Silva
\end{tabular}
\end{center}

\section{Preâmbulo}

A disciplina de \CP\ tem como objectivo principal ensinar
a progra\-mação de computadores como uma disciplina científica. Para isso
parte-se de um repertório de \emph{combinadores} que formam uma álgebra da
programação (conjunto de leis universais e seus corolários) e usam-se esses
combinadores para construir programas \emph{composicionalmente}, isto é,
agregando programas já existentes.
  
Na sequência pedagógica dos planos de estudo dos dois cursos que têm
esta disciplina, restringe-se a aplicação deste método à programação
funcional em \Haskell. Assim, o presente trabalho prático coloca os
alunos perante problemas concretos que deverão ser implementados em
\Haskell.  Há ainda um outro objectivo: o de ensinar a documentar
programas, validá-los, e a produzir textos técnico-científicos de
qualidade.

\section{Documentação} Para cumprir de forma integrada os objectivos
enunciados acima vamos recorrer a uma técnica de programa\-ção dita
``\litp{literária}'' \cite{Kn92}, cujo princípio base é o seguinte:
\begin{quote}\em Um programa e a sua documentação devem coincidir.
\end{quote} Por outras palavras, o código fonte e a documentação de um
programa deverão estar no mesmo ficheiro.

O ficheiro \texttt{cp1819t.pdf} que está a ler é já um exemplo de \litp{programação
literária}: foi gerado a partir do texto fonte \texttt{cp1819t.lhs}\footnote{O
suffixo `lhs' quer dizer \emph{\lhaskell{literate Haskell}}.} que encontrará
no \MaterialPedagogico\ desta disciplina descompactando o ficheiro \texttt{cp1819t.zip}
e executando
\begin{Verbatim}[fontsize=\small]
    $ lhs2TeX cp1819t.lhs > cp1819t.tex
    $ pdflatex cp1819t
\end{Verbatim}
em que \href{https://hackage.haskell.org/package/lhs2tex}{\texttt\LhsToTeX} é
um pre-processador que faz ``pretty printing''
de código Haskell em \Latex\ e que deve desde já instalar executando
\begin{Verbatim}[fontsize=\small]
    $ cabal install lhs2tex
\end{Verbatim}
Por outro lado, o mesmo ficheiro \texttt{cp1819t.lhs} é executável e contém
o ``kit'' básico, escrito em \Haskell, para realizar o trabalho. Basta executar
\begin{Verbatim}[fontsize=\small]
    $ ghci cp1819t.lhs
\end{Verbatim}

%if False
\begin{code}
{-# OPTIONS_GHC -XNPlusKPatterns #-}
{-# LANGUAGE GeneralizedNewtypeDeriving, DeriveDataTypeable, FlexibleInstances #-}
module Cp1819t where 
import Cp
import List  hiding (fac)
import Nat  
import Data.List hiding (find)
import Data.Typeable
import Data.Ratio
import Data.Bifunctor
import Data.Maybe
import Data.Matrix hiding ((!))
import Test.QuickCheck hiding ((><),choose)
import qualified Test.QuickCheck as QuickCheck
import System.Random  hiding (split)
import System.Process
import GHC.IO.Exception
import qualified Graphics.Gloss as G
import Control.Monad
import Control.Applicative hiding ((<|>))
import Data.Either
import Exp
\end{code}
%endif

\noindent Abra o ficheiro \texttt{cp1819t.lhs} no seu editor de texto preferido
e verifique que assim é: todo o texto que se encontra dentro do ambiente
\begin{quote}\small\tt
\verb!\begin{code}!
\\ ... \\
\verb!\end{code}!
\end{quote}
vai ser seleccionado pelo \GHCi\ para ser executado.

\section{Como realizar o trabalho}
Este trabalho teórico-prático deve ser realizado por grupos de três alunos.
Os detalhes da avaliação (datas para submissão do relatório e sua defesa
oral) são os que forem publicados na \cp{página da disciplina} na \emph{internet}.

Recomenda-se uma abordagem participativa dos membros do grupo
de trabalho por forma a poderem responder às questões que serão colocadas
na \emph{defesa oral} do relatório.

Em que consiste, então, o \emph{relatório} a que se refere o parágrafo anterior?
É a edição do texto que está a ser lido, preenchendo o anexo \ref{sec:resolucao}
com as respostas. O relatório deverá conter ainda a identificação dos membros
do grupo de trabalho, no local respectivo da folha de rosto.

Para gerar o PDF integral do relatório deve-se ainda correr os comando seguintes,
que actualizam a bibliografia (com \Bibtex) e o índice remissivo (com \Makeindex),
\begin{Verbatim}[fontsize=\small]
    $ bibtex cp1819t.aux
    $ makeindex cp1819t.idx
\end{Verbatim}
e recompilar o texto como acima se indicou. Dever-se-á ainda instalar o utilitário
\QuickCheck,
que ajuda a validar programas em \Haskell\ e a biblioteca \gloss{Gloss} para
geração de gráficos 2D:
\begin{Verbatim}[fontsize=\small]
    $ cabal install QuickCheck gloss
\end{Verbatim}
Para testar uma propriedade \QuickCheck~|prop|, basta invocá-la com o comando:
\begin{verbatim}
    > quickCheck prop
    +++ OK, passed 100 tests.
\end{verbatim}

Qualquer programador tem, na vida real, de ler e analisar (muito!) código
escrito por outros. No anexo \ref{sec:codigo} disponibiliza-se algum
código \Haskell\ relativo aos problemas que se seguem. Esse anexo deverá
ser consultado e analisado à medida que isso for necessário.

\Problema

Um compilador é um programa que traduz uma linguagem dita de
\emph{alto nível} numa linguagem (dita de \emph{baixo nível}) que
seja executável por uma máquina.
Por exemplo, o \gcc{GCC} compila C/C++ em código objecto que
corre numa variedade de arquitecturas.

Compiladores são normalmente programas complexos.
Constam essencialmente de duas partes:
o \emph{analisador sintático} que lê o texto de entrada
(o programa \emph{fonte} a compilar) e cria uma sua representação
interna, estruturada em árvore;
e o \emph{gerador de código} que converte essa representação interna
em código executável.
Note-se que tal representação intermédia pode ser usada para outros fins,
por exemplo,
para gerar uma listagem de qualidade (\uk{pretty print}) do programa fonte.

O projecto de compiladores é um assunto complexo que
será assunto de outras disciplinas.
Neste trabalho pretende-se apenas fazer uma introdução ao assunto,
mostrando como tais programas se podem construir funcionalmente à custa de
cata/ana/hilo-morfismos da linguagem em causa.

Para cumprirmos o nosso objectivo, a linguagem desta questão terá que ser, naturalmente,
muito simples: escolheu-se a das expressões aritméticas com inteiros,
\eg\ \( 1 + 2 \), \( 3 * (4 + 5) \) etc.
Como representação interna adopta-se o seguinte tipo polinomial, igualmente simples:
%
\begin{spec}
data Expr = Num Int | Bop Expr Op Expr 
data Op = Op String
\end{spec}

\begin{enumerate}
\item
Escreva as definições dos \{cata, ana e hilo\}-morfismos deste tipo de dados
segundo o método ensinado nesta disciplina (recorde módulos como \eg\ |BTree| etc).
\item
Como aplicação do módulo desenvolvido no ponto 1,
defina como \{cata, ana ou hilo\}-morfismo a função seguinte:
\begin{itemize}
\item |calcula :: Expr -> Int| que calcula o valor
de uma expressão;
\begin{propriedade}
O valor zero é um elemento neutro da adição.
\begin{code}
prop_neutro1 :: Expr -> Bool
prop_neutro1 = calcula . addZero .==. calcula where
            addZero e = Bop (Num 0) (Op "+") e
prop_neutro2 :: Expr -> Bool
prop_neutro2 = calcula . addZero .==. calcula where
            addZero e = Bop e (Op "+") (Num 0) 
\end{code}
\end{propriedade}  
\begin{propriedade}
As operações de soma e multiplicação são comutativas.
\begin{code}
prop_comuta = calcula . mirror .==. calcula where
            mirror = cataExpr (either Num g2)
            g2 = (uncurry (uncurry Bop)) . (swap >< id) . assocl . (id >< swap)
\end{code}
\end{propriedade}  
\end{itemize}
\item
Defina como \{cata, ana ou hilo\}-morfismos as funções
\begin{itemize}
\item |compile :: String -> Codigo| - trata-se do compilador propriamente
      dito. Deverá ser gerado código posfixo para uma máquina elementar
      de \pda{stack}. O tipo |Codigo| pode ser definido à escolha.
      Dão-se a seguir exemplos de comportamentos aceitáveis para esta
      função:
\begin{verbatim} 
Tp4> compile "2+4"
["PUSH 2", "PUSH 4", "ADD"]
Tp4> compile "3*(2+4)"
["PUSH 3", "PUSH 2", "PUSH 4", "ADD", "MUL"]
Tp4> compile "(3*2)+4"
["PUSH 3", "PUSH 2", "MUL", "PUSH 4", "ADD"]
Tp4> 
\end{verbatim}
\item |show' :: Expr -> String| - gera a representação textual
      de uma |Expr| pode encarar-se como o \uk{pretty printer}
      associado ao nosso compilador
\begin{propriedade}
Em anexo, é fornecido o código da função |readExp|, que é ``inversa" da função |show'|,
tal como a propriedade seguinte descreve:
\begin{code}
prop_inv :: Expr -> Bool
prop_inv = p1 . head . readExp . show' .==. id
\end{code}
\end{propriedade}  
\end{itemize}
%% \item Generalize o tipo |Expr| de forma a admitir operadores
%% unários (\eg\ \(-5\)) e repita os exercícios dos pontos anteriores.
\end{enumerate}

\paragraph{Valorização}
Em anexo é apresentado código \Haskell\ que permite declarar
|Expr| como instância da classe |Read|. Neste contexto,  
|read| pode ser vista como o analisador
sintático do nosso minúsculo compilador de expressões aritméticas.

Analise o código apresentado, corra-o e escreva no seu relatório uma explicação
\textbf{breve} do seu funcionamento, que deverá saber defender aquando da
apresentação oral do relatório.

Exprima ainda o analisador sintático |readExp| como um anamorfismo.

\Problema

Pretende-se neste problema definir uma linguagem gráfica \aspas{brinquedo}
a duas dimensões (2D) capaz de especificar e desenhar agregações de
caixas que contêm informação textual.
Vamos designar essa linguagem por |L2D| e vamos defini-la como um tipo
 em \Haskell:
\begin{code}
type L2D = X Caixa Tipo 
\end{code}
onde |X| é a estrutura de dados
\begin{code}
data X a b = Unid a | Comp b (X a b) (X a b) deriving Show
\end{code}
e onde:
\begin{code}
type Caixa = ((Int,Int),(Texto,G.Color))
type Texto = String
\end{code}
Assim, cada caixa de texto é especificada pela sua largura, altura, o seu
texto e a sua côr.\footnote{Pode relacionar |Caixa| com as caixas de texto usadas
nos jornais ou com \uk{frames} da linguagem \Html\ usada na Internet.}
Por exemplo,
\begin{spec}
((200,200),("Caixa azul",col_blue))
\end{spec}
designa a caixa da esquerda da figura \ref{fig:L2D}.

O que a linguagem |L2D| faz é agregar tais caixas tipográficas
umas com as outras segundo padrões especificados por vários
\aspas{tipos}, a saber,
\begin{code}
data Tipo = V | Vd | Ve | H | Ht | Hb 
\end{code}
com o seguinte significado:
\begin{itemize}
\item[|V|] - agregação vertical alinhada ao centro
\item[|Vd|] - agregação vertical justificada à direita
\item[|Ve|] - agregação vertical justificada à esquerda
\item[|H|] - agregação horizontal alinhada ao centro
\item[|Hb|] - agregação horizontal alinhada pela base
\item[|Ht|] - agregação horizontal alinhada pelo topo
\end{itemize}
Como |L2D| instancia o parâmetro |b| de |X| com
|Tipo|, é fácil de ver que cada \aspas{frase} da linguagem
|L2D| é representada por uma árvore binária em que cada nó
indica qual o tipo de agregação a aplicar às suas duas sub-árvores.
%
Por exemplo, a frase
\begin{code}
ex2= Comp Hb  (Unid ((100,200),("A",col_blue)))
              (Unid ((50,50),("B",col_green)))
\end{code}
deverá corresponder à imagem da direita da figura \ref{fig:L2D}.
E poder-se-á ir tão longe quando a linguagem o permita. Por exemplo, pense na
estrutura da frase que representa o \uk{layout} da figura \ref{fig:L2D1}.

\begin{figure}
\centering
\begin{picture}(190.00,130.00)(-15,-15)
\put(00.00,0.00){$(0,0)$}
\put(80.00,50.00){$(200,200)$}
\put(20.00,-10.00){
	\includegraphics[width=70\unitlength]{cp1819t_media/ex3.png}
}
\end{picture}
%
\includegraphics[width=0.2\textwidth]{cp1819t_media/ex2.png}
%
\caption{Caixa simples e caixa composta.\label{fig:L2D}}
\end{figure}

\begin{figure}
\centering
\includegraphics[width=0.6\textwidth]{cp1819t_media/ex.png}
\caption{\uk{Layout} feito de várias caixas coloridas.\label{fig:L2D1}}
\end{figure}

É importante notar que cada ``caixa'' não dispõe informação relativa
ao seu posicionamento final na figura. De facto, é a posição relativa
que deve ocupar face às restantes caixas que irá determinar a sua
posição final. Este é um dos objectivos deste trabalho:
\emph{calcular o posicionamento absoluto de cada uma das caixas por forma a
respeitar as restrições impostas pelas diversas agregações}. Para isso vamos
considerar um tipo de dados que comporta a informação de todas as
caixas devidamente posicionadas (i.e. com a informação adicional da
origem onde a caixa deve ser colocada).

\begin{spec}
type Fig = [(Origem,Caixa)]
type Origem = (Float, Float)
\end{spec}
%
A informação mais relevante deste tipo é a referente à lista de
``caixas posicionadas'' (tipo |(Origem,Caixa)|). Regista-se aí a origem
da caixa que, com a informação da sua altura e comprimento, permite
definir todos os seus pontos (consideramos as caixas sempre paralelas
aos eixos). 

\begin{enumerate}
\item Forneça a definição da função |calc_origems|, que calcula as
coordenadas iniciais das caixas no plano:
\begin{spec}
calc_origems :: (L2D,Origem) -> X (Caixa,Origem) ()
\end{spec}
\item Forneça agora a definição da função |agrup_caixas|, que agrupa
todas as caixas e respectivas origens numa só lista:
\begin{spec}
agrup_caixas :: X (Caixa,Origem) () -> Fig
\end{spec}
\end{enumerate}

Um segundo problema neste projecto é \emph{descobrir como visualizar a
informação gráfica calculada por |desenho|}. A nossa estratégia para
superar o problema baseia-se na biblioteca \gloss{Gloss}, que permite a geração
de gráficos 2D. Para tal disponibiliza-se a função
\begin{spec}
crCaixa :: Origem  -> Float -> Float -> String -> G.Color -> G.Picture
\end{spec}
que cria um rectângulo com base numa coordenada, um valor para a largura, um valor
para a altura, um texto que irá servir de etiqueta, e a cor pretendida.
Disponibiliza-se também a função
\begin{spec}
display :: G.Picture -> IO ()
\end{spec}
que dado um valor do tipo |G.picture| abre uma janela com esse valor desenhado. O objectivo
final deste exercício é implementar então uma função 
\begin{spec}
mostra_caixas :: (L2D,Origem) -> IO ()
\end{spec}
que dada uma frase da linguagem |L2D| e coordenadas iniciais apresenta
o respectivo desenho no ecrã.
%
\textbf{Sugestão}: 
Use a função |G.pictures| disponibilizada na biblioteca \gloss{Gloss}.

\Problema

Nesta disciplina estudou-se como fazer \pd{programação dinâmica} por cálculo,
recorrendo à lei de recursividade mútua.\footnote{Lei (\ref{eq:fokkinga})
em \cite{Ol18}, página \pageref{eq:fokkinga}.}

Para o caso de funções sobre os números naturais (|Nat0|, com functor |fF
X = 1 + X|) é fácil derivar-se da lei que foi estudada uma
	\emph{regra de algibeira}
	\label{pg:regra}
que se pode ensinar a programadores que não tenham estudado
\cp{Cálculo de Programas}. Apresenta-se de seguida essa regra, tomando como exemplo o
cálculo do ciclo-\textsf{for} que implementa a função de Fibonacci, recordar
o sistema
\begin{spec}
fib 0 = 1
fib(n+1) = f n

f 0 = 1
f (n+1) = fib n + f n
\end{spec}
Obter-se-á de imediato
\begin{code}
fib' = p1 . for loop init where
   loop(fib,f)=(f,fib+f)
   init = (1,1)
\end{code}
usando as regras seguintes:
\begin{itemize}
\item	O corpo do ciclo |loop| terá tantos argumentos quanto o número de funções mutuamente recursivas.
\item	Para as variáveis escolhem-se os próprios nomes das funções, pela ordem
que se achar conveniente.\footnote{Podem obviamente usar-se outros símbolos, mas numa primeiraleitura
dá jeito usarem-se tais nomes.}
\item	Para os resultados vão-se buscar as expressões respectivas, retirando a variável |n|.
\item	Em |init| coleccionam-se os resultados dos casos de base das funções, pela mesma ordem.
\end{itemize}
Mais um exemplo, envolvendo polinómios no segundo grau a $x^2 + b x + c$ em |Nat0|.
Seguindo o método estudado nas aulas\footnote{Secção 3.17 de \cite{Ol18}.},
de $f\ x = a x^2 + b x + c$ derivam-se duas funções mutuamente recursivas:
\begin{spec}
f 0 = c
f (n+1) = f n + k n

k 0 = a + b
k(n+1) = k n + 2 a
\end{spec}
Seguindo a regra acima, calcula-se de imediato a seguinte implementação, em Haskell:
\begin{code}
f' a b c = p1 . for loop init where
  loop(f,k) = (f+k,k+2*a)
  init = (c,a+b) 
\end{code}

Qual é o assunto desta questão, então? Considerem fórmula que dá a série de Taylor da
função coseno:
\begin{eqnarray*}
	cos\ x = \sum_{i=0}^\infty \frac{(-1)^i}{(2i)!} x^{2i}
\end{eqnarray*}
Pretende-se o ciclo-\textsf{for} que implementa a função 
|cos' x n| que dá o valor dessa série tomando |i| até |n| inclusivé:
\begin{spec}
cos' x = cdots . for loop init where cdots
\end{spec}
%
\textbf{Sugestão}: Começar por estudar muito bem o processo de cálculo dado
no anexo \ref{sec:recmul} para o problema (semelhante) da função exponencial.

\begin{propriedade}
Testes de que |cos' x| calcula bem o coseno de |pi| e o coseno de |pi/2|:
\begin{code}
prop_cos1 n = n >= 10 ==> abs(cos pi - cos' pi n) < 0.001
prop_cos2 n = n >= 10 ==> abs(cos (pi/2) - cos' (pi/2) n) < 0.001
\end{code}
\end{propriedade}

\paragraph{Valorização} Transliterar |cos'| para a linguagem C; compilar
e testar o código. Conseguia, por intuição apenas, chegar a esta função?

\Problema

Pretende-se nesta questão desenvolver uma biblioteca de funções para
manipular \emph{sistemas de ficheiros} genéricos.
Um sistema de ficheiros será visto como uma associação de \emph{nomes}
a ficheiros ou \emph{directorias}.
Estas últimas serão vistas como sub-sistemas de ficheiros e assim
recursivamente.
Assumindo que |a| é o tipo dos identificadores dos ficheiros e
directorias, e que |b| é o tipo do conteúdo dos ficheiros,
podemos definir um tipo indutivo de dados para representar sistemas de
ficheiros da seguinte forma:
\begin{code}
data FS a b = FS [(a,Node a b)] deriving (Eq,Show)
data Node a b = File b | Dir (FS a b) deriving (Eq,Show)
\end{code}
Um caminho (\emph{path}) neste sistema de ficheiros pode ser representado pelo
seguinte tipo de dados:
\begin{code}
type Path a = [a]
\end{code}
Assumindo estes tipos de dados, o seguinte termo
\begin{spec}
FS [("f1", File "Ola"),
    ("d1", Dir (FS [("f2", File "Ole"),
                    ("f3", File "Ole")
                   ]))
   ]
\end{spec}
representará um sistema de ficheiros em cuja raíz temos um ficheiro chamado
|f1| com conteúdo |"Ola"| e uma directoria chamada |"d1"| constituída por dois
ficheiros, um chamado |"f2"| e outro chamado |"f3"|, ambos com conteúdo |"Ole"|.
%
Neste caso, tanto o tipo dos identificadores como o tipo do conteúdo dos
ficheiros é |String|. No caso geral, o conteúdo de um ficheiro é arbitrário:
pode ser um binário, um texto, uma colecção de dados, etc.

A definição das usuais funções |inFS| e |recFS| para este tipo é a seguinte:
\begin{code}
inFS = FS . map (id >< inNode) 
inNode = either File Dir

recFS f = baseFS id id f
\end{code}
Suponha que se pretende definir como um |catamorfismo| a função que
conta o número de ficheiros existentes num sistema de ficheiros. Uma
possível definição para esta função seria:
\begin{code}
conta :: FS a b -> Int
conta = cataFS (sum . map ((either (const 1) id) . snd))
\end{code}
O que é para fazer:
\begin{enumerate}
\item Definir as funções |outFS|, |baseFS|, |cataFS|, |anaFS| e |hyloFS|.

\item Apresentar, no relatório, o diagrama de |cataFS|.

\item Definir as seguintes funções para manipulação de sistemas de
  ficheiros usando, obrigatoriamente, catamorfismos, anamorfismos ou
  hilomorfismos:

  \begin{enumerate}
  \item Verificação da integridade do sistema de ficheiros (i.e. verificar
    que não existem identificadores repetidos dentro da mesma directoria). \\
    |check :: FS a b -> Bool|
  \begin{propriedade}
    A integridade de um sistema de ficheiros não depende da ordem em que os    
    últimos são listados na sua directoria:
\begin{code}
prop_check :: FS String String -> Bool
prop_check = check . (cataFS (inFS . reverse)).==. check
\end{code}
  \end{propriedade}  
  \item Recolha do conteúdo de todos os ficheiros num arquivo indexado pelo \emph{path}.\\
    |tar :: FS a b -> [(Path a, b)]|
  \begin{propriedade}
    O número de ficheiros no sistema deve ser igual ao número de ficheiros
    listados pela função |tar|.
\begin{code}
prop_tar :: FS String String -> Bool
prop_tar = length . tar .==. conta 
\end{code}
  \end{propriedade}  
  \item Transformação de um arquivo com o conteúdo dos ficheiros
    indexado pelo \emph{path} num sistema de ficheiros.\\
    |untar :: [(Path a, b)] -> FS a b| \\
  \textbf{Sugestão}: Use a função |joinDupDirs| para juntar directorias que estejam na mesma
  pasta e que possuam o mesmo identificador.
  \begin{propriedade}
    A composição |tar . untar| preserva o número de ficheiros no sistema.
\begin{code}
prop_untar :: [(Path String, String)] -> Property
prop_untar = validPaths .==>. ((length . tar . untar) .==. length)
validPaths :: [(Path String, String)] -> Bool
validPaths = (== 0) . length . (filter (\(a,_) -> length a == 0))
\end{code}
\end{propriedade}  
  \item Localização de todos os \emph{paths} onde existe um
    determinado ficheiro.\\
    |find :: a -> FS a b -> [Path a]|
  \begin{propriedade}
    A composição |tar . untar| preserva todos os ficheiros no sistema.
\begin{code}
prop_find :: String -> FS String String -> Bool
prop_find = curry $ 
      length . (uncurry find) .==. length . (uncurry find) . (id >< (untar . tar))
\end{code}
  \end{propriedade}  
  \item Criação de um novo ficheiro num determinado \emph{path}.\\
    |new :: Path a -> b -> FS a b -> FS a b|
  \begin{propriedade}
A adição de um ficheiro não existente no sistema não origina ficheiros duplicados.
\begin{code}
prop_new :: ((Path String,String), FS String String) -> Property
prop_new = ((validPath .&&&. notDup) .&&&. (check . p2)) .==>. 
      (checkFiles . (uncurry (uncurry new)))  where
      validPath = (/=0) . length . p1 . p1
      notDup = not . (uncurry elem) . (p1 >< ((fmap p1) . tar))
\end{code}
\textbf{Questão}: Supondo-se que no código acima se substitui a propriedade
|checkFiles| pela propriedade mais fraca |check|, será que a propriedade
|prop_new| ainda é válida? Justifique a sua resposta. 
\end{propriedade}
 
\begin{propriedade}
	A listagem de ficheiros logo após uma adição nunca poderá ser menor que a listagem
	de ficheiros antes dessa mesma adição.
\begin{code}
prop_new2 :: ((Path String,String), FS String String) -> Property
prop_new2 = validPath .==>. ((length . tar . p2) .<=. (length . tar . (uncurry (uncurry new)))) where
      validPath = (/=0) . length . p1 . p1
\end{code}
  \end{propriedade}  
  \item Duplicação de um ficheiro.\\
    |cp :: Path a -> Path a -> FS a b -> FS a b|
  \begin{propriedade}
    A listagem de ficheiros com um dado nome não diminui após uma duplicação.
\begin{code}
prop_cp :: ((Path String, Path String),  FS String String) -> Bool
prop_cp =   length . tar . p2 .<=. length . tar . (uncurry (uncurry cp))
\end{code}
  \end{propriedade}  
  \item Eliminação de um ficheiro.\\
    |rm :: Path a -> FS a b -> FS a b|

  \textbf{Sugestão}: Construir um anamorfismo |nav :: (Path a, FS a b) -> FS a b|
  que navegue por um sistema de ficheiros tendo como base o |path| dado como argumento.
    \begin{propriedade}
    Remover duas vezes o mesmo ficheiro tem o mesmo efeito que o remover apenas uma vez.
\begin{code}
prop_rm :: (Path String, FS String String) -> Bool
prop_rm = (uncurry rm ) . (split p1 (uncurry rm)) .==. (uncurry rm)
\end{code}
\end{propriedade}
\begin{propriedade}
Adicionar um ficheiro e de seguida remover o mesmo não origina novos ficheiros no sistema.
\begin{code}
prop_rm2 :: ((Path String,String), FS String String) -> Property
prop_rm2 = validPath  .==>. ((length . tar . (uncurry rm) . (split (p1. p1) (uncurry (uncurry new)))) 
         .<=. (length . tar . p2)) where
         validPath = (/=0) . length . p1 . p1
\end{code}
\end{propriedade}  
  \end{enumerate}
\end{enumerate}

\paragraph{Valorização} 

Definir uma função para visualizar em \graphviz{Graphviz}
a estrutura de um sistema de ficheiros. A Figura~\ref{ex_prob1}, por exemplo,
apresenta a estrutura de um sistema com precisamente dois ficheiros dentro
de uma directoria chamada |"d1"|. 

Para realizar este exercício será necessário apenas  escrever o anamorfismo
\begin{quote}
|cFS2Exp :: (a, FS a b) -> (Exp () a)| 
\end{quote}
que converte a estrutura de um sistema de ficheiros numa árvore de expressões
descrita em \href{http://wiki.di.uminho.pt/twiki/pub/Education/CP/MaterialPedagogico/Exp.hs}{Exp.hs}.
A função |dotFS| depois tratará de passar a estrutura do sistema de ficheiros para o visualizador.
\begin{figure}
\centering
\includegraphics[scale=0.5]{cp1819t_media/fs.png}
\caption{Exemplo de um sistema de ficheiros visualizado em \graphviz{Graphviz}.}
\label{ex_prob1}
\end{figure}

%----------------- Programa, bibliotecas e código auxiliar --------------------%

\newpage

\part*{Anexos}

\appendix

\section{Como exprimir cálculos e diagramas em LaTeX/lhs2tex}
Estudar o texto fonte deste trabalho para obter o efeito:\footnote{Exemplos tirados de \cite{Ol18}.} 
\begin{eqnarray*}
\start
	|id = split f g|
%
\just\equiv{ universal property }
%
        |lcbr(
		p1 . id = f
	)(
		p2 . id = g
	)|
%
\just\equiv{ identity }
%
        |lcbr(
		p1 = f
	)(
		p2 = g
	)|
\qed
\end{eqnarray*}

Os diagramas podem ser produzidos recorrendo à \emph{package} \LaTeX\ 
\href{https://ctan.org/pkg/xymatrix}{xymatrix}, por exemplo: 
\begin{eqnarray*}
\xymatrix@@C=2cm{
    |Nat0|
           \ar[d]_-{|cataNat g|}
&
    |1 + Nat0|
           \ar[d]^{|id + (cataNat g)|}
           \ar[l]_-{|inNat|}
\\
     |B|
&
     |1 + B|
           \ar[l]^-{|g|}
}
\end{eqnarray*}

\section{Programação dinâmica por recursividade múltipla}\label{sec:recmul}
Neste anexo dão-se os detalhes da resolução do Exercício \ref{ex:exp} dos apontamentos da
disciplina\footnote{Cf.\ \cite{Ol18}, página \pageref{ex:exp}.},
onde se pretende implementar um ciclo que implemente
o cálculo da aproximação até |i=n| da função exponencial $exp\ x = e^x$
via série de Taylor:
\begin{eqnarray}
	exp\ x 
& = &
	\sum_{i=0}^{\infty} \frac {x^i} {i!}
\end{eqnarray}
Seja $e\ x\ n = \sum_{i=0}^{n} \frac {x^i} {i!}$ a função que dá essa aproximação.
É fácil de ver que |e x 0 = 1| e que $|e x (n+1)| = |e x n| + \frac {x^{n+1}} {(n+1)!}$.
Se definirmos $|h x n| = \frac {x^{n+1}} {(n+1)!}$ teremos |e x| e |h x| em recursividade
mútua. Se repetirmos o processo para |h x n| etc obteremos no total três funções nessa mesma
situação:
\begin{spec}
e x 0 = 1
e x (n+1) = h x n + e x n

h x 0 = x
h x (n+1) = x/(s n) * h x n

s 0 = 2
s (n+1) = 1 + s n
\end{spec}
Segundo a \emph{regra de algibeira} descrita na página \ref{pg:regra} deste enunciado,
ter-se-á, de imediato:
\begin{code}
e' x = prj . for loop init where
     init = (1,x,2)
     loop(e,h,s)=(h+e,x/s*h,1+s)
     prj(e,h,s) = e
\end{code}

\section{Código fornecido}\label{sec:codigo}

\subsection*{Problema 1}
Tipos:
\begin{code}
data Expr = Num Int 
               | Bop Expr Op Expr  deriving  (Eq,Show)

data Op = Op String deriving (Eq,Show)

type Codigo = [String]
\end{code}
Functor de base:
\begin{code}
baseExpr f g = id -|- (f >< (g >< g))
\end{code}
Instâncias:
\begin{code}
instance Read Expr where
   readsPrec _ = readExp
\end{code}
Read para Exp's:
\begin{code}
readOp :: String -> [(Op,String)]
readOp input = do 
                 (x,y) <- lex input
                 return ((Op x),y)

readNum :: ReadS Expr
readNum  = (map (\ (x,y) -> ((Num x), y))).reads

readBinOp :: ReadS Expr
readBinOp = (map (\ ((x,(y,z)),t) -> ((Bop x y z),t))) .
                   ((readNum `ou` (pcurvos readExp))
                    `depois` (readOp `depois` readExp))

readExp :: ReadS Expr
readExp = readBinOp `ou` (
          readNum `ou` (
          pcurvos readExp))
\end{code}
Combinadores:
\begin{code}

depois :: (ReadS a) -> (ReadS b) -> ReadS (a,b)
depois _ _ [] = []
depois r1 r2 input = [((x,y),i2) | (x,i1) <- r1 input , 
                                   (y,i2) <- r2 i1]

readSeq :: (ReadS a) -> ReadS [a]
readSeq r input 
  = case (r input) of
    [] -> [([],input)]
    l -> concat (map continua l)
         where continua (a,i) = map (c a) (readSeq r i)
               c x (xs,i) = ((x:xs),i)                     

ou :: (ReadS a) -> (ReadS a) -> ReadS a
ou r1 r2 input = (r1 input) ++ (r2 input)

senao :: (ReadS a) -> (ReadS a) -> ReadS a
senao r1 r2 input = case (r1 input) of
                     [] -> r2 input
                     l  -> l

readConst :: String -> ReadS String
readConst c = (filter ((== c).fst)) . lex

pcurvos = parentesis '(' ')'
prectos = parentesis '[' ']'
chavetas = parentesis '{' '}'

parentesis :: Char -> Char -> (ReadS a) -> ReadS a
parentesis _ _ _ [] = []
parentesis ap pa r input 
  = do 
      ((_,(x,_)),c) <- ((readConst [ap]) `depois` (
                        r                `depois` (
                        readConst [pa])))           input
      return (x,c)
\end{code}

\subsection*{Problema 2}
Tipos:
\begin{code}
type Fig = [(Origem,Caixa)]
type Origem = (Float, Float)
\end{code}
``Helpers":
\begin{code}
col_blue = G.azure
col_green = darkgreen

darkgreen = G.dark (G.dark G.green)
\end{code}
Exemplos:
\begin{code}
ex1Caixas = G.display (G.InWindow "Problema 4" (400, 400) (40, 40)) G.white $
          crCaixa (0,0) 200 200 "Caixa azul" col_blue

ex2Caixas =  G.display (G.InWindow "Problema 4" (400, 400) (40, 40)) G.white $
          caixasAndOrigin2Pict ((Comp Hb bbox gbox),(0.0,0.0)) where
          bbox = Unid ((100,200),("A",col_blue))
          gbox = Unid ((50,50),("B",col_green))

ex3Caixas = G.display (G.InWindow "Problema 4" (400, 400) (40, 40)) G.white mtest where
          mtest = caixasAndOrigin2Pict $ (Comp Hb (Comp Ve bot top) (Comp Ve gbox2 ybox2), (0.0,0.0))
          bbox1 = Unid ((100,200),("A",col_blue))
          bbox2 = Unid ((150,200),("E",col_blue))
          gbox1 = Unid ((50,50),("B",col_green))
          gbox2 = Unid ((100,300),("F",col_green))
          rbox1 = Unid ((300,50),("C",G.red))
          rbox2 = Unid ((200,100),("G",G.red))
          wbox1 = Unid ((450,200),("",G.white)) 
          ybox1 = Unid ((100,200),("D",G.yellow))
          ybox2 = Unid ((100,300),("H",G.yellow))
          bot = Comp Hb wbox1 bbox2 
          top = (Comp Ve (Comp Hb bbox1 gbox1) (Comp Hb rbox1 (Comp H ybox1 rbox2)))
\end{code}
A seguinte função cria uma caixa a partir dos seguintes parâmetros: origem,
largura, altura, etiqueta e côr de preenchimento.
\begin{code}
crCaixa :: Origem  -> Float -> Float -> String -> G.Color -> G.Picture
crCaixa (x,y) w h l c = G.Translate (x+(w/2)) (y+(h/2)) $  G.pictures [caixa, etiqueta] where
                    caixa = G.color c (G.rectangleSolid w h)
                    etiqueta = G.translate calc_trans_x calc_trans_y $ 
                             G.Scale calc_scale calc_scale $ G.color G.black $ G.Text l
                    calc_trans_x = (- ((fromIntegral (length l)) * calc_scale) / 2 )*base_shift_x
                    calc_trans_y = (- calc_scale / 2)*base_shift_y
                    calc_scale = bscale * (min h w) 
                    bscale = 1/700
                    base_shift_y = 100
                    base_shift_x = 64
\end{code}
Função para visualizar resultados gráficos:
\begin{code}
display = G.display (G.InWindow "Problema 4" (400, 400) (40, 40)) G.white 
\end{code}

\subsection*{Problema 4}
Funções para gestão de sistemas de ficheiros:
\begin{code}
concatFS = inFS . (uncurry (++)) . (outFS >< outFS)
mkdir (x,y) = FS [(x, Dir y)]
mkfile (x,y) = FS [(x, File y)]

joinDupDirs :: (Eq a) => (FS a b) -> (FS a b)
joinDupDirs  = anaFS (prepOut . (id >< proc) . prepIn) where 
         prepIn = (id >< (map (id >< outFS))) . sls . (map distr) . outFS
         prepOut = (map undistr) . (uncurry (++)) . ((map i1) >< (map i2)) . (id >< (map (id >< inFS)))
         proc = concat . (map joinDup) . groupByName
         sls = split lefts rights

joinDup :: [(a,[b])] -> [(a,[b])]
joinDup = cataList (either nil g) where g = return . (split (p1 . p1) (concat . (map p2) . (uncurry (:))))

createFSfromFile :: (Path a, b) -> (FS a b)
createFSfromFile ([a],b) = mkfile(a,b)
createFSfromFile (a:as,b) = mkdir(a, createFSfromFile (as,b))
\end{code}
Funções auxiliares:
\begin{code}
checkFiles :: (Eq a) => FS a b -> Bool
checkFiles = cataFS ((uncurry (&&)) . (split f g)) where
           f = nr . (fmap p1) . lefts . (fmap distr)
           g = and . rights . (fmap p2)

groupByName :: (Eq a) => [(a,[b])] -> [[(a,[b])]]
groupByName = (groupBy (curry p)) where
            p = (uncurry (==)) . (p1 >< p1)

filterPath :: (Eq a) => Path a -> [(Path a, b)] -> [(Path a, b)]
filterPath = filter . (\p -> \(a,b) -> p == a)
\end{code}
Dados para testes:
\begin{itemize}
\item Sistema de ficheiros vazio:
\begin{code}
efs = FS []
\end{code}
\item Nível 0
\begin{code}
f1 = FS [("f1", File "hello world")]
f2 = FS [("f2", File "more content")]
f00 = concatFS (f1,f2)
f01 = concatFS (f1, mkdir("d1", efs))
f02 = mkdir("d1",efs)
\end{code}
\item Nível 1
\begin{code}
f10 = mkdir("d1", f00)
f11 = concatFS(mkdir("d1", f00), mkdir("d2", f00))
f12 = concatFS(mkdir("d1", f00), mkdir("d2", f01))
f13 = concatFS(mkdir("d1", f00), mkdir("d2", efs))
\end{code}
\item Nível 2
\begin{code}
f20 = mkdir("d1", f10)
f21 = mkdir("d1", f11)
f22 = mkdir("d1", f12)
f23 = mkdir("d1", f13)
f24 = concatFS(mkdir("d1",f10), mkdir("d2",f12))
\end{code}
\item Sistemas de ficheiros inválidos:
\begin{code}
ifs0 = concatFS (f1,f1)
ifs1 = concatFS (f1, mkdir("f1", efs))
ifs2 = mkdir("d1", ifs0)
ifs3 = mkdir("d1", ifs1)
ifs4 = concatFS(mkdir("d1",ifs1), mkdir("d2",f12))
ifs5 = concatFS(mkdir("d1",f1), mkdir("d1",f2))
ifs6 = mkdir("d1",ifs5)
ifs7 = concatFS(mkdir("d1",f02),mkdir("d1",f02))
\end{code}
\end{itemize}
Visualização em \graphviz{Graphviz}:
\begin{code}
dotFS :: FS String b -> IO ExitCode
dotFS = dotpict . bmap (const "") id . (cFS2Exp "root")
\end{code}
\def\omitted{``Automagically" generated:
\begin{code}
instance (Arbitrary a, Arbitrary b) => Arbitrary (FS a b) where
   arbitrary = sized genfs  where
             genfs 0 = liftM (inFS . (map (id >< (i1)))) QuickCheck.arbitrary
             genfs n = oneof [liftM (inFS . (map (id >< (i1)))) QuickCheck.arbitrary,
                     liftM (inFS . return . (id >< (i2))) (liftM2 (,) 
                     QuickCheck.arbitrary (genfs (n - 1))),
                     liftM3 genAux QuickCheck.arbitrary (genfs (n - 1)) (genfs (n - 1))]
             genAux a x y = inFS [(a, i2 x), (a, i2 y)]

instance Arbitrary Expr where
   arbitrary = (genExpr 10)  where
             genExpr 0 = liftM (inExpr . i1) QuickCheck.arbitrary
             genExpr n = oneof [liftM (inExpr . i1) QuickCheck.arbitrary,
                       liftM (inExpr . i2 . (uncurry genAux1)) 
                       $ liftM2 (,)  (genExpr (n-1)) (genExpr (n-1)),
                       liftM (inExpr . i2 . (uncurry genAux2)) 
                       $ liftM2 (,)  (genExpr (n-1)) (genExpr (n-1))
                     ]
             genAux1 x y = (Op "+",(x,y))
             genAux2 x y = (Op "*",(x,y))
\end{code}}

\subsection*{Outras funções auxiliares}
%----------------- Outras definições auxiliares -------------------------------------------%
Lógicas:
\begin{code}
infixr 0 .==>.
(.==>.) :: (Testable prop) => (a -> Bool) -> (a -> prop) -> a -> Property
p .==>. f = \a -> p a ==> f a

infixr 0 .<==>.
(.<==>.) :: (a -> Bool) -> (a -> Bool) -> a -> Property
p .<==>. f = \a -> (p a ==> property (f a)) .&&. (f a ==> property (p a))

infixr 4 .==.
(.==.) :: Eq b => (a -> b) -> (a -> b) -> (a -> Bool)
f .==. g = \a -> f a == g a

infixr 4 .<=.
(.<=.) :: Ord b => (a -> b) -> (a -> b) -> (a -> Bool)
f .<=. g = \a -> f a <= g a

infixr 4 .&&&.
(.&&&.) :: (a -> Bool) -> (a -> Bool) -> (a -> Bool)
f .&&&. g = \a -> ((f a) && (g a))
\end{code}
Compilação e execução dentro do interpretador:\footnote{Pode ser útil em testes
envolvendo \gloss{Gloss}. Nesse caso, o teste em causa deve fazer parte de uma função
|main|.}
\begin{code}

run = do { system "ghc cp1819t" ; system "./cp1819t" }
\end{code}

%----------------- Soluções dos alunos -----------------------------------------%

\section{Soluções dos alunos}\label{sec:resolucao}
Os alunos devem colocar neste anexo as suas soluções aos exercícios
propostos, de acordo com o "layout" que se fornece. Não podem ser
alterados os nomes ou tipos das funções dadas, mas pode ser adicionado texto e/ou 
outras funções auxiliares que sejam necessárias.

\subsection*{Problema 1}





\begin{center}
\large \textbf{In, Out e Functor de Expr}
\end{center}

\begin{code}

inExpr :: Either Int (Op,(Expr,Expr)) -> Expr
inExpr =  either Num (uncurry (uncurry . flip Bop))

outExpr :: Expr -> Either Int (Op,(Expr,Expr))
outExpr (Num x) = Left x
outExpr (Bop a x b) = Right (x,(a,b))

recExpr f = id -|- (id >< (f >< f))

\end{code}

\begin{center}
\large \textbf{Catamorfismo, Anamorfismo e Hilomorfismo de Expr}
\end{center}

\begin{code}
--Catamorfismo de Expr
cataExpr g = g . recExpr(cataExpr g) . outExpr

--Anamorfismo de Expr
anaExpr g = inExpr . recExpr(anaExpr g) . g

--Hilomorfismo de Expr
hyloExpr f g = cataExpr f . anaExpr g

\end{code}


\begin{center}
\large \textbf{Resoluções}
\end{center}

\begin{center}
\large {Resolução da função \emph{calcula}}
\end{center}

\par Para esta função, tal como especificado, recebe uma ou mais expressões aritméticas e, posteriormente retorna o valor relativo a essas expressões. Por exemplo, a expressão 4*(4+1) = 20.  

\begin{code}

calcAux :: (Op, (Int,Int)) -> Int
calcAux (x, (a, b)) | x == Op "+" = a + b
                    | x == Op "*" = a * b
                    | x == Op "-" = a - b
                    | otherwise = div a b

calcula :: Expr -> Int
calcula = cataExpr(either id calcAux)

\end{code}

\par Para o desenvolvimento desta função, decidimos criar uma função auxiliar onde fazemos o cálculo para uma expressão com dois inteiros e uma operação. Assim, verificamos qual o operador introduzido, e a partir deste é feito o cálculo com os dois inteiros. Sendo já sido feito o cálculo para uma expressão, agora na função calcula basta chamar a auxiliar tantas vezes quantas as operações. Para tal, decidimos usar um catamorfismo em que o seu gene é especificado para um número inteiro ou para uma expressão. Para testar esta expressão a forma correta de introduzir é, por exemplo, calcula (Bop (Num 3) (Op "+" ) (Num 4)). 

\begin{center}
\large {Resolução da função \emph{compile}}
\end{center}



\par Este exercício trata da implementação de um compilador que gera o código introduzido para uma máquina género stack. Posto isto, aquando a introdução de um inteiro a sua representação é "PUSH inteiro" e para uma operação é realizada por extenso, por exemplo: compile "3+4" = ["PUSH 3", "PUSH 4", "ADD"]. Assim na função abaixo referida utilizamos um catamorfismo, onde o g1 é a representação de um número (["PUSH .."]) e o g2 a representação de uma ou mais expressões (["PUSH ..","PUSH ..","OP", ...]. 

\begin{code}

compile :: String -> Codigo
compile = cataExpr(either g1 g2).p1.head.readExp
          where g1 a = ["PUSH " ++ show a]
                g2 (x, (a,b)) | x == Op "+" = a ++ b ++ ["ADD"]
                              | x == Op "*" = a ++ b ++ ["MUL"]
                              | x == Op "-" = a ++ b ++ ["SUB"]
                              | otherwise = a ++ b ++ ["DIV"]

\end{code}


\begin{center}
\large {Resolução da função \emph{show'}}
\end{center}



\par Em relação a esta função, representa uma expressão do tipo Expr numa String. Assim sendo, fizemos tal conversão com o uso de um catamorfismo onde para g1 especificamos um inteiro (Num 3 = 3) e para g2 o caso de uma expressão completa com a respetiva operação entre dois inteiros. Por exemplo, com a introdução de uma expressão (Bop (Num 3) (Op "+" ) (Num 4)) é gerado um output "(( 3 ))+( 4 )".

\begin{code}

show' :: Expr -> String 
show' = cataExpr (either g1 g2)
        where g1 a = showAux a
              g2 (Op a,(x,y)) | length(x) > 1 = "(" ++ x ++ ")" ++ a ++ y
                              | length(y) > 1 = x ++ a ++ "(" ++ y ++ ")"
                              | otherwise = "( "++ x ++ a ++ y ++" )"
showAux :: Int -> String
showAux a | a < 0 = "(" ++ show a ++ ")"
          | otherwise = show a

\end{code}



\subsection*{Problema 2}

\begin{center}
\large \textbf{In e Out de um L2D}
\end{center}

\begin{code}

inL2D :: Either a (b, (X a b,X a b)) -> X a b
inL2D = either Unid comp where comp (a,(b,c)) = Comp a b c

outL2D :: X a b -> Either a (b, (X a b,X a b))
outL2D (Unid a) = Left a
outL2D (Comp a b c) = Right (a,(b,c))

\end{code}

\begin{center}
\large \textbf{Anamorfismo, Catamorfismo e Hilomorfismo de uma L2D  }
\end{center}

\begin{code}

--Functor
recL2D f = id -|- id >< (f >< f)

--Catamorfismo
cataL2D g = g . recL2D(cataL2D g) . outL2D

--Anamorfismo
anaL2D g = inL2D . recL2D(anaL2D g) . g

--Hylomorfismo
hyloL2D g h = cataL2D g . anaL2D h

\end{code}

\begin{center}
\large \textbf{Resoluções}
\end{center}

\begin{center}
\large {Resolução da função \emph{collectLeafs}}
\end{center}



\par Como podemos ver na função seguinte decidiu-se utilizar um catamorfismo onde foi necessário uma 
função g1 que guarda uma Caixa numa lista e um g2 que agrega a Caixa às Caixas retiradas da estrutura
de dados.

\begin{code}

collectLeafs :: X a b -> [a]
collectLeafs = cataL2D(either g1 g2)
                where g1 a = [a]
                      g2 (b,(a,c)) = a ++ c

\end{code}

\begin{center}
\large {Resolução da função \emph{dimen}}
\end{center}

Relativamente a esta função é caracterizada por se utilizar um catamorfismo que trata da estrutura
(X a b) onde existe um (Unid a) ou uma composição (Comp b (X a b) (X a b)) e cálcula as dimensões da Caixa ou das Caixas agregadas. 
\par Relembrando a definição da Caixa:
\begin{spec}
Caixa = ((Int,Int),(Texto,G.Color))
\end{spec}
Como se pode verificar no primeiro gene do catamorfismo, g1, são passadas as dimensões da Caixa de (Int,Int)
para (Float,Float), já no caso do segundo gene, g2, são recebidas mais que uma Caixa e, para tal, é
invocada a função compareDimen para calcular as agregações e, posteriormente, calcular as dimensões das
agregações.

\begin{code}

dimen :: X Caixa Tipo -> (Float, Float)
dimen = cataL2D(either g1 g2)
          where g1 a = int2float(p1 a)
                g2 (t,(b,c)) = compareDimen t b c

\end{code}

\begin{center}
\large {Resolução da função \emph{compareDimen}}
\end{center}

Como foi referido anteriormente, a função compareDimen, recebe uma combinação Comp b (X a b) (X a b),
ou seja, recebe um Tipo, as dimensões de duas Caixas. Efetua o cálculo da agregação das duas Caixas segundo
o Tipo recebido (V, Vd, Ve, H, Hb, Ht) e retorna as novas dimensões.

\begin{code}
compareDimen :: Tipo -> (Float,Float) -> (Float,Float) -> (Float,Float)
compareDimen V  (a,b) (x,y) = if (a>x) then (a,b+y) else (x,b+y)
compareDimen Vd (a,b) (x,y) = if (a>x) then (a,b+y) else (x,b+y)
compareDimen Ve (a,b) (x,y) = if (a>x) then (a,b+y) else (x,b+y)
compareDimen H  (a,b) (x,y) = if (b>y) then (a+x,b) else (a+x,y)
compareDimen Hb (a,b) (x,y) = if (b>y) then (a+x,b) else (a+x,y)
compareDimen Ht (a,b) (x,y) = if (b>y) then (a+x,b) else (a+x,y)
\end{code}

\begin{center}
\large {Resolução da função \emph{int2float}}
\end{center}

Esta função foi elaborada para auxiliar à transformação das dimensões de uma caixa, que são valores
inteiros, para floats.

\begin{code}
int2float :: (Int,Int) -> (Float,Float)
int2float (a,b) = (x,y)
                  where x = fromIntegral a :: Float
                        y = fromIntegral b :: Float
\end{code}

\begin{center}
\large {Resolução da função \emph{calcOrigins}}
\end{center}

Tal como é pedido no enunciado, esta função tem o propósito de calcular as coordenadas de um conjunto de Caixas no plano, tendo em conta
que a árvore de Caixas ficará com as folhas vazias no fim da execução da função. Para tal foi utilizado um anamorfismo que utiliza uma 
função g com os argumentos passados à calcOrigins. Esta função tem dois casos:
\begin{itemize}
  \item No primeiro caso temos uma única caixa, logo vai-se aplicar a injeção 1 (i1), ficando (Caixa, Origem);
  \item No segundo caso temos um par de caixas, logo vai-se aplicar a injeção 2 (i2) colocando as folhas vazias e calculando a posição da 
        próxima caixa. Para esse efeito, utiliza-se as dimensões das caixas obtidas através da função dimen e calculando o seu posicionamento
        consoante o tipo de agregação através da função calc. 
\end{itemize} 

\begin{code}
calcOrigins :: ((X Caixa Tipo),Origem) -> X (Caixa,Origem) ()
calcOrigins (c,o) = anaL2D g (o,c)
                      where g (o,(Unid a)) = i1(a,o)
                            g (o,(Comp a b c)) = i2((),((o,b),(aux a,c)))
                                    where aux V  = calc a z w
                                          aux Vd = calc a z w 
                                          aux Ht = calc a z w 
                                          aux H  | o==(0.0,0.0) = calc a z (0.0,p2 w) 
                                                 | otherwise    = calc a o z 
                                          aux _  = calc a o z 
                                          z =  dimen b
                                          w =  dimen c
\end{code}

\begin{center}
\large {Resolução da função \emph{calc}}
\end{center}


A função calc é uma das funções que auxilia a função calcOrigins e esta recebe como argumentos o Tipo da agregação,
a origem da primeira Caixa e as dimensões da segunda Caixa. Deste modo para cada tipo de agregação, a função calcula
as coordenadas (Origem) onde a segunda Caixa irá ficar colocada face a primeira Caixa.

\begin{code}                                         
calc :: Tipo -> Origem -> (Float, Float) -> Origem
calc V (o1,o2) (x,y) = (n1,n2)
                        where n1 = (o1/2) - (x/2)
                              n2 = o2
calc Vd (o1,o2) (x,y) = (n1,n2)
                        where n1 = o2 - y
                              n2 = o2
calc Ve (o1,o2) (x,y) = (n1,n2)
                        where n1 = o1
                              n2 = o2 + y
calc H (o1,o2) (x,y) | x==0.0             = (o1,(o2/2)-(y/2))
                     | (o1,o2)==(0.0,0.0) = (x,o2+(y/2))
                     | otherwise          = (o1+x,o2+(y/2))
calc Hb (o1,o2) (x,y) = (n1,n2)
                        where n1 = x
                              n2 = o2
calc Ht (o1,o2) (x,y) = (n1,n2)
                        where n1 = o1
                              n2 = o2 - y
\end{code}

\begin{center}
\large {Resolução da função \emph{agrup\_caixas}}
\end{center}

Este exercício tem como âmbito a implementação de uma função com a finalidade de agrupa varias Caixas conforme as respetivas origens. Desta forma 
será possível construir o esquema das Caixas, isto é, a figura. Para construir esta função foi utilizado um catamorfismo onde g1 representa apenas
uma Caixa (Origem,Caixa) e g2 um conjunto de tuplos (Origem,Caixa) representando várias Caixas.

\begin{code}
agrup_caixas :: X (Caixa,Origem) () -> Fig
agrup_caixas = cataL2D(either g1 g2)
                where g1 (a,b) = [(b,a)]
                      g2 (b,(a,c)) = a ++ c
\end{code}

\begin{center}
\large {Resolução da função \emph{caixas2Fig}}
\end{center}

Esta função foi elaborada com o intuito de receber um tuplo com uma L2D seguida de uma Caixa seguido de um Tipo e uma Origem, de modo a converter este
conjunto de Caixas numa Figura. Para tal esta função utiliza a calcOrigins que foi explicada anteriormente e utiliza a função agrupa\_caixas com os dados 
gerados, permitindo gerar uma Figura como se pode ver no código seguinte.

\begin{code}
caixas2Fig :: ((X Caixa Tipo),Origem) -> Fig
caixas2Fig = agrup_caixas.calcOrigins
\end{code}

\begin{center}
\large {Resolução da função \emph{mostra\_caixas}}
\end{center}

Neste último exercício, o objetivo principal é partindo de uma frase da linguagem L2D e de uma Origem, ou seja, de um par de coordenadas iniciais,
apresentar o desenho correspondente à agregação do conjunto de caixas da linguagem L2D no ecra. Para tal foi necessário desenvolver várias funções auxiliares
que nos possibilita-se fazer o cálculo da Figura final. Primeiramente fornecemos a L2D e a Origem a função caixa2Fig que irá calcular a figura onde 
posteriormente, através da função fig2Pictures, a figura gerada vai ser convertida numa lista de G.Picture e, finalmente, irá ser feito o display (dado um
valor do tipo G:picture abre uma janela com esse valor desenhado) dessa lista final para o ecra.

\begin{code}
mostra_caixas :: (L2D,Origem) -> IO ()
mostra_caixas = display.G.pictures.fig2Pictures.caixas2Fig
\end{code}

\begin{center}
\large {Resolução da função \emph{fig2Pictures}}
\end{center}

A função fig2Pictures é uma função utilizada pela mostra\_caixas com o intuito de transformar uma Figura numa lista com G.Picture. É uma função composta por
dois casos: o caso em que não lhe foi fornecida figura e retorna uma lista vazia e, um segundo caso, onde recebe uma figura que é uma lista de tuplos
(Origem,Caixa) em que recursivamente aplica a função crCaixaAux a cada tuplo da Figura. 

\begin{code}
fig2Pictures :: Fig -> [G.Picture]
fig2Pictures [] = []
fig2Pictures ((o,c):t) = (crCaixaAux o (p1 c) (p2 c)):(fig2Pictures t)
\end{code}


\begin{center}
\large {Resolução da função \emph{crCaixaAux}}
\end{center}

É através desta função que a função fig2Pictures realiza os seus cálculos, isto é, a crCaixaAux recebe uma Origem, as coordenadas da Caixa e um tuplo
composto pelo Texto e a G.Color (a cor da caixa) e cria a G.Picture. Com esta função é possível fornecer todos os dados necessários para a função fornecida
crCaixa que cria um rectângulo com base numa coordenada, um valor para a largura, um valor para a altura, um texto que irá servir de etiqueta, e a cor pretendida.

\begin{code}
crCaixaAux :: Origem -> (Int,Int) -> (String,G.Color) -> G.Picture
crCaixaAux o (x,y) (n,c) = crCaixa o a b n c
                        where a = fromIntegral x :: Float
                              b = fromIntegral y :: Float
\end{code}

\begin{center}
\large {Resolução da função \emph{caixasAndOrigin2Pict}}
\end{center}

Por fim, esta última função deste problema é uma sequência de funçoes criadas anteriormente. É recebido como argumento um tuplo (L2D,Origem) com o intuito de
gerar uma G.Picture, para isso utiliza-se a função caixas2Fig de modo a gerar cada Figura para posteriormente convertê-las em G.Pictures através da função fig2Pictures.

\begin{code}
caixasAndOrigin2Pict :: ((X Caixa Tipo),Origem) -> G.Picture         
caixasAndOrigin2Pict = G.pictures.fig2Pictures.caixas2Fig

\end{code}

\subsection*{Problema 3}
Considerando a fórmula que dá a série de Taylor da função coseno:
\begin{eqnarray*}
  cos\ x n = \frac{(-1)^i}{(2i)!} x^{2i}
\end{eqnarray*}
Substituindo o n por n+1 teremos:
\begin{eqnarray*}
  cos\ x (n+1) = \frac{(-1)^{n+1}}{(2(n+1))!} x^{2(n+1)}
\end{eqnarray*}
Obtemos assim h x n. Substituindo novamente o n por n+1 iremos obter o h x (n+1):
\begin{eqnarray*}
  h\ x (n+1) = \frac{(-1)^{n+2}}{(2n+4)!} x^{2n+4} = \frac{(-1)^{n+1}*(-1)*x^{2n+2}*x^{2}}{(2n+2)!*(2n+3)*(2n+4)} = \frac{(-1)^{n+1}}{(2(n+1))!} x^{2(n+1)}*\frac{(-x)^2}{(2n+3)*(2n+4)}
\end{eqnarray*}
Como resultado obtivemos de um lado o h x n e de outro resto do somatório inicial, daí é possível retirar:
\begin{spec}
s n = 2n+3
s (n+1) = (2n+3)+2
s 0 = 3
v n = 2n+4
v (n+1) = (2n+4)+2
v 0 = 4
\end{spec}

Solução:
\begin{code}
cos' x = prj . for loop init where
    loop (cs,h,s,v) = (h + cs, -x^2 /(s*v) * h, 2+s, 2+v)
    init = (1,(-x^2)/2,3,4)
    prj (cs,h,s,v) = cs
\end{code}

\subsection*{Problema 4}
Triologia ``ana-cata-hilo":
\begin{center}
\large \textbf{Out}
\end{center}
\begin{code}

outFS (FS l) = map (id >< outNode) l

outNode (File b) = Left b
outNode (Dir a) = Right a

\end{code}

\begin{center}
\large \textbf{Anamorfismo, Catamorfismo e Hilomorfismo}
\end{center}

\begin{code}

baseFS f g h = map (f >< (g -|- h))

--Catamorfismo
cataFS :: ([(a, Either b c)] -> c) -> FS a b -> c
cataFS g = g . recFS(cataFS g) . outFS

--Anamorfismo
anaFS :: (c -> [(a, Either b c)]) -> c -> FS a b
anaFS g = inFS . recFS(anaFS g) . g

--Hylomorfismo
hyloFS g h = cataFS g . anaFS h

\end{code}

\begin{center}
\large \textbf{Diagrama de Catamorfismo}
\end{center}

\begin{eqnarray*}
\xymatrix@@C=6cm{
    |FS a b|
    \ar[d]_-{|cataFS g|}
    \ar@@/^1pc/[r]^{|outFS|}
    \ar@@{}[r]||-{\cong}
&
    |(X >< (A + FS a b))*|
    \ar[d]^{|map (id >< (id + cataFS g)|}
    \ar@@/^1pc/[l]^{|inFS|}
\\
     |B|
&
     |(X >< (A + B))*|
      \ar[l]^-{|g|}
}
\end{eqnarray*}

Outras funções pedidas:

\begin{center}
\large {Resolução da função \emph{check}}
\end{center}
A função check verifica a integridade do sistema de ficheiros, isto é, verifica que não existem identificadores
repetidos dentro da mesma directoria. Primeiro foi criada a função checkRep de modo a verificar cada elemento de uma lista, retornando True
caso não encontre repetidos ou retornando False caso encontre algum elemento repetido.
A função check é composta por um catamorfismo onde aplicará a função anterior para ambos os lados da árvore, ou seja, percorrendo assim cada elemento da árvore (sistema de ficheiros), aplicando a função checkRep para verificar se o elemento em questão está repetido na árvore.
\begin{code}
checkRep :: Eq a => [a] -> Bool
checkRep [] = True
checkRep (h:t) = (not (elem h t)) && (checkRep t)

check :: (Eq a) => FS a b -> Bool
check = cataFS (checAux)
              where checAux [] = True
                    checAux ((a, Left b):t) | (checkRep (map p1 ((a, Left b):t))) = True && (checAux t)
                                            | otherwise = False
                    checAux ((a, Right b):t) | b = True && checAux t 
                                             | otherwise = False
\end{code}                                    

\begin{center}
\large {Resolução da função \emph{tar}}
\end{center}
Nesta função é necessário fazer a recolha do conteúdo de todos os ficheiros num arquivo indexado pelo path e, para tal, começamos por
criar uma função auxiliar addToTuple que recebe um elemento e uma lista de tuplos em que o primeiro elemento de cada tuplo é uma lista
e adiciona esse elemento ao longo da lista de tuplos. De seguida na função principal é composta por um catamorfismo onde no caso de 
ser o ramo da esquerda da árvore guarda o elemento numa lista, já no caso do ramo da direita aplica-se a função auxilar e concatena-se 
recursivamente o resultado obtido para o resto da lista.

\begin{code} 
addToTuple :: a -> [([a], b)] -> [([a], b)]
addToTuple _ [] = []
addToTuple x (([],_):t) = addToTuple x t
addToTuple x ((l,a):t)  = ((x:l),a):(addToTuple x t)

tar :: (Eq a) => FS a b -> [(Path a, b)]
tar = cataFS (tarAux)
            where tarAux [] = []
                  tarAux ((a, Left b):t)  = ([a],b):(tarAux t)
                  tarAux ((a, Right b):t) = (addToTuple a b) ++ (tarAux t)
\end{code}

\begin{center}
\large {Resolução da função \emph{untar}}
\end{center}
Através desta função pertende-se transformar um arquivo com o conteúdo dos ficheiros indexado pelo path num sistema de ficheiros. Para isso
decidiu-se utilizar a sugestão fornecida, ou seja, utilizar a função joinDupDirs para juntar directorias que estejam na mesma pasta e
que possuam o mesmo identificador. Esta função baseada num anamorfismo irá realizar o inverso da função tar, usado para o efeito duas funções auxiliares, sendo que a primeira é usada no anamorfismo e a segunda é usada após o anamorfismo (joinDupDirs). A função auxiliar faz com que isso 
aconteça devido a analisar todos os casos, ou seja, caso a lista seja vazia, continua vazia, caso a lista tenha apenas um elemento coloca-se o elemento à esquerda e concatena-se a lista do sistema de ficheiros, em último caso coloca-se o elemento à direita e concatena-se a lista do sistema de ficheiros.

\begin{code}
untar :: (Eq a) => [(Path a, b)] -> FS a b
untar = joinDupDirs . anaFS (untarAux)
              where untarAux [] = []
                    untarAux (((p:ps),b):rl) | (ps == []) = [(p, Left b)] ++ untarAux rl 
                                             | otherwise  = [(p, Right [(ps,b)])] ++ untarAux rl
\end{code}

\begin{center}
\large {Resolução da função \emph{find}}
\end{center}
Nesta função é necessário localizar todos os paths onde existe um determinado ficheiro e, para tal, começamos por criar uma função auxiliar 
addToList que irá adicionar um ficheiro a uma lista existente. Desta feita, utilizou-se um catamorfismo que verifica se caso a lista é vazia 
e mantém a lista vazia, verifica se caso o ficheiro é igual ao elemento à esquerda, mantendo-o e aplicando a função recursivamente ao resto 
da lista ou verifica se caso o elemento estiver à direta adiciona o elemento a lista e concatena com a chamada recursiva da função para o 
resto da lista.

\begin{code}
addToList :: a -> [[a]] -> [[a]]
addToList  _ [] = []
addToList x ([]:t) = (addToList x t)
addToList x (h:t) = (x:h):(addToList x t)
 
find :: (Eq a) => a -> FS a b -> [Path a]
find a = cataFS (findAux a)
          where findAux file [] = []
                findAux file ((a,Left b):t) | (file == a) = ([a]):(findAux file t)
                                            | otherwise   = findAux file t
                findAux file ((a,Right b):t) = (addToList a b) ++ (findAux file t)
\end{code}

\begin{center}
\large {Resolução da função \emph{new}}
\end{center}

\begin{code}

new :: (Eq a) => Path a -> b -> FS a b -> FS a b
new = undefined
\end{code}

\begin{center}
\large {Resolução da função \emph{cp}}
\end{center}


\begin{code}

cp :: (Eq a) => Path a -> Path a -> FS a b -> FS a b
cp = undefined
\end{code}

\begin{center}
\large {Resolução da função \emph{rm}}
\end{center}


\begin{code}

rm :: (Eq a) => (Path a) -> (FS a b) -> FS a b
rm = undefined
\end{code}

\begin{center}
\large {Resolução da função \emph{auxJoin}}
\end{center}


\begin{code}

auxJoin :: ([(a, Either b c)],d) -> [(a, Either b (d,c))]
auxJoin = undefined
\end{code}

\begin{center}
\large {Resolução da função \emph{cFS2Exp}}
\end{center}


\begin{code}

cFS2Exp :: a -> FS a b -> (Exp () a)
cFS2Exp = undefined
\end{code}

%----------------- Fim do anexo com soluções dos alunos ------------------------%

%----------------- Índice remissivo (exige makeindex) -------------------------%

\printindex

%----------------- Bibliografia (exige bibtex) --------------------------------%

\bibliographystyle{plain}
\bibliography{cp1819t}

%----------------- Fim do documento -------------------------------------------%
\end{document}
