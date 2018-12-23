package gui;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.RenderLayer;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import logic.brick.Brick;

class GameFactory {

    public enum Type {
        PLAYER, WALL, BALL, METALBRICK, GLASSBRICK, WOODENBRICK,
    }

    static Entity BrickFactory(double x, double y, Brick brick) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f));
        if (brick.isMetalBrick()) {
            MetalBrickComponent MBrick = new MetalBrickComponent(brick);
            return Entities.builder().
                    at(x, y).
                    type(Type.METALBRICK)
                    .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("metal3.png", 50, 15))
                    .bbox(new HitBox("Box", BoundingShape.box(50, 15)))
                    .with(new CollidableComponent(true), physics, MBrick)
                    .build();
        }
        if (brick.isWoodenBrick()) {
            WoodenBrickComponent WBrick = new WoodenBrickComponent(brick);
            return Entities.builder().
                    at(x, y).
                    type(Type.WOODENBRICK)
                    .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("wooden3.png", 50, 15))
                    .bbox(new HitBox("Box", BoundingShape.box(50, 15)))
                    .with(new CollidableComponent(true), physics, WBrick)
                    .build();
        }
        GlassBrickComponent GBrick = new GlassBrickComponent(brick);
        return Entities.builder().
                at(x, y).
                type(Type.GLASSBRICK)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("glass.png", 50, 15))
                .bbox(new HitBox("Box", BoundingShape.box(50, 15)))
                .with(new CollidableComponent(true), physics, GBrick)
                .build();
    }
    static Entity newPlayer(double x, double y){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        physics.setFixtureDef(new FixtureDef().friction(3).restitution(1f).density(0.1f));
        PlayerControl control = new PlayerControl();
        return Entities.builder().
                at(x,y).
                type(Type.PLAYER)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("bat.png",100,30))
                .bbox(new HitBox("Box",BoundingShape.box(100,30)))
                .with(new CollidableComponent(true),physics,control)
                .build();
    }
    static Entity newBackGround(){
        return Entities.builder().viewFromTexture("bg.png").
                renderLayer(RenderLayer.BACKGROUND).build();

    }
    static Entity newBall(double x, double y){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).friction(0f));
        BallControl control = new BallControl();
        return Entities.builder().
                at(x,y)
                .type(Type.BALL)
                .bbox(new HitBox("Ball", BoundingShape.circle(10)))
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("ball.png",15,15))
                .with(physics,new CollidableComponent(true),control)
                .build();
    }

    static Entity newWalls(){
        Entity walls = Entities.makeScreenBounds(100);
        walls.setType(Type.WALL);
        walls.addComponent(new CollidableComponent(true));
        return walls;
    }

    static Entity gameOver(){
        return Entities.builder().
                at(150   ,150)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("GO.png",304,200))
                .build();
    }
    static Entity win(){
        return Entities.builder().
                at(100   ,100)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("won.png",420,300))
                .build();

    }
    static Entity retry(){
        return Entities.builder().
                at(125   ,400)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("retry.png",332,113))
                .build();
    }




}
