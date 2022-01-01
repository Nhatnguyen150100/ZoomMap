//import java.awt.*;
//import java.awt.event.*;
//import java.awt.geom.*;
//import java.awt.image.BufferedImage;
//import java.awt.image.renderable.RenderableImage;
//import java.io.*;
//import java.net.*;
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import javax.swing.event.*;
//
//
//public class ZoomTest extends Canvas{
//    public void paint(Graphics g) {
//        g.drawString("Hello", 40, 40);
//        setBackground(Color.WHITE);
//        g.fillRect(130, 30, 100, 80);
//        g.drawOval(30, 130, 50, 60);
//        setForeground(Color.RED);
//        g.fillOval(130, 130, 50, 60);
//        g.drawArc(30, 200, 40, 50, 90, 60);
//        g.fillArc(30, 130, 40, 50, 180, 40);
//
//    }
//
//    public static void main(String[] args)
//    {
//        ImagePanel panel = new ImagePanel();
//        ImageZoom zoom = new ImageZoom(panel);
//        ZoomTest zoomTest = new ZoomTest();
//        JFrame f = new JFrame();
//        //        f.add(zoomTest);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.getContentPane().add(zoom.getUIPanel(), "North");
//        f.getContentPane().add(new JScrollPane(panel));
//
//        f.setSize(800,650);
//        f.setLocation(200,200);
//        f.setVisible(true);
//    }
//}
//
//class ImagePanel extends JPanel
//{
//    BufferedImage image;
//    double scale;
//
//    public ImagePanel()
//    {
//        loadImage();
//        scale = 1.0;
//        setBackground(Color.black);
//    }
//
//    protected void paintComponent(Graphics g)
//    {
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D)g;
//        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//        int w = getWidth();
//        int h = getHeight();
//        int imageWidth = image.getWidth();
//        int imageHeight = image.getHeight();
//        double x = (w - scale * imageWidth)/2;
//        double y = (h - scale * imageHeight)/2;
//        AffineTransform at = AffineTransform.getTranslateInstance(x,y);
//        at.scale(scale, scale);
//        g2.drawRenderedImage(image, at);
////        g.drawString("Hello", 40, 40);
//        setBackground(Color.WHITE);
////        g.fillRect(130, 30, 100, 80);
//        g2.drawOval(30, 130, 50, 60);
//        setForeground(Color.RED);
////        g.fillOval(130, 130, 50, 60);
////        g.drawArc(30, 200, 40, 50, 90, 60);
////        g.fillArc(30, 130, 40, 50, 180, 40);
//    }
//
//    /**
//     * For the scroll pane.
//     */
//    public Dimension getPreferredSize()
//    {
//        int w = (int)(scale * image.getWidth());
//        int h = (int)(scale * image.getHeight());
//        return new Dimension(w, h);
//    }
//
//    public void setScale(double s)
//    {
//        scale = s;
//        revalidate();      // update the scroll pane
//        repaint();
//    }
//
//    private void loadImage()
//    {
//        String fileName = "images/hanoi.png";
//        try
//        {
//            URL url = getClass().getResource(fileName);
//            image = ImageIO.read(url);
//        }
//        catch(MalformedURLException mue)
//        {
//            System.out.println("URL trouble: " + mue.getMessage());
//        }
//        catch(IOException ioe)
//        {
//            System.out.println("read trouble: " + ioe.getMessage());
//        }
//    }
//}
//
//class ImageZoom
//{
//    ImagePanel imagePanel;
//
//    public ImageZoom(ImagePanel ip)
//    {
//        imagePanel = ip;
//    }
//
//    public JPanel getUIPanel()
//    {
//        SpinnerNumberModel model = new SpinnerNumberModel(1.0, 1.0, 2.5, .01);
//        final JSpinner spinner = new JSpinner(model);
//        spinner.setPreferredSize(new Dimension(45, spinner.getPreferredSize().height));
//        spinner.addChangeListener(new ChangeListener()
//        {
//            public void stateChanged(ChangeEvent e)
//            {
//                float scale = ((Double)spinner.getValue()).floatValue();
//                imagePanel.setScale(scale);
//            }
//        });
//        JPanel panel = new JPanel();
//        panel.add(new JLabel("scale"));
//        panel.add(spinner);
//        return panel;
//    }
//}


