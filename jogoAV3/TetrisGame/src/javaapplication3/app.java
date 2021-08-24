
package javaapplication3;
import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextInputDialog;
import javafx.scene.media.AudioClip;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
/**
 *
 * @author Pichau
 */
public class app extends Application {
    public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage s) throws Exception {
            int TAM=40;
            Group root = new Group();
            Scene t = new Scene( root );
            s.setScene( t );
            pecas p = new pecas();
            Cenario q = new Cenario();
            Random ran = new Random();
            
            String FileJogo = "Tetris Theme (Dubstep Remix).mp3";     // Musica no jogo
            AudioClip inGame = new AudioClip(new File(FileJogo).toURI().toString());
            
            String cabosse = "Gameover.mp3";     // For example
            AudioClip gameOver = new AudioClip(new File(cabosse).toURI().toString());
            
            String men = "Original Tetris theme (Tetris Soundtrack).mp3";     // Musica no menu
            AudioClip inMenu = new AudioClip(new File(men).toURI().toString());
            
            String me = "Punch.mp3";     // Transição
            AudioClip Punch = new AudioClip(new File(me).toURI().toString());
            inMenu.play(0.07);
            Canvas canvas = new Canvas(TAM*10, TAM*16);
            root.getChildren().add( canvas );
            GraphicsContext c = canvas.getGraphicsContext2D();
            ArrayList<String> input = new ArrayList<String>();
            Ranking ranking = new Ranking();
 
        

        t.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        input.add( code );
                }
            });
            long b = System.nanoTime();
            new AnimationTimer()
    {
        int x = 3 , y = -1 , verificarPecaNova=0 , verificarPecaNovaD=0 , r=0 , TAM = 40 , k = 0 ,prox = ran.nextInt(7) , a = ran.nextInt(7),Status = 2;
    	TextInputDialog dialogoNome = new TextInputDialog();
        @Override
        public void handle(long currentNanoTime)
        {
            if(Status==1){
                if(inGame.isPlaying()==false){
                    inGame.play(0.3);
                }
                
            if(Math.round((currentNanoTime-b)/1000000000.0)==k){

                    y++;
                    verificarPecaNova=q.ColisaoBaixo(y, x, p, a,r);
                    y+=q.ColisaoBaixo(y, x, p, a,r);
                    q.criapeca(x, y, p, a,r);
                    constroiCen( p, q, c,  TAM);
                    proxPeca(p, q,c,  TAM,prox);
                    if(verificarPecaNova==-1){
                    q.salvaCenario(x, y, p, a,r);
                    y=0;
                    x=3;
                    r=0;
                    a=prox;
                    prox=ran.nextInt(7);
                    q.criapeca(x, y, p, a,r);
                    constroiCen( p, q, c,  TAM);
                    if(q.ColisaoBaixo(y, x, p, a, r)==-1||q.Colisao(y, x, p, a, r)==1){
                        y=-1;
                x=3;
                prox=ran.nextInt(7);
                a=ran.nextInt(7);
                q.zeraCenario();
                inGame.stop();
                gameOver.play(0.6);
                Status = 3;
                c.setFont(Font.font("Serif", FontWeight.BOLD, 40));
                c.setFill(Color.YELLOW);
                c.fillText("GAME OVER", 70 , 125);
                c.setFont(Font.font("Serif", FontWeight.BOLD, 35));
                c.setStroke(Color.BLACK);
                c.setFill(Color.WHITE);
                c.fillText("Aperte ENTER", 80 , 200);
                c.strokeText("Aperte ENTER", 80 , 200);
                dialogoNome.setTitle("Ranking");
                dialogoNome.setHeaderText("Digite seu nome para o ranking");
                dialogoNome.setContentText("Nome:");
                dialogoNome.show();
                    }
                    proxPeca(p, q,c,  TAM,prox);
                    verificarPecaNova=0;
                    }
                
                    k+=1;
                    
        }
            
            if(input.contains("LEFT")){
                x--;
                x+=q.Colisao(y, x, p, a,r);
                q.criapeca(x, y, p, a,r);
                constroiCen( p, q, c,  TAM);
                proxPeca(p, q,c,  TAM,prox);
                input.remove(0);
                
            }else if(input.contains("RIGHT")){
                x++;
                x-=q.Colisao(y, x, p, a,r);
                q.criapeca(x, y, p, a,r);
                constroiCen( p, q, c,  TAM);
                proxPeca(p, q,c,  TAM,prox);
                input.remove(0);
                
            }else if(input.contains("UP")){
                if(r<3){
                        r++;
                    }else{
                        r=0;
                    }
                if(q.Colisao(y, x, p, a, r)==1||q.ColisaoBaixo(y, x, p, a, r)==-1){
                    if(r!=0){
                        r--;
                    }else{
                        r=3;
                    }
                }
                q.criapeca(x, y, p, a,r);
                constroiCen( p, q, c,  TAM);
                proxPeca(p, q,c,  TAM,prox);
                input.remove(0);
                
            }else if(input.contains("DOWN")){
            	input.remove(0);
                y++;
                verificarPecaNovaD=q.ColisaoBaixo(y, x, p, a,r);
                y+=q.ColisaoBaixo(y, x, p, a,r);
                q.criapeca(x, y, p, a,r);
                constroiCen( p, q, c,  TAM);
                proxPeca(p, q,c,  TAM,prox);
                if(verificarPecaNovaD==-1){
                    q.salvaCenario(x, y, p, a,r);
                    y=0;
                    x=3;
                    r=0;
                    a=prox;
                    prox=ran.nextInt(7);
                    q.criapeca(x, y, p, a,r);
                    constroiCen( p, q, c,  TAM);
                    if(q.ColisaoBaixo(y, x, p, a, r)==-1||q.Colisao(y, x, p, a, r)==1){
                        y=-1;
                x=3;
                prox=ran.nextInt(7);
                a=ran.nextInt(7);
                q.zeraCenario();
                inGame.stop();
                gameOver.play(0.6);
                dialogoNome.setTitle("Ranking");
                dialogoNome.setHeaderText("Digite seu nome para o ranking");
                dialogoNome.setContentText("Nome:");
                dialogoNome.show();
                Status = 3;
                c.setFont(Font.font("Serif", FontWeight.BOLD, 40));
                c.setFill(Color.YELLOW);
                c.fillText("GAME OVER", 70 , 125);
                c.setFont(Font.font("Serif", FontWeight.BOLD, 35));
                c.setStroke(Color.BLACK);
                c.setFill(Color.WHITE);
                c.fillText("Aperte ENTER", 80 , 200);
                c.strokeText("Aperte ENTER", 80 , 200);
                    }
                    proxPeca(p, q,c,  TAM,prox);
                    verificarPecaNovaD=0;
                    }
                
                
            }else if(input.contains("ENTER")){
                Punch.play(0.4);
                inGame.stop();
                
                c.setFont(Font.font("Serif", FontWeight.BOLD, 40));
                c.setFill(Color.WHITE);
                c.fillText("    PAUSED ", 70 , 125);
                Status = 0;
                input.remove(0);
            }else if(input.contains("ESCAPE")){
                y=-1;
                x=3;
                prox=ran.nextInt(7);
                a=ran.nextInt(7);
                q.setScore(0);
                q.zeraCenario();
                Status = 2;
                inGame.stop();
                inMenu.play(0.07);
                Punch.play(0.4);
                input.remove(0);
            }else if(input.isEmpty()==false){
            input.remove(0);
            }
        
           }else if(Status ==0){
                if(Math.round((currentNanoTime-b)/1000000000.0)==k){
                    k++;
                }
            if(input.contains("ENTER")){
                inGame.play(0.3);
                Punch.play(0.4);
                Status = 1;
                input.remove(0);
            }else if(input.contains("ESCAPE")){
                y=-1;
                x=3;
                prox=ran.nextInt(7);
                a=ran.nextInt(7);
                q.setScore(0);
                q.zeraCenario();
                Status = 2;
                inGame.stop();
                inMenu.play(0.07);
                Punch.play(0.4);
                input.remove(0);
            }else if(input.isEmpty()==false){
                input.remove(0);
            }
            
        }else if(Status ==2){
            if(inMenu.isPlaying()==false){
                    inMenu.play(0.07);
                }
            if(Math.round((currentNanoTime-b)/1000000000.0)==k){
                    k++;
                }
            constroiCen( p, q, c,  TAM);
            Menu(c);
            CriaRanking(c, ranking);
            if(input.contains("ENTER")){
                inMenu.stop();
                inGame.play(0.3);
                Punch.play(0.4);
                Status = 1;
                input.remove(0);
            }else if(input.contains("ESCAPE")){
                input.remove(0);
                Punch.play(0.4);
                s.close();
            }else if(input.isEmpty()==false){
                input.remove(0);
            }
            
        }else if(Status ==3){
        	if(inGame.isPlaying()){
                inGame.stop();
            }
        	if(Math.round((currentNanoTime-b)/1000000000.0)==k){
                k++;
            }
        	if(input.contains("ENTER")){
        		ranking.cadastrar(dialogoNome.getResult(), q.getScore());
        		ranking.ordRanking();
        		q.setScore(0);
                input.remove(0);
                Punch.play(0.4);
                inMenu.play(0.07);
                Status=2;
            }else if(input.isEmpty()==false){
                input.remove(0);
            }
        	
        	
        }
        
        }
        
    }.start();
       s.setTitle("TETRIS GAME");
       s.sizeToScene();
       s.setResizable(false);
        s.show();
}
        
        public static void constroiCen(pecas p, Cenario q,GraphicsContext c, int TAM){
            for(int i = 0; i<q.cen.length*TAM;i+=TAM){
                    for(int j = 0; j<q.cen[i/TAM].length*TAM;j+=TAM){
                        if(q.cen[i/TAM][j/TAM]!=-1){
                           c.setFill(p.Cores[q.cen[i/TAM][j/TAM]]);
                           c.fillRect(j, i, TAM, TAM);
                           c.setStroke(Color.BLACK);
                           c.strokeRect(j, i, TAM, TAM);
                        }else{
                            c.setFill(Color.DIMGREY);
                            c.fillRect(j, i, TAM, TAM);
                            
                        }
                    }
                    
                }
        }
        public static void proxPeca(pecas p,Cenario q, GraphicsContext c, int C, int b){
            int TAM = C/3;
            for(int i = 0; i<p.PECAS[0][b].length*TAM;i+=TAM){
                    for(int j = 0; j<p.PECAS[0][b][0].length*TAM;j+=TAM){
                        if(p.PECAS[0][b][i/TAM][j/TAM]!=0){
                           c.setFill(p.Cores[b]);
                           c.fillRect(j+10, i+10, TAM, TAM);
                           c.setStroke(Color.BLACK);
                           c.strokeRect(j+10, i+10, TAM, TAM);
                        }else{
                            c.setFill(Color.DIMGREY);
                            c.fillRect(j+10, i+10, TAM, TAM);
                            
                        }
                    }
                    
                }
            c.setFont(Font.font("Serif", FontWeight.BOLD, 20));
            c.setFill(Color.WHITE);
            c.fillText("Score: "+q.getScore(), 310 , 30);
            
        }
        
        public static void Menu(GraphicsContext c){
            
            c.setFont(Font.font("Serif", FontWeight.BOLD, 70));
            c.setFill(Color.YELLOW);
            c.fillText("TETRIS", 70 , 125);
            c.setFill(Color.LIGHTBLUE);
            c.fillText("GAME", 85 , 185);
            c.setFont(Font.font("Serif", FontWeight.BOLD, 10));
            c.setFill(Color.WHITE);
            c.fillText("Regras: Junte uma linha de blocos para ganhar pontos! Use as setas para controlar o jogo.", 10 , 590);
            c.fillText("Aperte ESC para o menu, ENTER para pausar o jogo. Aperte ENTER para iniciar", 10 , 610);
            c.fillText("Se já estiver no menu, o ESC fecha o jogo.", 10 , 630);
            c.setFont(Font.font("Serif", FontWeight.BOLD, 10));
            c.fillText("A José and Pedro's game.®", 280 , 635);
            
        }
        public static void CriaRanking(GraphicsContext c, Ranking ranking){
        	c.setFont(Font.font("Serif", FontWeight.BOLD, 15));
        	c.setFill(Color.WHITE);
        	
        	for(int i=0;i<ranking.size();i++) {
        		c.fillText((i+1)+". Nome: "+ranking.getNome(i)+". Score: "+ ranking.getPontos(i), 50 , (i*20)+220);
        		if(i>13)
        			break;
        		
        		
        	}
        	
        	
        	
        }
        
        
}