
import acm.program.GraphicsProgram;
import acm.graphics.*;

public class Ball2 extends GOval{
	
	public double vx2;
	public double vy2;
	
	private int appwidth = 400;
	private int appheight = 600;
	
	public Ball2(double x2, double y2, double width2, double height2)
	{
		super(x2, y2, width2, height2);
	}
	
	public Ball2(double x2, double y2, double width2, double height2, double newVX2, double newVY2)
	{
		super(x2, y2, width2, height2);
		vx2 = newVX2;
		vy2 = newVY2;
		
	}
	public void move2()
	{
		if(getX() + getWidth() >= appwidth || getX() <= 0)
			vx2 = -vx2;
		
		if(getY() + getHeight() >= appheight || getY() <= 0)
			vy2 = -vy2;
		
		
		move(vx2,vy2);
	}
	
	public double getVX2() 
	{
		return vx2;
	}
	public double getVY2()
	{
	return vy2;
	}
	
	public void setVX2(double nvx2)
	{
		vx2 = nvx2;
	}
	
	public void setVY2(double nvy2)
	{
		vy2 = nvy2;
	}
}
