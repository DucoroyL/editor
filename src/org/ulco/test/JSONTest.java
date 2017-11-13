package org.ulco.test;

import junit.framework.TestCase;
import org.ulco.*;

public class JSONTest extends TestCase {

    public void testParseCircle() throws Exception {
        String json = "{ type:    circle, center: { type: point, x:2, y:7 }, radius: 1.8 }";

        assertTrue(JSON.parse(json) instanceof Circle);
        assertEquals(JSON.parse(json).toString(), "circle[point[2.0,7.0],1.8]");
    }

    public void testParseRectangle() throws Exception {
        String json = "{ type:  rectangle, center: { type: point, x:-1, y:-9 }, height: 10, width: 7 }";

        assertTrue(JSON.parse(json) instanceof Rectangle);
        assertEquals(JSON.parse(json).toString(), "rectangle[point[-1.0,-9.0],10.0,7.0]");
    }

    public void testParseSquare() throws Exception {
        String json = "{ type:    square, center: { type: point, x:10, y:20 }, length: 12 }";

        assertTrue(JSON.parse(json) instanceof Square);
        assertEquals(JSON.parse(json).toString(), "square[point[10.0,20.0],12.0]");
    }

    public void testParseGroup() throws Exception {
        String json = "{ type: group, objects : { { type: square, center: { type: point, x: 0.0, y: 0.0 }, length: 5.0 }, " +
                "{ type: circle, center: { type: point, x: 5.0, y: 5.0 }, radius: 4.0 } }, groups : {  } }";

        assertTrue(JSON.parseGroup(json) instanceof Group);
        assertEquals(JSON.parseGroup(json).toString(), "group[[square[point[0.0,0.0],5.0], circle[point[5.0,5.0],4.0]],[]]");
    }

    public void testParseGroup2() throws Exception {
        String json = "{ type: group, objects : { { type: rectangle, center: { type: point, x: -6.0, y: 10.0 }, " +
                "height: 5.2, width: 9.0 } }, groups : { { type: group, objects : { { type: square, center: { type: point, x: 0.0, " +
                "y: 0.0 }, length: 5.0 }, { type: circle, center: { type: point, x: 5.0, y: 5.0 }, radius: 4.0 } }, " +
                "groups : {  } } } }";

        assertTrue(JSON.parseGroup(json) instanceof Group);
        assertEquals(JSON.parseGroup(json).toString(), "group[[rectangle[point[-6.0,10.0],5.2,9.0]],[group[[square[point[0.0,0.0],5.0], circle[point[5.0,5.0],4.0]],[]]]]");
    }

    public void testParseLayer() throws Exception {
        String json = "{ type: layer, objects : { { type: square, center: { type: point, x: 0.0, y: 0.0 }, length: 5.0 }, " +
                "{ type: circle, center: { type: point, x: 5.0, y: 5.0 }, radius: 4.0 } } }";

        assertTrue(JSON.parseLayer(json) instanceof Layer);
    }

    public void testParseDocument() throws Exception {
        String json = "{ type: document, layers: { { type: layer, objects : { { type: square, center: " +
                "{ type: point, x: 0.0, y: 0.0 }, length: 5.0 }, { type: circle, center: { type: point, x: 5.0, y: 5.0 }" +
                ", radius: 4.0 } } }, { type: layer, objects : { { type: rectangle, center: { type: point, x: -5.0, y: 1.0 }" +
                ", height: 4.0, width: 2.0 }, { type: circle, center: { type: point, x: -4.0, y: 8.0 }, radius: 1.0 } } } } ";

        assertTrue(JSON.parseDocument(json) instanceof Document);
    }
}