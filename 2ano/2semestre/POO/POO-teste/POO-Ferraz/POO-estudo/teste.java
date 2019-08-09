void add(Entrada ed) throws ExistingEntryException {
    if(this.entradas.containsKey(ed.getTermo())) {
        throw new ExistingEntryException
    } else {
        this.entradas.put(ed.getTermo(), ed.clone())
    }
}

boolean exists(String termo) {
    if(this.entradas.containsKey(termo)) {
        return true;
    } else {
        return false;
    }
}

Entrada get(String termo) throws EntryDoesNotExistException {
    for(String s : this.entradas.keySet()) {
        if(termo.equals(s)) {
            return this.entradas.get(s).clone()
        }
    }
    throw new EntryDoesNotExistException
}

Collection<Entrada> getAll() {
    Collection<Entrada> res = new TreeSet<>();

    for(String s : this.entradas.keySet()) {
        res.add(this.entradas.get(s).clone())
    }

    return res;
}

boolean sinonimos(String termo1, String termo2) {
    Entrada e1 = this.entradas.get(termo1);
    Entrada e2 = this.entradas.get(termo2);

    if(e1.getDefinicao().equals(e2.getDefinicao())) {
        return true;
    }

    return false;

}

Map<String, List<String>> getSinonimos() {
    Map<String, List<String>> res = new TreeMap<>();
    for(Entrada e1 : this.entradas.values()) {
        if(!res.containsKey()) {
            List<String> aux = new List<>();
            String s = e1.getDefinicao();
            aux.add(e1.getTermo())
            for(Entrada e2 : this.entradas.values()) {
                if(!e1.equals(e2)) {
                    if(s.equals(e2.getDefinicao())) {
                        aux.add(e2.getTermo())
                    }
                }
            }
            res.put(s, aux)
        }
    }

    return res;
}

public Matriz(int n, int m) {
    this.linhas = new List<>();
    for(i = 0; i < n; i++) {
        List<Object> l2 = new List<>();
        for(j = 0; j < m; j++) {
            l2.add(null);
        }
        this.linhas.add(l2);
    }
}

public Object get(int l, int c) throws ArrayIndexOutOfBoudsException {
    if(l > this.linhas.size() && c > this.linhas.get(0).size()) {
        throw new ArrayIndexOutOfBoudsException;
    } else {
        return this.linhas.get(l).get(c).clone();
    }
}

public int size() {
    return this.linhas.size() * this.linhas.get(0).size();
}

public int count(Object o) {
    int count = 0
    for(List<Object> l1 : this.linhas) {
        for(Object o1 : l1) {
            if(o1.equals(o)) {
                count++;
            }
        }
    }
    return count;
}
