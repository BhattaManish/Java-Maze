

package de.uni_hannover.hci.maze.waypointlist;

public class WaypointList
{
    private int x;
    private int y;
    private WaypointList next;
    
    public WaypointList(final int n, final int n2) {
        this(n, n2, null);
    }
    
    public WaypointList(final int x, final int y, final WaypointList next) {
        this.x = x;
        this.y = y;
        this.next = next;
    }
    
    public void setNext(final WaypointList next) {
        this.next = next;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public WaypointList getNext() {
        return this.next;
    }
}
