package com.game.src.main.classes;


import java.awt.Graphics;
import java.awt.Rectangle;

//interface 

public interface EntityA //Entity As don't collide with other entity As, and the same with B, but they collid with eachother
{

	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();

	public double getX();
	public double getY();


}
