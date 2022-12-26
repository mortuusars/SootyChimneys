package io.github.mortuusars.sootychimneys.integration.jei.drawable;

//import com.mojang.blaze3d.vertex.PoseStack;
//import mezz.jei.api.gui.drawable.IDrawable;
//import mezz.jei.api.gui.drawable.IDrawableAnimated;
//import mezz.jei.api.gui.drawable.IDrawableStatic;
//import mezz.jei.api.helpers.IGuiHelper;
//import net.minecraft.client.Minecraft;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.util.Tuple;
//import net.minecraft.world.phys.Vec2;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;

public class ChimneySmokeAnimatedDrawable /*implements IDrawableAnimated*/ {
//    private Random random;
//    private float speed = 1f;
//    private float intensity = 1f;
//    private List<IDrawable> smokeParticles;
//    private List<Tuple<Vec2, Integer>> currentParticles;
//
//    private int animationSpeedTicks = 1;
//    private long previousGameTime = 0;
//
//    private int particleAddDelay = 0;
//
//    public ChimneySmokeAnimatedDrawable(IGuiHelper helper) {
//        random = new Random();
//        smokeParticles = new ArrayList<>();
//        currentParticles = new ArrayList<>();
//
//        // Add 11 and 10 to start from smaller particles and create smoother animation:
//        IDrawableStatic particle11 = helper.drawableBuilder(new ResourceLocation("minecraft", "textures/particle/big_smoke_11.png"), 0, 0, 16, 16)
//                .setTextureSize(16, 16)
//                .build();
//        smokeParticles.add(particle11);
//
//        IDrawableStatic particle10 = helper.drawableBuilder(new ResourceLocation("minecraft", "textures/particle/big_smoke_10.png"), 0, 0, 16, 16)
//                .setTextureSize(16, 16)
//                .build();
//        smokeParticles.add(particle10);
//
//        for (int i = 0; i < 12; i++) {
//            IDrawableStatic particle = helper.drawableBuilder(new ResourceLocation("minecraft", "textures/particle/big_smoke_" + i + ".png"), 0, 0, 16, 16)
//                    .setTextureSize(16, 16)
//                    .build();
//            smokeParticles.add(particle);
//        }
//    }
//
//    @Override
//    public void draw(PoseStack poseStack, int xOffset, int yOffset) {
//        calculateAnimationTick();
//
//        for (Tuple<Vec2, Integer> particle : currentParticles) {
//            Vec2 pos = particle.getA();
//            int index = particle.getB();
//
//            IDrawable particleFrame = smokeParticles.get(index);
//
//            poseStack.translate(0, 0, 100); // increase z-index so smoke renders above other things
//
//            particleFrame.draw(poseStack, (int) (xOffset + pos.x), (int) (yOffset + pos.y));
//        }
//    }
//
//    private void update() {
//        particleAddDelay--;
//
//        if (particleAddDelay <= 0 && currentParticles.size() < (int) (11 * getIntensity())) {
//            particleAddDelay = random.nextInt(4, (int)(11 - 5 * getIntensity()));
//            currentParticles.add(new Tuple<>(new Vec2((float) random.nextGaussian(), (float) -(random.nextDouble() * 5)), 0));
//        }
//
//        List<Tuple<Vec2, Integer>> particlesToRemove = new ArrayList<>();
//
//        for (int i = 0; i < currentParticles.size(); i++) {
//            Tuple<Vec2, Integer> particle = currentParticles.get(i);
//
//            // Update smoke frame and position
//            particle.setB(particle.getB() + 1);
//            particle.setA(particle.getA().add(new Vec2(random.nextInt(0, 2), -2 * getSpeed())));
//
//            if (particle.getB() >= smokeParticles.size())
//                particlesToRemove.add(particle);
//        }
//
//        particlesToRemove.forEach(particleToRemove -> currentParticles.remove(particleToRemove));
//    }
//
//    private void calculateAnimationTick() {
//        long gameTime = Minecraft.getInstance().level.getGameTime();
//        if (Math.abs(gameTime - previousGameTime) > animationSpeedTicks) {
//            update();
//            previousGameTime = gameTime;
//        }
//    }
//
//    @Override
//    public int getWidth() {
//        return 16;
//    }
//    @Override
//    public int getHeight() {
//        return 16;
//    }
//
//    public int getAnimationSpeedTicks() {
//        return animationSpeedTicks;
//    }
//    public void setAnimationSpeedTicks(int ticks) {
//        animationSpeedTicks = ticks;
//    }
//    public float getSpeed() {
//        return speed;
//    }
//    public void setSpeed(float speed) {
//        this.speed = speed;
//    }
//    public float getIntensity() {
//        return intensity;
//    }
//    public void setIntensity(float strength) {
//        this.intensity = strength;
//    }
}
