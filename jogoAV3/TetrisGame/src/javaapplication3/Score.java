
package javaapplication3;

/**
 *
 * @author José
 */
public class Score {
	
	
    
    
    private String Nome;
    private int Pontos;

    public Score(String Nome, int Pontos){
        this.Pontos = Pontos;
        this.Nome = Nome;
        
    }
    /**
     * @return the Nome
     */
    public String getNome() {
        return Nome;
    }

    /**
     * @param Nome the Nome to set
     */
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    /**
     * @return the Pontos
     */
    public int getPontos() {
        return Pontos;
    }

    /**
     * @param Pontos the Pontos to set
     */
    public void setPontos(int Pontos) {
        this.Pontos = Pontos;
    }

    /**
     * @return the Score
     */
    
}
