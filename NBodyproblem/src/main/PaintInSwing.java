package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PaintInSwing 
{
    //unsere frisch gebackene Komponente
    private FirstPaintComponent paintComponent = new FirstPaintComponent();
    
    /**
     * Im Konstrukor wird die �bliche Arbeit erledigt um den JFrame zu �ffnen
     * und die Komponenten zu initialisieren
     */
    public PaintInSwing() {
        
        JFrame frame = new JFrame("Selbst Zeichnen mit Swing");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //eine JComponent hat keine Ahnung davon was man auf ihr zeichnen m�chte.
        //Der LayoutManager hat also keine M�glichkeit die passende Gr��e f�r unser
        //Objekt festzustellen und w�rde von (0,0) ausgehen.
        //Daher helfen wir etwas nach und setzen die gew�nschte Gr��e h�ndisch
        paintComponent.setPreferredSize(new Dimension(300,300));
        //unsere Komponente wird mittig im JFrame plaziert 
        frame.add(paintComponent,BorderLayout.CENTER);
        
        //in den unteren Bereich des Frames packen wir einige 
        //Steuerelemente die wir der �bersicht wegen in einer 
        //eigenen Methode erstellen und initialisieren
        frame.add(createControls(),BorderLayout.SOUTH);
        
        //der Frame enth�lt nun alle ben�tigten Komponenten
        //und kann nun seine minimale Gr��e berechnen
        frame.pack();
        //und noch den Frame sichtbar machen und zentrieren
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    /**
     * hier wird ein JPanel erzeugt auf das wir alle
     * Steuerelemente legen 
     * @return ein JPanel das alle Steuerelemente enth�lt
     */
    private Component createControls() {
        //ein einfaches FlowLayout soll f�r unser Beispiel gen�gen
        JPanel panel = new JPanel(new FlowLayout());
        
        //Ein Array mit den 3 Grundfarben wird erstellt und in
        //eine Combobox �bergeben.
        //damit k�nnen wir sp�ter die Farbe der Zeichnung bestimmen
        Object[] colors = {Color.RED,Color.BLUE,Color.GREEN};
        final JComboBox colorBox = new JComboBox(colors);
        panel.add(colorBox);
        
        //Als n�chstes ein Array mit Shapes (Figuren).
        //Der Einfachheit halber setzen wir die Position und Gr��e
        //f�r alle Objekte fest.
        //Die toString Methode wird hier �berschrieben damit die Auswahl
        //in der Combobox besser lesbar ist.
        Object[] shapes = {
                new Ellipse2D.Float(10f,10f,100f,100f) {public String toString() {return "Ellipse";}},
                new RoundRectangle2D.Float(10f,10f,100f,100f,20f,20f) {public String toString() {return "Abgerundetes Rechteck";}}, 
                new Rectangle2D.Float(10f,10f,100f,100f) {public String toString() {return "Rechteck";}}
                };
        //Mit der ComboBox k�nnen wir bestimmen welche Figur gezeichnet werden soll
        final JComboBox shapeBox = new JComboBox(shapes);
        panel.add(shapeBox);
        
        //als letztes noch ein Button mit dem die gew�hlte Figur gezeichnet wird
        JButton paintNow = new JButton("Zeichnen");
        panel.add(paintNow);
        paintNow.addActionListener(new ActionListener() {
        
            public void actionPerformed(ActionEvent e) {
                //wir teilen unserer Zeichenkomponente die gew�hlte Farbe mit
                paintComponent.setColor((Color)colorBox.getSelectedItem());
                //wir teilen unserer Zeichenkomponente mit welche Figur wir haben m�chten
                paintComponent.setShape((Shape)shapeBox.getSelectedItem());
                //jetzt soll gezeichnet werden
                paintComponent.repaint();
            }
        
        });
        
        return panel;
    }
    public static void main(String[] args) 
    {
        new PaintInSwing();
    }
}