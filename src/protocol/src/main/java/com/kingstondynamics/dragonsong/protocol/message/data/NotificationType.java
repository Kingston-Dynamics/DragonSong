package com.kingstondynamics.dragonsong.protocol.message.data;

public enum NotificationType {
    
    // AUTHENTICATION
    AUTHENTICATION_SUCCESSFUL("AUTHEI0001"),
    AUTHENTICATION_FAILURE("AUTHEE0001"),
    ALREADY_AUTHENTICATED("AUTHEE0002"),
    API_KEY_INVALID("AUTHEE0003"),
    AUTHENTICATION_TIME_EXCEEDED("AUTHEE0004"),
    NOT_AUTHENTICATED("AUTHEE0005"),
    
    // CHANNEL CHAT
    POST_SUCCESSFUL("CHANEI0001"),
    SUBSCRIPTION_FAILURE("CHANEE0001"),
    UBSCRIPTION_SUCCESSFUL("CHANEI0001"),
    NOT_SUBSCRIBED_TO_CHANNEL("CHANEE0002"),
    
    // DIRECT CHAT
    DIREC_POST_SUCCESSFUL("DIRECI0001"),
    RECIPIENT_NOT_FOUND("DIRECE0001"),
    
    // APPLICATION SPECIFIC
    APPLICATION_NOT_DEPLOYED("APPLIE0000"),
    
    // SERVER SPECIFIC
    DEPLOYMENT_SHUT_DOWN("DISCOI0001");
    
    private final String code;
    
    NotificationType(String code) {
        this.code = code;
    }
    
    public static NotificationType fromValue(String code) {
        NotificationType[] types = NotificationType.values();
        
        for (NotificationType type : types) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
    
    public String getCode() {
        return code;
    }
    
}
