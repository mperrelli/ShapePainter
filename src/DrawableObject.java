import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class DrawableObject {
	String shape = null;
	int x, y, w, h;
	public static Color currentcolor;
	
	public DrawableObject(String shape, String x, String y, String w, String h){
		this.shape = shape;
		this.x = Integer.parseInt(x);
		this.y = Integer.parseInt(y);
		this.w = Integer.parseInt(w);
		this.h = 0;
		if(h != null){
			this.h = Integer.parseInt(h);
		}
	}
	
	public DrawableObject(String shape, int x, int y, int w, int h){
		this.shape = shape;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = 0;
		if(h > 0){
			this.h = h;
		}
	}
	
	public void Update(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = 0;
		if(h > 0){
			this.h = h;
		}
	}
	
	//Draws the specified object
	public void Draw(Graphics g){
		int x = this.x;
		int y = this.y;
		int w = this.w;
		int h = this.h;
		
		if(this.shape.equals("square")){
			g.drawRect(x-(w/2), y-(w/2), w, w);
		}
		else if(this.shape.equals("filledsquare")){
			g.fillRect(x-(w/2), y-(w/2), w, w);	
		}
		else if(this.shape.equals("rectangle")){
			g.drawRect(x-(w/2), y-(h/2), w, h);	
		}
		else if(this.shape.equals("filledcircle")){
			g.fillOval(x-w, y-w, w*2, w*2);
		}
		else if(this.shape.equals("circle")){
			g.drawOval(x-w, y-w, w*2, w*2);
		}
		else if(this.shape.equals("filledrectangle")){
			g.fillRect(x-(w/2), y-(h/2), w, h);
		}
		else if(this.shape.equals("line")){
			g.drawLine(x, y, w, h);
		}
		else if(this.shape.equals("color")){
			currentcolor = new Color(x,y,w);
			g.setColor(currentcolor);
		}
		else{
			System.out.println(this.shape + " " + this.x + " " + this.y + " " + this.w + " " + this.h);
		}
	}
	
	//Tests for a collision between a coordinate on the screen and a specific shape.
	public boolean collision(Point p){
		boolean v = false;
		if(this.shape.equals("square") || this.shape.equals("filledsquare")){
			if(this.x - (this.w / 2) < p.x && this.x - (this.w / 2) + this.w > p.x &&
					this.y - (this.w / 2) < p.y && this.y - (this.w / 2) + this.w > p.y){
				v = true;
			}
		}
		if(this.shape.equals("rectangle") || this.shape.equals("filledrectangle")){
			if(this.x - (this.w / 2) < p.x && this.x - (this.w / 2) + this.w > p.x &&
					this.y - (this.h / 2) < p.y && this.y - (this.h / 2) + this.h > p.y){
				v = true;
			}
		}
		if(this.shape.equals("line")){
			double linedistance = Math.sqrt((this.w - this.x)*(this.w - this.x) + (this.h - this.y)*(this.h - this.y));
			double piece1 = Math.sqrt((this.x - p.x)*(this.x - p.x) + (this.y - p.y)*(this.y - p.y));
			double piece2 = Math.sqrt((this.w - p.x)*(this.w - p.x) + (this.h - p.y)*(this.h - p.y));
			double linedistancegive = linedistance + (linedistance *.005);
			if(piece1 + piece2 < linedistancegive){
				v = true;
			}
		}
		if(this.shape.equals("circle") || this.shape.equals("filledcircle")){
			if((p.x - this.x)*(p.x - this.x) + (p.y - this.y)*(p.y - this.y) <= this.w*this.w){
				v = true;
			}
		}
		return v;
	}

}
