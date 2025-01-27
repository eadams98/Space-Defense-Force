package com.group.sdf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class TriggerCreate {
    
    @Autowired
    private DataSource dataSource;

	// Hardcoded values are used, but future will use dynamic properties. Might pull from environment ant use in the trigger creations. More d
	@PostConstruct
	public void createMaxBagUpgradesTrigger() {
        String sql = "CREATE TRIGGER trg_bag_upgrades_limit "
                   + "BEFORE INSERT ON bag_upgrades "
                   + "FOR EACH ROW "
                   + "BEGIN "
                   + "    DECLARE total INT; "
                   + "    SELECT COUNT(*) INTO total FROM bag_upgrades WHERE BAG_ID = NEW.BAG_ID; "
                   + "    IF total >= 10 THEN "
                   + "        SIGNAL SQLSTATE '45000' "
                   + "        SET MESSAGE_TEXT = 'Bag can contain a maximum of 10 upgrades.'; "
                   + "    END IF; "
                   + "END;";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Trigger created successfully!");
        } catch (Exception e) {
            System.err.println("Error creating trigger: " + e.getMessage());
        }
    }
    
    @PostConstruct 
    public void createMaxBagUnitsTrigger() {
        String sql = "CREATE TRIGGER trg_bag_units_limit "
                   + "BEFORE INSERT ON bag_units "
                   + "FOR EACH ROW "
                   + "BEGIN "
                   + "    DECLARE total INT; "
                   + "    SELECT COUNT(*) INTO total FROM bag_units WHERE BAG_ID = NEW.BAG_ID; "
                   + "    IF total >= 1 THEN "
                   + "        SIGNAL SQLSTATE '45000' "
                   + "        SET MESSAGE_TEXT = 'Bag can contain a maximum of 1 unit.'; "
                   + "    END IF; "
                   + "END;";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Trigger created successfully!");
        } catch (Exception e) {
            System.err.println("Error creating trigger: " + e.getMessage());
        }
    }
	
}
