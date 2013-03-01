import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


public class MouseListener implements java.awt.event.MouseListener, MouseMotionListener {
	
	private Drawing d;
	
	public MouseListener(Drawing drawing){
		d = drawing;
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		int x = me.getX();
		int y = me.getY();
		Main.deleteobjat(new Point(x,y));

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent grab) {
		if (!Main.current.equals("") && !Main.deleter){
			int px = grab.getX();
			int py = grab.getY();
			Main.p1 = new Point(px, py);
			if(Main.current.equals("line")){
				Main.temp = new DrawableObject(Main.current, px, py, px, py);
			} 
			else{
				Main.temp = new DrawableObject(Main.current, px, py, 0, 0);
			}
			d.repaint();
		}
		

	}

	@Override
	public void mouseReleased(MouseEvent release) {
		if (!Main.current.equals("") && !Main.deleter){
			int rx = release.getX();
			int ry = release.getY();
			Main.p3 = new Point(rx, ry);
			Main.addObject();
			d.repaint();
		}

	}
	
	public void mouseDragged(MouseEvent drag){
		if (!Main.current.equals("") && !Main.deleter){
			int dx = drag.getX();
			int dy = drag.getY();
			Main.p2 = new Point(dx, dy);
			Main.updateObject();
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
