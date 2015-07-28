package DeMeo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChartBar extends JPanel {
private double[] values;

private String[] names;

private String title;

public ChartBar(double[] v, String[] n, String t) {
	
names = n;
values = v;
title = t;
}

public void paintComponent(Graphics g) {
super.paintComponent(g);
if (values == null || values.length == 0)
  return;
double minValue = 0;
double maxValue = 1;
for (int i = 0; i < values.length; i++) {
  if (minValue > values[i])
    minValue = values[i];
  if (maxValue < values[i])
    maxValue = values[i];
}

Dimension d = getSize();
int clientWidth = d.width;
int clientHeight = d.height;
int barWidth = clientWidth / values.length;

Font titleFont = new Font("SansSerif", Font.BOLD, 20);
FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
Font labelFont = new Font("SansSerif", Font.PLAIN, 10);
FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

int titleWidth = titleFontMetrics.stringWidth(title);
int y = titleFontMetrics.getAscent();
int x = (clientWidth - titleWidth) / 2;
g.setFont(titleFont);
g.drawString(title, x, y);

int top = titleFontMetrics.getHeight();
int bottom = labelFontMetrics.getHeight();
if (maxValue == minValue)
  return;
double scale = (clientHeight - top - bottom) / (maxValue - minValue);
y = clientHeight - labelFontMetrics.getDescent();
g.setFont(labelFont);

for (int i = 0; i < values.length; i++) {
  int valueX = i * barWidth + 1;
  int valueY = top;
  int height = (int) (values[i] * scale);
  if (values[i] >= 0)
    valueY += (int) ((maxValue - values[i]) * scale);
  else {
    valueY += (int) (maxValue * scale);
    height = -height;
  }

  g.setColor(Color.blue);
  g.fillRect(valueX, valueY, barWidth - 2, height);
  g.setColor(Color.black);
  g.drawRect(valueX, valueY, barWidth - 2, height);
  int labelWidth = labelFontMetrics.stringWidth(names[i]);
  x = i * barWidth + (barWidth - labelWidth) / 2;
  g.drawString(names[i], x, y);
}
}

public static void main(String[] argv) 
{
	double[] v = new double[11];
	
	for (int i=0;i<11;i++)
	{
	  v[i]=i;
	}
 // ChartBar cb=new ChartBar();	
  // cb.chart(11,v);
}

public void chart(int n, double[] values) 
{
JFrame f = new JFrame();
f.setSize(600, 400);

String[] names = new String[n];

for (int i=0;i<n;i++)
{
  names[i] = String.valueOf(i);
}

f.getContentPane().add(new ChartBar(values, names, "Graphic Solution"));

WindowListener wndCloser = new WindowAdapter() {
  public void windowClosing(WindowEvent e) {
   // System.exit(0);
  }
};
f.addWindowListener(wndCloser);
f.setVisible(true);
}




} 