import java.awt.BorderLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.swing.MapView;

import com.teamdev.jxmaps.*;

public class ZoomTest extends MapView{

    /**
     * The map object
     */
    private static Map map;

    /**
     * Editable circle options
     */
    private CircleOptions settingsCircle;
    /**
     * Editable LineOptions
     */
    private  PolylineOptions settingsLine;


    public CircleOptions getSettingsCircle() {
        return settingsCircle;
    }

    public void setSettingsCircle(CircleOptions settingsCircle) {
        this.settingsCircle = settingsCircle;
    }


    /**
     * Generate a marker on the LatLongPoint
     * @param pos of the wanted marker
     * @return Marker
     */
    public Marker generateMarker(LatLng pos)
    {
        Marker marker=null;


        marker = new Marker(map);
        marker.setPosition(new LatLng(21.002572, 105.836068));
        marker.setTitle("30 người");
//        map.setCenter(pos);

        Circle circle = new Circle(map);
        circle.setCenter(new LatLng(21.002572, 105.836068));
        circle.setRadius(30);


        CircleOptions co = new CircleOptions();
        co.setFillColor("#FF0000");
        co.setFillOpacity(0.4);

        circle.setOptions(co);

        System.out.println("Graficado marker");
        return marker;
    }

    /**
     * Generate a simple nibe between two LatLong points
     * @param start Start point of the line
     * @param end End point of the line
     * @param markers Do you wanna put a marker on the LatLong points
     */
    public void generateSimplePath(LatLng start,LatLng end,Boolean markers)
    {
        LatLng[] path = {start,end};
        Polyline polyline = new Polyline(map);
        polyline.setPath(path);
        if(markers)
        {
            generateMarker(start);
            generateMarker(end);
        }
    }

    public Map darMap()
    {
        return map;
    }

    /**
     * Generate a circle area on the map
     * @param center LatLong of the center of the map
     * @param radius on meters
     */
    public void generateArea(LatLng center,Double radius)
    {
        Circle circle = new Circle(map);
        circle.setCenter(center);
        circle.setRadius(radius);
        circle.setVisible(true);
        circle.setOptions(settingsCircle);
    }

    /**
     * Generate a line on the Map on the selected breakpoints
     * @param markers  do you wanna put a marker on each vertex
     * @param path Group of points of the Line
     */
    public void GenerateLine(boolean markers,LatLng... path)
    {
        if(markers)
        {
            for(LatLng p:path)
            {
                generateMarker(p);
            }
        }
        Polyline polyline = new Polyline(map);
        polyline.setPath(path);
    }

    /**
     * Create a new Map panel whit the param Name
     * @param pString Name for the map
     */
    public ZoomTest(String pString) {

        JFrame frame = new JFrame("Chicago-Data: "+pString);


        settingsCircle=new CircleOptions();
        settingsCircle.setFillColor("#FF0000");
        settingsCircle.setRadius(2000000);
        settingsCircle.setFillOpacity(0.35);

        settingsLine=new PolylineOptions();
        settingsLine.setGeodesic(true);
        settingsLine.setStrokeColor("#FF0000");
        settingsLine.setStrokeOpacity(1.0);
        settingsLine.setStrokeWeight(2.0);

        // Setting of a ready handler to MapView object. onMapReady will be called when map initialization is done and
        // the map object is ready to use. Current implementation of onMapReady customizes the map object.
        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                // Check if the map is loaded correctly
                if (status == MapStatus.MAP_STATUS_OK) {
                    // Getting the associated map object
                    map = getMap();
                    MapOptions mapOptions = new MapOptions();
                    MapTypeControlOptions controlOptions = new MapTypeControlOptions();
                    controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
                    mapOptions.setMapTypeControlOptions(controlOptions);

                    map.setOptions(mapOptions);
                    map.setCenter(new LatLng(21.004956, 105.845939));
                    map.setZoom(15);
                }
            }
        });
        System.out.print("Espere mientras se genera el mapa ");
        try {
            for(int i=0;i<10;i++)
            {
                TimeUnit.SECONDS.sleep(1);
                System.out.print(".");
            }
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println("|");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(this, BorderLayout.CENTER);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        final ZoomTest example = new ZoomTest("test");
        example.generateMarker(map.getCenter());
    }

}
