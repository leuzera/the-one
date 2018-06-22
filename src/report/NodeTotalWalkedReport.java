package report;

import core.DTNHost;
import core.UpdateListener;

public class NodeTotalWalkedReport extends SnapshotReport
        implements UpdateListener {
    @Override
    protected void writeSnapshot(DTNHost host) {
        write(host.toString() + " " + host.totalWalked());
    }
}
