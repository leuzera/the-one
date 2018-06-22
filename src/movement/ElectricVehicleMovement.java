/*
 * Copyright 2010 Aalto University, ComNet
 * Released under GPLv3. See LICENSE.txt for details.
 */
package movement;

import core.Coord;
import core.Settings;
import movement.map.DijkstraPathFinder;
import movement.map.MapNode;

import java.util.List;

/**
 * The CarMovement class representing the car movement submodel
 *
 * @author Leonardo Fleury <leuzera>
 */
public class ElectricVehicleMovement extends MapBasedMovement implements
        SwitchableMovement, TransportMovement {

    private Coord from;
    private Coord to;

    private DijkstraPathFinder pathFinder;

    public static final String EV_BASED_MOVEMENT_NS = "EletrivVehicleMovement";
    public static final String EV_AUTONOMY_S = "VehicleConsumption";
    /**
     * Electric Vehicle movement constructor
     *
     * @param settings
     */
    public ElectricVehicleMovement(Settings settings) {
        super(settings);
        pathFinder = new DijkstraPathFinder(getOkMapNodeTypes());
    }

    /**
     * Construct a new ElectricVehicle instance from a prototype
     *
     * @param proto
     */
    public ElectricVehicleMovement(ElectricVehicleMovement proto) {
        super(proto);
        this.pathFinder = proto.pathFinder;
    }

    /**
     * Sets the next route to be taken
     *
     * @param nodeLocation
     * @param nodeDestination
     */
    public void setNextRoute(Coord nodeLocation, Coord nodeDestination) {
        from = nodeLocation.clone();
        to = nodeDestination.clone();
    }

    @Override
    public Path getPath() {
        Path path = new Path(generateSpeed());
        double distance;

        MapNode fromNode = getMap().getNodeByCoord(from);
        MapNode toNode = getMap().getNodeByCoord(to);

        List<MapNode> nodePath = pathFinder.getShortestPath(fromNode, toNode);

        for (MapNode node : nodePath) { // create a Path from the shortest path
            path.addWaypoint(node.getLocation());
        }

        distance = path.getDistance();
        System.out.println("Distancia: " + distance);

        lastMapNode = toNode;

        return path;
    }

    /**
     * @return true
     * @see SwitchableMovement
     */
    public boolean isReady() {
        return true;
    }
}
