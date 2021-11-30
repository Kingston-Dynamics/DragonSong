-- View All DragonSong Services

USE DragonSong;


# SELECT * FROM Service;

SELECT Service.ServiceName, ServiceEnvironment.EnvironmentName, ServiceVersion.VersionName
FROM Service 
	JOIN ServiceEnvironment ON Service.ID = ServiceEnvironment.ServiceID
    JOIN ServiceVersion ON ServiceEnvironment.ID = ServiceVersion.EnvironmentID