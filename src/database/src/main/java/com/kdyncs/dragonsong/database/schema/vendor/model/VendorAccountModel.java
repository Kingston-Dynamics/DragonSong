package com.kdyncs.dragonsong.database.schema.vendor.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public class VendorAccountModel {
    
    private UUID id;
    
    private String username;
    private String email;
    private String password;
    
    private OffsetDateTime createTimestamp;
    
}
