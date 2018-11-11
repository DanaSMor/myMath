package gui;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import myMath.Polynom;

public class GraphPoints extends JFrame {

	public GraphPoints(Polynom p)  {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 800);

		Polynom pD = (Polynom) p.derivative();
		
		DataTable data = new DataTable(Double.class, Double.class);
		DataTable Max = new DataTable(Double.class, Double.class);
		DataTable Min = new DataTable(Double.class, Double.class);

		Min.setName("Min-Points");
		Max.setName("Max-Points");

		for (double x = -8.0; x <= 8.0; x+=0.20) {
			double y = p.f(x);
			if(pD.f(x) >= 0 && pD.f(x+0.20) < 0) {
				Max.add(x,y);
			}
			else if(pD.f(x) < 0 && pD.f(x+0.20) > 0) {
				Min.add(x,y);
			}
			else data.add(x,y);
		}

		XYPlot plot = new XYPlot(data,Min,Max);
		getContentPane().add(new InteractivePanel(plot));

		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderers(data, lines);

		lines.setGap(1.0);

		Color colorMin = new Color(1.0f, 0.0f, 0.0f);
		Color colorMax = new Color(0.9f, 0.5f, 0.0f);
		Color colorD = new Color(0.3f, 0.3f,0.7f);

		plot.getLineRenderers(data).get(0).setColor(colorD);
		plot.getPointRenderers(data).get(0).setColor(colorD);

		plot.getPointRenderers(Min).get(0).setColor(colorMin);
		plot.getPointRenderers(Min).get(0).setShape(new Ellipse2D.Double(-4.0, -4.0, 10.0, 10.0));
		
		plot.getPointRenderers(Max).get(0).setColor(colorMax);
		plot.getPointRenderers(Max).get(0).setShape(new Ellipse2D.Double(-4.0, -4.0, 10.0, 10.0));
		
		plot.setLegendVisible(true);

	}

}
