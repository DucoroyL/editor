package org.ulco.test;

import junit.framework.TestCase;
import org.ulco.Circle;
import org.ulco.GraphicsObject;
import org.ulco.Point;

public class CircleTest extends TestCase {

    public void testType() throws Exception {
        Circle s = new Circle(new Point(0, 0),10);

        assertTrue(s instanceof Circle);
        assertTrue(s instanceof GraphicsObject);
    }

    public void testJson() throws Exception {
        Circle s = new Circle(new Point(0,0), 10);

        assertEquals(s.toJson(), "{ type: circle, center: { type: point, x: 0.0, y: 0.0 }, radius: 10.0 }");
    }

    public void testCopy() throws Exception {
        Circle s = new Circle(new Point(0,0), 10);

        assertEquals(s.toJson(), s.copy().toJson());
    }
}