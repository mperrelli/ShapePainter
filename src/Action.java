import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Action implements ActionListener {
	
	String obj = "";
	
	public Action(String type) {
		this.obj = type;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(Main.fcolorset == false){
			if(obj.equals("square")){
				Main.current = "square";
				deleteroff();
			}
			else if(obj.equals("rectangle")){
				Main.current = "rectangle";
				deleteroff();
			}
			else if(obj.equals("circle")){
				Main.current = "circle";
				deleteroff();
			}
			else if(obj.equals("line")){
				Main.current = "line";
				deleteroff();
			}
		}
		else{
			if(obj.equals("square")){
				Main.current = "filledsquare";
				deleteroff();
			}
			else if(obj.equals("rectangle")){
				Main.current = "filledrectangle";
				deleteroff();
			}
			else if(obj.equals("circle")){
				Main.current = "filledcircle";
				deleteroff();
			}
			else if(obj.equals("line")){
				Main.current = "line";
				deleteroff();
			}
		}

	}

	private void deleteroff() {
		Main.deleter = false;
		Main.Deleter.setLabel("Deleter : OFF");
	}

}
