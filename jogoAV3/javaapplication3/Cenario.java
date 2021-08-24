
package javaapplication3;

import java.io.File;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class Cenario {
            private String sfxB = "Pop Up Sound Effect  GG Green Screens.mp3";     // Musica de quebra peça
            private AudioClip BreakLine = new AudioClip(new File(sfxB).toURI().toString());
    
    private boolean salva = false;
    private int score=0;
    
    public int[][] cen = {
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}
    };
    private int[][] salvaCen = {
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}
    };

    
    
    public void salvaCenario(int x, int y, pecas p, int a,int r){
        int quebra=0;
        int k = y+p.PECAS[r][a].length;
        int w = x+p.PECAS[r][a][0].length;
        for(int i = 0;i<cen.length;i++){
            for(int j = 0;j<cen[i].length;j++){
                if(i>=y&&i<k&&j>=x&&j<w){
                    if(p.PECAS[r][a][i-y][j-x]!=0)
                    salvaCen[i][j] = a;
                }
            }
        }
        
       for(int i = 0;i<salvaCen.length;i++){
            for(int j = 0;j<salvaCen[i].length;j++){
                if(salvaCen[i][j]!=-1){
                    quebra++;
                }
                if(quebra==10){
                    setScore(getScore() + 1);
                    BreakLine.play();
                    for(int b = i; b>0;b--){
                        for(int c = 0; c<salvaCen[b].length;c++){
                            salvaCen[b][c]=salvaCen[b-1][c];
                        }
                    }
                    
                }
                
            }

            quebra=0;
        }
        
    }
    public void zeraCenario(){
        
        for(int i=0;i<salvaCen.length;i++){
            for(int j=0;j < salvaCen[i].length; j++){
            salvaCen[i][j] = -1;
        }
        }
        for(int i=0;i< cen.length;i++){
            for(int j=0;j < cen[i].length; j++){
            cen[i][j] = -1;
        }
        }
        
    }
    public void constroiCenario(){
        
        for(int i=0;i<cen.length;i++){
            for(int j=0;j < cen[i].length; j++){
            cen[i][j] = salvaCen[i][j];
        }
        }
        
        
    }
    
    public void criapeca(int x, int y, pecas p, int a, int r){
       
        constroiCenario();
        
        int k = y+p.PECAS[r][a].length;
        int w = x+p.PECAS[r][a][0].length;
        for(int i = 0;i<cen.length;i++){
            for(int j = 0;j<cen[i].length;j++){
                if(i>=y&&i<k&&j>=x&&j<w){
                    if(p.PECAS[r][a][i-y][j-x]!=0)
                    cen[i][j] = a;
                }
            }
        }                        
    }
    
 public int Colisao(int y,int x,pecas p, int a,int r){
       if(x<0||x+p.PECAS[r][a][0].length>cen[0].length||y<0)
           return 1;
        for(int i=0;i<p.PECAS[r][a].length;i++){
            for(int j=0;j<p.PECAS[r][a][i].length;j++){
            if(salvaCen[i+y][x+j]!=-1&&p.PECAS[r][a][i][j]==1)
                return 1;
            }
        }
        return 0;
        
    }
    public int ColisaoBaixo(int y,int x,pecas p, int a, int r){
       if(y+p.PECAS[r][a].length>cen.length)
           return -1;
        for(int i=0;i<p.PECAS[r][a].length;i++){
            for(int j=0;j<p.PECAS[r][a][i].length;j++){
            if(salvaCen[i+y][x+j]!=-1&&p.PECAS[r][a][i][j]==1)
                return -1;
            }
        }
        return 0;
        
    }
    
    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }
        
    };
    

