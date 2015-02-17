package fr.xebia.di.domain;

import static java.lang.Math.*;

public class GeoDistanceEngine {
    private static final double EARTH_RADIUS_IN_METERS = 6_378_137;
    private static final double DEGREE_TO_RADIAN = PI / 180D;
    
    public double evaluate(Coordinate c1, Coordinate c2) {
        double dlong = (c2.longitude - c1.longitude) * DEGREE_TO_RADIAN;
        double dlat = (c2.latitude - c1.latitude) * DEGREE_TO_RADIAN;
        double a = pow(sin(dlat / 2D), 2D) +
                cos(c1.latitude * DEGREE_TO_RADIAN) * cos(c2.latitude * DEGREE_TO_RADIAN) * pow(sin(dlong / 2D), 2D);
        double c = 2D * atan2(sqrt(a), sqrt(1D - a));

        return EARTH_RADIUS_IN_METERS * c;
    }
}
