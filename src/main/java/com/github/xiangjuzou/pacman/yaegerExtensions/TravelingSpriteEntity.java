package com.github.xiangjuzou.pacman.yaegerExtensions;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.UpdateExposer;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public abstract class TravelingSpriteEntity extends DynamicSpriteEntity implements DistanceWatcher, UpdateExposer {
    private double accumulatedDistance = 0;
    private Coordinate2D prevLocation;
    private static final double THRESHOLD = 32.0;

    protected TravelingSpriteEntity(final String resource, final Coordinate2D initialLocation) {
        this(resource, initialLocation, null, 1, 1);
    }

    protected TravelingSpriteEntity(final String resource, final Coordinate2D initialLocation, final int rows, final int columns) {
        this(resource, initialLocation, null, rows, columns);
    }

    protected TravelingSpriteEntity(final String resource, final Coordinate2D initialLocation, final Size size) {
        this(resource, initialLocation, size, 1, 1);
    }

    protected TravelingSpriteEntity(final String resource, final Coordinate2D initialLocation, final Size size, final int rows, final int columns) {
        super(resource, initialLocation, size, rows, columns);

        this.prevLocation = initialLocation;
    }

    @Override
    public void explicitUpdate(final long timestamp) {

        // 1. Bereken afstand sinds de vorige tick
        Coordinate2D currentLayout = getAnchorLocation();
        double distanceMoved = currentLayout.distance(prevLocation);
//        if (distanceMoved == 0.0) {
//            return;
//        }

        // 2. Tel op bij de totaal afgelegde afstand
        accumulatedDistance += distanceMoved;
        prevLocation = currentLayout;

        // 3. Check of we de 32 pixels gepasseerd zijn
        if (accumulatedDistance >= getDistanceThreshold() || accumulatedDistance == 0) {
            // 1. Bereken de exacte grid-positie
            double snappedX = Math.round(getAnchorLocation().getX() / 16.0) * 16.0;
            double snappedY = Math.round(getAnchorLocation().getY() / 16.0) * 16.0;

            // 2. Forceer de entiteit naar het midden van de grid-cel
            setAnchorLocation(new Coordinate2D(snappedX, snappedY));

            // 3. Reset de meting vanaf de nieuwe, schone positie
            prevLocation = getAnchorLocation();
            accumulatedDistance = 0; // Reset 0 omdat we nu exact op het grid staan

            // 4. Voer de callback uit
            onDistanceReached();
        }
    }

    @Override
    public double getDistanceThreshold () {
        return THRESHOLD;
    }
}
