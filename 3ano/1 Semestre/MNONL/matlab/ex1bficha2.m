function [F,d] = ex1ficha2(x)
F=[(x-1)*exp(x)-x-17];
if nargout>1
    d=exp(x)+(x+1)*exp(x)-1;
end
