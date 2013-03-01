import java.awt.Canvas;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Exporter implements ActionListener {
	
	Frame app; Drawing d;
	public Exporter(Frame app, Drawing drawing){
		this.app = app;
		this.d = drawing;	
	}

	public void actionPerformed(ActionEvent e) {
		FileDialog save = new FileDialog(app, "Save As", FileDialog.SAVE);
		save.setVisible(true);
		String fileName = save.getDirectory() + save.getFile();
		String ext = fileName.substring(fileName.length() - 4);
		
		if (!ext.equalsIgnoreCase(".png")){
			fileName += ".png";
		}
		
		RenderedImage image = canvasToImage(d);
		
		File file = new File(fileName);
	    try {
			ImageIO.write(image, "png", file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	private BufferedImage canvasToImage(Canvas cnvs) {
        int w = cnvs.getWidth();
        int h = cnvs.getHeight();
        int type = BufferedImage.TYPE_INT_RGB;
        BufferedImage image = new BufferedImage(w,h,type);
        Graphics2D g2 = image.createGraphics();
        cnvs.paint(g2);
        g2.dispose();
        return image;
    }

}
