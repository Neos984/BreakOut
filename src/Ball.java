
import acm.program.GraphicsProgram;
import acm.graphics.*;

public class Ball extends GOval{
	
	private double vx;
	private double vy;
	
	private int appwidth = 400;
	private int appheight = 600;
	
	public Ball(double x, double y, double width, double height)
	{
		super(x, y, width, height);
	}
	
	public Ball(double x, double y, double width, double height, double newVX, double newVY)
	{
		super(x, y, width, height);
		vx = newVX;
		vy = newVY;
		
	}
	public void move()
	{
		if(getX() + getWidth() >= appwidth || getX() <= 0)
			vx = -vx;
		
		if(getY() + getHeight() >= appheight || getY() <= 0)
			vy = -vy;
		
		
		move(vx,vy);
	}
	
	public double getVX() 
	{
		return vx;
	}
	public double getVY()
	{
	return vy;
	}
	
	public void setVX(double nvx)
	{
		vx = nvx;
	}
	
	public void setVY(double nvy)
	{
		vy = nvy;
	}
}

