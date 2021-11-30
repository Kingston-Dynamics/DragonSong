package com.kingstondynamics.dragonsong.database.schema.vendor.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public class VendorDetailsModel {
    
    // Unique Identifier
    private UUID id;
    
    // Names
    private String firstname;
    private String lastname;
    
    // Numbers
    private String primaryTelephone;
    private String otherTelephone;
    
    // Create Timestamp
    private OffsetDateTime createTimestamp;
    
    // Foreign Keys?
    
}
