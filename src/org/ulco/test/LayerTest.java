package org.ulco.test;

import junit.framework.TestCase;
import org.ulco.*;

public class LayerTest extends TestCase {
    public void testType() throws Exception {
        Document document = new Document();
        int oldID = ID.ID;
        Layer layer = document.createLayer();

        layer.add(new Square(new Point(2, 8), 10));

        assertEquals(layer.getID(), oldID + 1);
        assertEquals(layer.get(0).getID(), oldID + 2);
    }

    public void testJSON() throws Exception {
        Layer l = new Layer();
        Square s = new Square(new Point(0, 0), 5);
        Circle c = new Circle(new Point(5, 5), 4);

        l.add(s);
        l.add(c);
        assertEquals(l.toJson(), "{ type: layer, objects : { { type: square, center: { type: point, x: 0.0, y: 0.0 }, length: 5.0 }, " +
                "{ type: circle, center: { type: point, x: 5.0, y: 5.0 }, radius: 4.0 } } }");
    }
}