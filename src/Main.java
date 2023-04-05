import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class Main extends PApplet {
    public static void main(String[] args) {
        PApplet.runSketch(new String[]{"MAIN"},new Main());
    }

    String backGround_assets = "assets-master/sprites/background-night.png";
    String base_assets = "assets-master/sprites/base.png";

    String pipe_top_assets = "assets-master/sprites/pipe-green-top.png";
    String pipe_bot_assets = "assets-master/sprites/pipe-green-bot.png";
    String bird_assets1 = "assets-master/sprites/redbird-downflap.png";
    String bird_assets2 = "assets-master/sprites/redbird-midflap.png";
    String bird_assets3 = "assets-master/sprites/redbird-upflap.png";
    String[] bird_assets = new String[]{bird_assets1,bird_assets2,bird_assets3};

    int cenarioX,cenarioY;
    int chaoCenarioX,chaoCenarioY;
    double Vky, gravity;
    int birdX,birdY;
    int[] pipeX,pipeY;
    PImage backGpic ;
    PImage pipebot_pic ;
    PImage pipetop_pic ;
    PImage[] birdpic ;
    PImage birdpic1 ;
    PImage birdpic2 ;
    PImage birdpic3 ;
    PImage basepic ;

    @Override
    public void settings() {
        size(800,660);
    }

    @Override
    public void setup() {

        frameRate(60);
        backGpic = loadImage(backGround_assets);
        pipebot_pic = loadImage(pipe_bot_assets);
        pipetop_pic = loadImage(pipe_top_assets);
        PImage[] pip = {loadImage(bird_assets[0]),loadImage(bird_assets[1]), loadImage(bird_assets[2])};
        birdpic = pip;
        birdpic1 = loadImage(bird_assets1);
        birdpic2 = loadImage(bird_assets2);
        birdpic3 = loadImage(bird_assets3);
        basepic = loadImage(base_assets);

        pipeX = new int[5];
        pipeY = new int[pipeX.length];
        birdY = 120;
        birdX = 120;
        gravity = 1.5;

        for(int i=0; i<pipeX.length; i++){
            pipeX[i] = width+ 200*i;
            pipeY[i] = (int)random(-300,-20);
        }
    }
    int game = 1;

    @Override
    public void draw() {

        if(game == 0){
            background(0);
            backGame();
            bird();
            pipe();
        }else{
            text("YOU LOSE",20,100);
        }

        fill(0);
        text("FR: "+frameRateLastNanos,100,40);
    }

    private void pipe() {
        for(int i=0; i<pipeX.length; i++){

            image(pipebot_pic, pipeX[i],(float) pipeY[i] , pipetop_pic.width*1.3f,pipetop_pic.height*1.3f);
            image(pipetop_pic,pipeX[i],(float) (pipeY[i] + height -110) , pipebot_pic.width*1.3f,pipebot_pic.height*1.3f);
            fill(0);
            text("Y-top: "+(int)(pipeY[i] + pipebot_pic.height*1.3f),
                    pipeX[i],
                    pipeY[i] + pipebot_pic.height*1.3f+2);
            text("Y-bot: "+(float) (pipeY[i] + height -110),
                    pipeX[i],
                    (float) (pipeY[i] + height -110));
            pipeX[i] -= 4;
            if(pipeX[i] < -200 ){
                pipeX[i] = width;
            }
            square(pipeX[i],pipeY[i] + pipebot_pic.height*1.3f,15);

            if( (birdX+(birdpic[0].width/2) > pipeX[i]) & (birdX-(birdpic[0].width/2) < pipeX[i] + pipetop_pic.width)){
                if( (birdY+(birdpic[0].height) > (float) (pipeY[i] + height -110) ) | (birdY < pipeY[i] + pipebot_pic.height*1.3f-5) ){
                    game = -1;
                }
            }

        }
    }


    int changeBird =0;
    public void bird (){

        image(birdpic[changeBird], birdX,birdY,birdpic[changeBird].width*1.2f, birdpic[changeBird].height*1.2f);
        if(changeBird >1){
            changeBird = 0;
        }
        changeBird++;

        if(birdY < height | Vky < -10){
            birdY += Vky;
            Vky += gravity;
        }

        textSize(12);
        fill(0);
        text("keyCode: "+keycodePress, 40, 30);
        text("Vky: "+Vky, 40, 40);
        text("gravity: "+gravity, 40, 50);
        text("birdY: "+birdY, 40, 60);

    }

    @Override
    public void mousePressed() {
        Vky = -15;
    }

    int keycodePress = 0;
    @Override
    public void keyPressed(KeyEvent event) {
        if(event.getKeyCode() == 10){
            game = 0;
        }
        keycodePress = event.getKeyCode();
        if(event.getKeyCode() == 38){
            Vky = -15;
        }

    }

    public void backGame(){

        image(backGpic,cenarioX,cenarioY,backGpic.width * 1.2f,backGpic.height * 1.2f);
        image(backGpic,cenarioX +backGpic.width,cenarioY,backGpic.width * 1.2f,backGpic.height * 1.2f);
        image(backGpic,cenarioX +backGpic.width*2,cenarioY,backGpic.width * 1.2f,backGpic.height * 1.2f);
        image(backGpic,cenarioX +backGpic.width*3,cenarioY,backGpic.width * 1.2f,backGpic.height * 1.2f);

        image(basepic, chaoCenarioX,height - basepic.height);
        image(basepic, chaoCenarioX + basepic.width,height - basepic.height);
        image(basepic, chaoCenarioX + basepic.width *2,height - basepic.height);
        image(basepic, chaoCenarioX + basepic.width *3,height - basepic.height);


        cenarioX -= 2;
        chaoCenarioX -= 3;


        if(chaoCenarioX <= -basepic.width){
            chaoCenarioX =0;
        }

        if(cenarioX <= -backGpic.width){
            cenarioX = 0;
        }

    }
}