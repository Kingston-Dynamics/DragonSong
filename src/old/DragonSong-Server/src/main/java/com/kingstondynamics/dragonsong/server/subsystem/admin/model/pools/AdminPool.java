package com.kingstondynamics.dragonsong.server.subsystem.admin.model.pools;

import com.kingstondynamics.dragonsong.server.subsystem.admin.model.connection.AdminConnection;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author peter
 */

@Component
public class AdminPool {
    
    public void registerConnection(AdminConnection connection) {
        throw new UnsupportedOperationException("Unimplemented");
    }
    
    public void deregisterConnection(String connectionID) {
        throw new UnsupportedOperationException("Unimplemented");
    }
    
    public ArrayList<AdminConnection> getConnections() {
        throw new UnsupportedOperationException("Unimplemented");
    }
    
    public AdminConnection getConnection(String connectionID) {
        throw new UnsupportedOperationException("Unimplemented");
    }
}
