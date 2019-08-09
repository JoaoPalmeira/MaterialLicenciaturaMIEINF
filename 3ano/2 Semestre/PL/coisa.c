#define FLOAT 1
#define ID 2
#define PESQ 3
#define PDIR 4
#define CESQ 5
#define RET 6
#define LE 7
#define NUM 8
#define PI 9 
#define DP 10
#define MR 11
#define PV 12
#define CDIR 13

"float" 				{return FLOAT;}
[a-zA-Z_][a-zA-Z0-9_-]* 				{return ID;}
"" 					{return PESQ;}
"" 					{return PDIR;}
"" 					{return CESQ;}
"" 					{return RET;}
"" 					{return LE;}
"" 					{return NUM;}
"" 					{return PI;}
"" 					{return DP;}
"" 					{return MR;}
"" 					{return PV;}
"" 					{return CDIR;}

int main(){
	int s;
	while(s=yylex()){
		printf("%d", s);
	}
	return (0);
}