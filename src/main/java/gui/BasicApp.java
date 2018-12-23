package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.ui.InGamePanel;
import facade.HomeworkTwoFacade;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.brick.Brick;
import logic.level.Level;

import java.util.Collections;
import java.util.List;

import static gui.GameFactory.*;


public class BasicApp extends GameApplication  {
    private HomeworkTwoFacade hw2;
    private int passed=0;
    private int added=0;
    private boolean started=false;

    private InGamePanel panel;
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(600);
        gameSettings.setHeight(600);
        gameSettings.setTitle("CC302-Breakout");
        gameSettings.setVersion("1.0");
        gameSettings.setAppIcon("icon.png");
    }


    @Override
    protected void initGame(){

        panel = new InGamePanel();
        getGameScene().addUINode(panel);
        hw2=new HomeworkTwoFacade();
        AppUpdater appUpdater = new AppUpdater(this);
        hw2.assingGui(appUpdater);
        Entity player = newPlayer(getWidth()/2-50/2,500);
        Entity bg= newBackGround();
        Entity ball= GameFactory.newBall(player.getX()+40,player.getY()-15);
        Entity walls= newWalls();
        getGameWorld().addEntities(player,bg,ball,walls);

    }

    private void mousEventHandler() {
        getGameWorld().getEntitiesByType(Type.GLASSBRICK).forEach(entity -> entity.getView().setOnMouseClicked(event -> entity.getComponent(GlassBrickComponent.class).OnHit()));
        getGameWorld().getEntitiesByType(Type.WOODENBRICK).forEach(entity -> entity.getView().setOnMouseClicked(event -> entity.getComponent(WoodenBrickComponent.class).OnHit()));
        getGameWorld().getEntitiesByType(Type.METALBRICK).forEach(entity -> entity.getView().setOnMouseClicked(event -> entity.getComponent(MetalBrickComponent.class).OnHit()));
    }



    @Override
    protected void initInput(){
        Input input = getInput();

        getInput().addAction(new UserAction("Open/Close Panel") {
            @Override
        protected void onActionBegin() {
                getGameScene().removeUINode(panel);
                textInfo();
                if (panel.isOpen())
                    panel.close();
                else
                    panel.open();
                        }
                }, KeyCode.TAB);

        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() throws IndexOutOfBoundsException{
                List<Entity> balls = null;
                try {
                    balls = getGameWorld().getEntitiesByType(Type.BALL);
                } catch (NullPointerException npe) {
                }
                try{
                Entity ball = balls.get(0);
                    if (ball.getComponent(PhysicsComponent.class).getVelocityX()==0 && ball.getComponent(PhysicsComponent.class).getVelocityY()==0){
                            getGameWorld().getEntitiesByType(Type.BALL).
                            forEach(e->e.getComponent(BallControl.class).right());
                            getGameWorld().getEntitiesByType(Type.PLAYER).
                                forEach(e-> e.getComponent(PlayerControl.class).right());
                    }
                getGameWorld().getEntitiesByType(Type.PLAYER).
                        forEach(e-> e.getComponent(PlayerControl.class).right());

            }
            catch (IndexOutOfBoundsException io){

            }
            }
        }, KeyCode.D);
        input.addAction(new UserAction("Testing") {
            @Override
            protected void onAction() {
                mousEventHandler();
            }
        },KeyCode.T);
        input.addAction(new UserAction("NewLevel") {
            @Override
            protected void onActionBegin() {
                if (!started) {
                    started=true;
                    newLevel();
                    brickDisplayed();}
                    else {
                addPlayinglevel();}

            }
        },KeyCode.N);
        input.addAction(new UserAction("Retry") {
            @Override
            protected void onActionBegin() {
                if(hw2.isGameOver()||!hw2.hasCurrentLevel()){
                passed=0;
                added=0;
                started=false;
                startNewGame();}

            }
        },KeyCode.R);


        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() throws IndexOutOfBoundsException{
                List<Entity> balls = null;
                try {
                    balls = getGameWorld().getEntitiesByType(Type.BALL);
                } catch (NullPointerException npe) {
                }
                try{
                Entity ball = balls.get(0);
                if (ball.getComponent(PhysicsComponent.class).getVelocityX() == 0 && ball.getComponent(PhysicsComponent.class).getVelocityY() == 0) {
                    getGameWorld().getEntitiesByType(Type.BALL).
                            forEach(e -> e.getComponent(BallControl.class).left());
                    getGameWorld().getEntitiesByType(Type.PLAYER).
                            forEach(e -> e.getComponent(PlayerControl.class).left());
                }
                getGameWorld().getEntitiesByType(Type.PLAYER).
                        forEach(e -> e.getComponent(PlayerControl.class).left());}
                catch (IndexOutOfBoundsException e){
                }
            }
        }, KeyCode.A);
        input.addAction(new UserAction("Launch") {
            @Override
            protected void onAction() throws IndexOutOfBoundsException {
                List<Entity> balls = null;
                try {
                    balls = getGameWorld().getEntitiesByType(Type.BALL);
                } catch (NullPointerException npe) {
                }
                try{
                Entity ball = balls.get(0);

                if (ball.getComponent(PhysicsComponent.class).getVelocityX() == 0 && ball.getComponent(PhysicsComponent.class).getVelocityY() == 0&& hw2.hasCurrentLevel()){
                getGameWorld().getEntitiesByType(Type.BALL).
                        forEach(e->e.getComponent(PhysicsComponent.class).setLinearVelocity(5*60,-5*60));}}
                catch (IndexOutOfBoundsException e){}
            }
        }, KeyCode.SPACE);
    }




    @Override
    public void initPhysics(){
        getPhysicsWorld().setGravity(0,0);
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(Type.BALL, Type.GLASSBRICK) {
            @Override
            protected void onHitBoxTrigger(Entity ball, Entity GlassBrick, HitBox boxBall, HitBox boxBrick) {
                getAudioPlayer().playSound("glass.wav");
                GlassBrick.getComponent(GlassBrickComponent.class).OnHit();
                if (GlassBrick.getComponent(GlassBrickComponent.class).isDestroyed()){
                    Entity Sparks = new Entity();
                    Sparks.setPosition(GlassBrick.getX()+25,GlassBrick.getY());
                    ParticleEmitter emitter = ParticleEmitters.newSparkEmitter();
                    emitter.setBlendMode(BlendMode.SRC_OVER);
                    emitter.setExpireFunction(i -> Duration.seconds(FXGLMath.random(2, 2)));
                    emitter.setMaxEmissions(1);
                    ParticleComponent component = new ParticleComponent(emitter);
                    Sparks.addComponent(component);
                    getGameWorld().addEntity(Sparks);
                    getAudioPlayer().playSound("destroyed.wav");


                }}

            @Override
            protected void onCollisionEnd(Entity a, Entity b) {

            }
        });


        getPhysicsWorld().addCollisionHandler(new CollisionHandler(Type.BALL, Type.WOODENBRICK) {
            @Override
            protected void onHitBoxTrigger(Entity ball, Entity WoodenBrick, HitBox boxBall, HitBox boxBrick) {
                getAudioPlayer().playSound("wood.wav");
                WoodenBrick.getComponent(WoodenBrickComponent.class).OnHit();
                if (WoodenBrick.getComponent(WoodenBrickComponent.class).isDestroyed()){
                    Entity Sparks = new Entity();
                    Sparks.setPosition(WoodenBrick.getX()+25,WoodenBrick.getY());
                    ParticleEmitter emitter = ParticleEmitters.newSparkEmitter();
                    emitter.setBlendMode(BlendMode.SRC_OVER);
                    emitter.setExpireFunction(i -> Duration.seconds(FXGLMath.random(2, 2)));
                    emitter.setMaxEmissions(1);
                    ParticleComponent component = new ParticleComponent(emitter);
                    Sparks.addComponent(component);
                    getGameWorld().addEntity(Sparks);
                    getAudioPlayer().playSound("destroyed.wav");
                }
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(Type.BALL, Type.METALBRICK) {
            @Override
            protected void onHitBoxTrigger(Entity ball, Entity MetalBrick, HitBox boxBall, HitBox boxBrick) {
                MetalBrick.getComponent(MetalBrickComponent.class).OnHit();
                getAudioPlayer().playSound("metal.wav");
                if (MetalBrick.getComponent(MetalBrickComponent.class).isDestroyed()){
                    Entity Sparks = new Entity();
                    Sparks.setPosition(MetalBrick.getPosition());
                    ParticleEmitter emitter = ParticleEmitters.newSparkEmitter();
                    emitter.setBlendMode(BlendMode.SRC_OVER);
                    emitter.setExpireFunction(i -> Duration.seconds(FXGLMath.random(2, 2)));
                    emitter.setMaxEmissions(1);
                    ParticleComponent component = new ParticleComponent(emitter);
                    Sparks.addComponent(component);
                    getGameWorld().addEntity(Sparks);
                    getAudioPlayer().playSound("destroyed.wav");
                }

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(Type.PLAYER, Type.WALL) {
            @Override
            protected void onHitBoxTrigger(Entity Player, Entity Wall, HitBox boxPlayer, HitBox boxWall) {

            }
        });


        getPhysicsWorld().addCollisionHandler(new CollisionHandler(Type.BALL, Type.WALL) {
            @Override
            protected void onHitBoxTrigger(Entity Ball, Entity Wall, HitBox boxBall, HitBox boxWall) {
                if (boxWall.getName()=="BOT"){
                    if(hw2.isGameOver()){
                        Entity GameOver = gameOver();
                        Entity retry = retry();
                        getGameWorld().addEntities(GameOver,retry);
                    }
                    Ball.removeFromWorld();
                    hw2.dropBall();
                    if(hw2.isGameOver()){
                        Entity GameOver = gameOver();
                        Entity retry = retry();
                        getGameWorld().addEntities(GameOver,retry);
                    }
                    else{
                    newBall();}



                }
            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(Type.BALL, Type.WALL) {
            @Override
            protected void onHitBoxTrigger(Entity Ball, Entity Wall, HitBox boxBall, HitBox boxWall) {
                if (boxWall.getName()=="LEFT"||boxWall.getName()=="RIGHT"){
                    if(Ball.getComponent(PhysicsComponent.class).getVelocityY()==0||Ball.getComponent(PhysicsComponent.class).getVelocityX()==0){
                        Ball.getComponent(PhysicsComponent.class).setLinearVelocity(5*60,-5*60);
                    }
                }


            }
        });



    }

    private void newBall() {
        List<Entity> players = getGameWorld().getEntitiesByType(Type.PLAYER);
        Entity player = players.get(0);
        Entity ball= GameFactory.newBall(player.getX()+40,player.getY()-15);
        getGameWorld().addEntities(ball);

    }

    public static void main(String...args){
        launch(args);
    }


    private void newLevel(){
        added++;
        Level level1 = hw2.newLevelWithBricksFull("Level "+added, 20, 0.5, 0.2, (int) System.currentTimeMillis());
        hw2.setCurrentLevel(level1);

    }
    private void addPlayinglevel() {
        added++;
        Level level2 = hw2.newLevelWithBricksFull("Level "+added, 20, 0.5, 0.2, (int) System.currentTimeMillis());
        hw2.addPlayingLevel(level2);
    }

    private void textInfo(){
        getGameScene().removeUINode(panel);
        Text level = getUIFactory().newText("Current "+hw2.getLevelName());
        Text score = getUIFactory().newText("Points "+hw2.getCurrentPoints());
        int points=hw2.getLevelPoints();
        Text goal = getUIFactory().newText("Goal "+points);
        Text balls = getUIFactory().newText("Balls Left "+hw2.getBallsLeft());
        Text Passed =getUIFactory().newText("Passed "+passed);
        level.setTranslateX(50);
        level.setTranslateY(50);
        score.setTranslateX(50);
        score.setTranslateY(70);
        goal.setTranslateX(50);
        goal.setTranslateY(90);
        balls.setTranslateX(50);
        balls.setTranslateY(110);
        Passed.setTranslateX(50);
        Passed.setTranslateY(130);
        panel.getChildren().add(level);
        panel.getChildren().add(score);
        panel.getChildren().add(goal);
        panel.getChildren().add(balls);
        panel.getChildren().add(Passed);
        getGameScene().addUINode(panel);
    }

    public  void changeLevel(){
        passed++;
        if(!hw2.winner()){
            cleanScreen();
            brickDisplayed();}
        if(!hw2.hasNextLevel()&&!hw2.hasCurrentLevel()){
            Entity win = win();
            Entity retry = retry();
            getGameWorld().addEntities(win,retry);
        }
    }
    private void cleanScreen() {
        getGameWorld().getEntitiesByType(Type.METALBRICK, Type.GLASSBRICK, Type.WOODENBRICK).forEach(entity -> entity.removeFromWorld());
    }

    private void brickDisplayed(){

        List <Brick> BrickList = hw2.getBricks();
        Collections.shuffle(BrickList);
        int n=BrickList.size();
        double r=n/10;
        Math.ceil(r);
        int displayed=0;

        for (int j=0;j<r;j++){
            for (int i=0;i<=9;i++){

                displayed++;
                Brick brick= BrickList.get(i+j*10);
                getGameWorld().addEntities(BrickFactory(60+i*50,30+j*30,brick));
            }
        }
        for (int k=displayed;k<n;k++){
            int i=n-displayed;
            displayed++;
            Brick brick= BrickList.get(k);
            getGameWorld().addEntities(BrickFactory(60+i*50,30+r*30,brick));

        }

    }
}
