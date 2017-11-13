package org.ulco.test;

import junit.framework.TestCase;
import org.ulco.GraphicsObject;
import org.ulco.Point;
import org.ulco.Rectangle;

public class RectangleTest extends TestCase {

    public void testType() throws Exception {
        Rectangle r = new Rectangle(new Point(0, 0), 3, 7);

        assertTrue(r instanceof Rectangle);
        assertTrue(r instanceof GraphicsObject);
    }

    public void testJson() throws Exception {
        Rectangle r = new Rectangle(new Point(0, 0), 3, 7);

        assertEquals(r.toJson(), "{ type: rectangle, center: { type: point, x: 0.0, y: 0.0 }, height: 3.0, width: 7.0 }");
    }

    public void testCopy() throws Exception {
        Rectangle r = new Rectangle(new Point(0, 0), 3, 7);

        assertEquals(r.toJson(), r.copy().toJson());
    }

    public void testIsClosed() throws Exception {
        Rectangle r = new Rectangle(new Point(0, 0), 3, 7);

        assertTrue(r.isClosed(new Point(1, 1), 4));
    }
}