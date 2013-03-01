import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class Drawing extends Canvas {
	
	private Main m;
	int hsize = 900, wsize = 900;
	Color bgcolor = Color.WHITE;
	public Drawing(){
		setSize(hsize,wsize);
		addMouseMotionListener(new MouseListener(this));
		addMouseListener(new MouseListener(this));
	}
	
	@SuppressWarnings("static-access")
	//Draws the DrawableObjects in the arraylist of objects
	public void paint(Graphics g){
		
		g.setColor(bgcolor);
		g.fillRect(0, 0, wsize, hsize);
		g.setColor(Color.BLACK);
		
		for(DrawableObject obj : m.objects){
			obj.Draw(g);
		}
		
		if (m.temp != null){
			m.temp.Draw(g);
		}

	}

}
