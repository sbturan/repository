package Tools;

public class RgbColor {
	
	int RGB;
	int red;
	int green;
	int blue;
	
	public RgbColor(int RGB)
	{
		this.RGB=RGB;
		this.red = (RGB >> 16) & 0xff;
	    this.green = (RGB >> 8) & 0xff;
	    this.blue = (RGB) & 0xff;
		
	}
	
	public int getRed() {
		return red;
	}
	public int getGreen() {
		return green;
	}
	public int getBlue() {
		return blue;
	}
	public int getRGB() {
		return RGB;
	}

}
