import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saver implements ActionListener {
	
	Frame app; Drawing d;
	public Saver (Frame app, Drawing drawing){
		this.app = app;
		this.d = drawing;
		
	}
	public void actionPerformed(ActionEvent arg0) {
		FileDialog save = new FileDialog(app, "Save As", FileDialog.SAVE);
		save.setVisible(true);
		String fileName = save.getDirectory() + save.getFile();
		String ext = fileName.substring(fileName.length() - 4);

		//If an extension is not specified the .txt extension is placed on
		if (!ext.equalsIgnoreCase(".txt"))
			fileName += ".txt";

		//Write the file
		File file = new File(fileName);
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			for (DrawableObject obj : Main.objects) {
				String lineString = obj.shape + " " + obj.x + " " + obj.y + " " + obj.w;
				if (obj.h != 0){
					lineString += " " + obj.h;
				}
				output.write(lineString);
				output.newLine();
			}
			output.close();
		} catch (IOException ioe) {
		}

	}

}
