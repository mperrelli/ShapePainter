import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Opener implements ActionListener {
	Frame app; Drawing d;
	public Opener(Frame app, Drawing drawing) {
		this.app = app;
		this.d = drawing;
	}

	public void actionPerformed(ActionEvent arg0) {
		// Pop up a file dialogue 
		FileDialog open = new FileDialog(app);
		open.setVisible(true);
		// Get the received file and directory
		String filename = open.getFile();
		String directory = open.getDirectory();
		Main.datafile = directory + filename;
		Main.objects.clear();
		d.repaint();
		Main.reader();
		d.repaint();

	}

}
