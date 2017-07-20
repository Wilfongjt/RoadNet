package com.codeflyz.roadnet;


import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author jameswilfong
 */
/**
 * MBR is a list of two coordinates
 */
public class MBR extends Feature {

    public MBR() {
        super.add(new Coordinate(1000000, 1000000)); // lower left, minX minY
        super.add(new Coordinate(-1000000, -1000000)); // upper right, maxX maxY
    }

    public Coordinate getMIN() {
        return get(0);
    }

    public Coordinate getMAX() {
        return get(1);
    }

    public boolean contains(Coordinate coord) {
        boolean rc = false;
        Coordinate min = get(0);
        Coordinate max = get(1);
        if (coord.x >= min.x && coord.x <= max.x) {
            if (coord.y >= min.y && coord.y <= max.y) {
                rc = true;
            }
        }

        return rc;
    }

    public boolean contains(Feature feature) {
        Coordinate centroid = feature.getCentroid();
        return MBR.this.contains(centroid);
    }

    public double getWidth() {
        return get(0).x - get(1).x;
    }

    public double getHeight() {
        return get(0).y - get(1).y;
    }

    public boolean union(MBR mbr) {
        add(mbr.get(0));// min 
        add(mbr.get(1));// max

        return true;
    }

    @Override
    public boolean add(Coordinate c) {

        Coordinate min = get(0);
        Coordinate max = get(1);
        if (min.x > c.x) {
            min.x = c.x;
        }
        if (max.x < c.x) {
            max.x = c.x;
        }

        if (min.y > c.y) {
            min.y = c.y;
        }
        if (max.y < c.y) {
            max.y = c.y;
        }
        return true;
    }



    public String toString() {
        // {"mbr":[[min],[max]]}
        Coordinate min = get(0);
        Coordinate max = get(1);
        String rc = "\"mbr\":[[min],[max]]"; // no curly brackets, most likely to be included into another object and not a standalone
        rc = rc.replace("[min]", min.toString())
                .replace("[max]", max.toString());
        return rc;
    }

}
