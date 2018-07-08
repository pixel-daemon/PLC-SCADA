// juna update kartoy
package scada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import javafx.beans.value.ObservableValue;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.jfree.data.xy.XYDataset;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.AbstractDataset;

import javax.swing.event.ChangeListener;
import java.awt.EventQueue;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.event.ChangeEvent;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.SlidingCategoryDataset;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class Graph extends javax.swing.JFrame{
    private JPanel current, energy, power, voltage, phVoltage;   
    int group, index;
    DataBase db;
    int j = 24;
    int counter = 0;
    TimeSeries []xy;
    XYPlot plot;
    
    public int getFeederNumber(){
        return (group-1)*10 + index;
    }
    
    public TimeSeries[] getXYSeries()
    {        
        TimeSeries crseries = new TimeSeries("Value");
        TimeSeries cyseries = new TimeSeries("Value");
        TimeSeries cbseries = new TimeSeries("Value");
        
        TimeSeries erseries = new TimeSeries("Value");
        TimeSeries eyseries = new TimeSeries("Value");
        TimeSeries ebseries = new TimeSeries("Value");
        
        TimeSeries prseries = new TimeSeries("Value");
        TimeSeries pyseries = new TimeSeries("Value");
        TimeSeries pbseries = new TimeSeries("Value");
        
        TimeSeries vrseries = new TimeSeries("Value");
        TimeSeries vyseries = new TimeSeries("Value");
        TimeSeries vbseries = new TimeSeries("Value");
        
        TimeSeries vrnseries = new TimeSeries("Value");
        TimeSeries vynseries = new TimeSeries("Value");
        TimeSeries vbnseries = new TimeSeries("Value");
        
        java.util.List<String[]> li = db.getData();
        System.out.println(getFeederNumber()+" total entries = "+li.size());
        
        int n = getFeederNumber();
        System.out.println("Feeder number = "+n);
        int i = n-1, d = 17;
        System.out.println(li.size()/d);
        System.out.println("Counter "+counter);
        
        while(i<li.size()){
            String[] s = li.get(i);
            System.out.println(i);
            i+=d;
            String timeString = s[18];
            try{
               // System.out.println("Tims string is "+timeString);
                java.util.Date dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(timeString.replaceAll("^\"|\"$", ""));
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                Timestamp ts = new Timestamp(c.getTimeInMillis());
                //System.out.println("Timestamp object = "+ts);
                //if(s[6].equals("0"))
                System.out.println("value of current is "+s[6]);
                crseries.add(new Millisecond(ts),Double.parseDouble(s[6]));
                cyseries.add(new Millisecond(ts),Double.parseDouble(s[7]));
                cbseries.add(new Millisecond(ts),Double.parseDouble(s[8]));
                
               // System.out.println("ER = "+s[15]);
                erseries.add(new Millisecond(ts),Double.parseDouble(s[15]),true);
                eyseries.add(new Millisecond(ts),Double.parseDouble(s[16]),true);
                ebseries.add(new Millisecond(ts),Double.parseDouble(s[17]),true);
                
                vrseries.add(new Millisecond(ts),Double.parseDouble(s[0]),true);
                vyseries.add(new Millisecond(ts),Double.parseDouble(s[1]),true);
                vbseries.add(new Millisecond(ts),Double.parseDouble(s[2]),true);
                
                vrnseries.add(new Millisecond(ts),Double.parseDouble(s[3]),true);
                vynseries.add(new Millisecond(ts),Double.parseDouble(s[4]),true);
                vbnseries.add(new Millisecond(ts),Double.parseDouble(s[5]),true);
                
                counter++;
            }
            catch(Exception e){
                System.out.println(e);
            }
        }

        xy = new TimeSeries[12];
        xy[0] = crseries;
        xy[1] = cyseries;
        xy[2] = cbseries;
        
        xy[3] = erseries;
        xy[4] = eyseries;
        xy[5] = ebseries;
        
        xy[6] = vrseries;
        xy[7] = vyseries;
        xy[8] = vbseries;
        
        xy[9] =  vrnseries;
        xy[10] = vynseries;
        xy[11] = vbnseries;
        
        return xy;
    }
    
    public XYDataset createDataset(TimeSeries[] xyd){
        //xy = getXYSeries();
        
        TimeSeriesCollection ds = new TimeSeriesCollection();
        ds.addSeries(xyd[0]);
        ds.addSeries(xyd[1]);
        ds.addSeries(xyd[2]);
        return ds;
    }
    public void setRange(String title){
       
    }
    
    public ChartPanel getChart(String title, String x, String y, XYDataset dataset){
        org.jfree.chart.JFreeChart chart = ChartFactory.createTimeSeriesChart(title, x, y, dataset, true, true, false);
        plot = chart.getXYPlot( );
        DateAxis axis = (DateAxis) plot.getDomainAxis();
         ValueAxis nax = (ValueAxis) plot.getRangeAxis();
        
        //axis.setTickUnit(new DateTickUnit(DateTickUnitType.SECOND,5));
        if(title.equals("Current"))
            nax.setRange(5.0,11.0);
        else if(title.equals("Energy"))
            nax.setRange(20.0,50.0);
        else if(title.equals("Voltage"))
            nax.setRange(400.0,500.0);
        else if(title.equals("Phase Voltage"))
            nax.setRange(200.0,300.0);
        //nax.setAutoRange(true);
        //nax.setFixedAutoRange(10.0);
        plot.setBackgroundPaint(Color.BLACK);
        
        axis.setDateFormatOverride(new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss"));
        plot.setDomainPannable(true);
        ChartPanel chartPanel = new ChartPanel( chart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        return chartPanel;
    }
    
   /* public ChartPanel getSLDataset(TimeSeries[] temp){
     
        SlidingCategoryDataset sldataset = new SlidingCategoryDataset((CategoryDataset) createDataset(temp),0,10);
        
        JFreeChart chart = ChartFactory.createBarChart(
                "SlidingCategoryDatasetDemo2",         // chart title
                "Series",               // domain axis label
                "Value",                  // range axis label
                sldataset,                  // data
                PlotOrientation.HORIZONTAL, // orientation
                false,                     // include legend
                true,                     // tooltips?
                false                     // URLs?
            );

            CategoryPlot splot = (CategoryPlot) chart.getPlot();

            org.jfree.chart.axis.CategoryAxis domainAxis = splot.getDomainAxis();
            domainAxis.setMaximumCategoryLabelWidthRatio(0.8f);
            domainAxis.setLowerMargin(0.02);
            domainAxis.setUpperMargin(0.02);

            // set the range axis to display integers only...
            ValueAxis rangeAxis = (ValueAxis) splot.getRangeAxis();
            //rangeAxis.setStandardTickUnits(ValueAxis.createDoubleTickUnits());
            rangeAxis.setRange(0.0, 100.0);

            // disable bar outlines...
            BarRenderer renderer = (BarRenderer) splot.getRenderer();
            renderer.setDrawBarOutline(false);

            // set up gradient paints for series...
            GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue,
                    0.0f, 0.0f, new Color(0, 0, 64));
            renderer.setSeriesPaint(0, gp0);

            ChartPanel chartPanel = new ChartPanel( chart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        return chartPanel;

    }
    */
    
    public Graph(int group, int index, DataBase db){
        super();
        this.group = group;
        this.index = index;
        this.db = db;
        setLayout(new GridLayout(2,2,1,1));
       
        current = new JPanel(new BorderLayout());
        energy = new JPanel(new BorderLayout());
        power = new JPanel(new BorderLayout());
        voltage = new JPanel(new BorderLayout());
        phVoltage = new JPanel(new BorderLayout());
        
        TimeSeries[] xy = getXYSeries();
        
        TimeSeries[] temp = new TimeSeries[3];
        temp[0] = xy[0];
        temp[1] = xy[1];
        temp[2] = xy[2];
        XYDataset ds = createDataset(temp);
        //ChartPanel chartPanel = getChart("Current", "Time", "Value", ds);
        ChartPanel chartPanel = getChart("Current", "Time", "Value", ds);
        current.add(chartPanel,BorderLayout.CENTER);
        
        temp[0] = xy[3];
        temp[1] = xy[4];
        temp[2] = xy[5];
        ds = createDataset(temp);
        chartPanel = getChart("Energy", "Time", "Value", ds);
        energy.add(chartPanel,BorderLayout.CENTER);
        
        temp[0] = xy[6];
        temp[1] = xy[7];
        temp[2] = xy[8];
        ds = createDataset(temp);
        chartPanel = getChart("Voltage", "Time", "Value", ds);
        voltage.add(chartPanel,BorderLayout.CENTER);
        
        temp[0] = xy[9];
        temp[1] = xy[10];
        temp[2] = xy[11];
        ds = createDataset(temp);
        chartPanel = getChart("Phase Voltage", "Time", "Value", ds);
        phVoltage.add(chartPanel,BorderLayout.CENTER);
        /*
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.RED );
        renderer.setSeriesPaint( 1 , Color.GREEN );
        renderer.setSeriesPaint( 2 , Color.YELLOW );
        renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
        renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
        renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
        plot.setRenderer( renderer ); 
        setContentPane( chartPanel ); 
        */     
        
        pack();
        new java.util.Timer().schedule( 
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        try{
                           // System.out.println("hii");
                            java.util.Date date = new java.util.Date();
                            Calendar c = Calendar.getInstance();
                            c.setTime(date);
                            Timestamp ts = new Timestamp(c.getTimeInMillis());
                            RegularTimePeriod m = new Millisecond(ts);    
                           // RegularTimePeriod m = new Millisecond(new Timestamp(System.currentTimeMillis()));    
                            //System.out.println("extra ts object "+m);
                            double[] d = CurrentDB.getCurrentReadings(getFeederNumber());
                           // System.out.println("Current current is "+d[6]);
                            xy[0].addOrUpdate(m, d[6]);
                            xy[1].addOrUpdate(m, d[7]);
                            xy[2].addOrUpdate(m, d[8]);
                            
                            xy[3].addOrUpdate(m, d[15]);
                            xy[4].addOrUpdate(m, d[16]);
                            xy[5].addOrUpdate(m, d[17]);
                            
                            xy[6].addOrUpdate(m, d[0]);
                            xy[7].addOrUpdate(m, d[1]);
                            xy[8].addOrUpdate(m, d[2]);
                            
                            xy[9].addOrUpdate(m, d[3]);
                            xy[10].addOrUpdate(m, d[4]);
                            xy[11].addOrUpdate(m, d[5]);
                            
                            setRange("Current");
                            setRange("Energy");
                            setRange("Voltage");
                            setRange("Phase Voltage");
                            counter++;
                        }
                        catch(Exception e){
                            System.out.println(e);
                        }
                    }
                },0, 
                1000 
        );
        JSlider js = new JSlider(); 
        add(current);
        add(voltage);
        add(energy);
        add(phVoltage);
        setBackground(new Color(255,150,50));
         
        setSize(1000,1000);
        setVisible(true);        
        System.out.println("hii");       
    }
}