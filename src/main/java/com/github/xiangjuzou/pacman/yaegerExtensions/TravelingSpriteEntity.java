package com.github.xiangjuzou.pacman.yaegerExtensions;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.UpdateExposer;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.xiangjuzou.pacman.entities.maps.Locatie2D;

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

        // Bereken afstand sinds de vorige tick
        Coordinate2D currentLayout = getAnchorLocation();
        double distanceMoved = currentLayout.distance(prevLocation);
         if (distanceMoved == 0.0) {
           onDistanceReached();
           return;
         }

        accumulatedDistance += distanceMoved;
        prevLocation = currentLayout;

        // Check of we de 32 pixels gepasseerd zijn
        if (accumulatedDistance >= getDistanceThreshold()) {
            // Bereken de exacte grid-positie
            double snappedX = Math.round(getAnchorLocation().getX() / 16.0) * 16.0;
            double snappedY = Math.round(getAnchorLocation().getY() / 16.0) * 16.0;

            // Forceer de entiteit naar het midden van de grid-cel
            setAnchorLocation(new Coordinate2D(snappedX, snappedY));

            // Reset de meting vanaf de nieuwe, schone positie
            prevLocation = getAnchorLocation();
            accumulatedDistance = 0; // Reset 0 omdat we nu exact op het grid staan

            // Voer de callback uit
            onDistanceReached();
        }
    }

    @Override
    public double getDistanceThreshold () {
        return THRESHOLD;
    }

    @Override
    public void onDistanceReached() {
        var TileCoordinate = getAnchorLocation().add(new Coordinate2D(16, 16));
        var locatie = new Locatie2D(TileCoordinate);

        var characterOffset = new Coordinate2D(-16,-16);

        // sta ik aan het eind van de rechter tunnel, en ga ik naar rechts, ga dan naar het eind linker tunnel.
        if (locatie.getX() == 27 && locatie.getY() == 14 && getDirection() == Direction.RIGHT.getValue()) {
            var spawnLeft = new Locatie2D(0,14);
            setAnchorLocation(spawnLeft.GetCoordinate().add(characterOffset));
        }

        // sta ik aan het eind van de linker tunnel, en ga ik naar links, ga dan naar het eind van de rechter tunnel.
        if (locatie.getX() == 0 && locatie.getY() == 14 && getDirection() == Direction.LEFT.getValue()) {
            var spawnLeft = new Locatie2D(27,14);
            setAnchorLocation(spawnLeft.GetCoordinate().add(characterOffset));
        }

    }
}
