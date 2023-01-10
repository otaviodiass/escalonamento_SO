
public class Processo {
    public int id;
    public int tempo;
    public int prioridade;
    public int parada = 0;
    public int tempParada = 0;

    public Processo(int id, int tempo, int prioridade) {
        this.id = id;
        this.tempo = tempo;
        this.prioridade = prioridade;
    }

    public Processo(int id, int tempo, int prioridade, int parada, int tempParada) {
        this.id = id;
        this.tempo = tempo;
        this.prioridade = prioridade;
        this.parada = parada;
        this.tempParada = tempParada;
    }

    public int getId() {
        return id;
    }

    public int getTempo() {
        return tempo;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setTempo(int novoTempo){
        this.tempo = novoTempo;
    }

}