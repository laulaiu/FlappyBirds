import processing.core.PImage;

import java.util.Random;

public class Pipes{
    Main main;
    PImage img ;
    int widthPipe;
    int heightPipe;
    public Pipes(Main main, int a) {
        this.main = main;
        img = main.loadImage("assets-master/sprites/pipe-green.png");
        widthPipe = main.width/5;
        heightPipe= main.height/5;

        creatPipeDisplay(main,a);
    }
    private void creatPipeDisplay(Main main, int a) {

        main.translate(0,0);
        int rand = new Random().nextInt(100,main.height -100);


        //pipe de cima
        main.scale(1,-1);
        getPipe(a,-250);
        //pipe de baixo
        main.scale(1,-1);
        getPipe(a,main.height - 100);

    }

    private void getPipe(int x, int y){
        main.image(img,x,y, img.width+30,img.height+30);
    }


}
