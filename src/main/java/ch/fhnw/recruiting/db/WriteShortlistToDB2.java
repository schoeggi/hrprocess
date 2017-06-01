package ch.fhnw.recruiting.db;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author Joel Lehner / 16.04.2017
 */

@Named
public class WriteShortlistToDB2 implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger(WriteShortlistToDB2.class.getName());
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
 
        LOGGER.info("Start: WriteShortlistToDB2 called!!!");
        int FormField_1Prio = (int) execution.getVariable("FormField_1Prio");
        int FormField_2Prio = (int) execution.getVariable("FormField_2Prio");
        int FormField_3Prio = (int) execution.getVariable("FormField_3Prio");
        
        int candidate_maxid = 0;
        int candidate_minid = 0;
        String sql_maxid = "SELECT MAX(id) as id FROM applicant";
        SqlRowSet rowSet_maxid = jdbcTemplate.queryForRowSet(sql_maxid);     
        while(rowSet_maxid.next())
        {
        	candidate_maxid = rowSet_maxid.getInt("id");
        	LOGGER.info("candidate_maxid: " +candidate_maxid);
        }
        String sql_minid = "SELECT MIN(id) as id FROM applicant";
        SqlRowSet rowSet_minid = jdbcTemplate.queryForRowSet(sql_minid);     
        while(rowSet_minid.next())
        {
        	candidate_minid = rowSet_minid.getInt("id");
        	LOGGER.info("candidate_minid: " +candidate_minid);
        }
        
        String sql_prio1 = "UPDATE applicant SET HIRINGPRIO=1 WHERE id="+FormField_1Prio;
        LOGGER.info("Executed SQL statement Prio 1:" +sql_prio1);
        jdbcTemplate.execute(sql_prio1);      	

        if (FormField_2Prio >= candidate_minid && FormField_2Prio <= candidate_maxid && FormField_2Prio != FormField_1Prio){
        	String sql_prio2 = "UPDATE applicant SET HIRINGPRIO=2 WHERE id="+FormField_2Prio;
            LOGGER.info("Executed SQL statement Prio 2:" +sql_prio2);
            jdbcTemplate.execute(sql_prio2); 
        }
        if (FormField_3Prio >= candidate_minid && FormField_3Prio <= candidate_maxid && FormField_3Prio != FormField_1Prio && FormField_3Prio != FormField_2Prio){
        	String sql_prio3 = "UPDATE applicant SET HIRINGPRIO=3 WHERE id="+FormField_3Prio;
            LOGGER.info("Executed SQL statement Prio 3:" +sql_prio3);
            jdbcTemplate.execute(sql_prio3); 
        }

        LOGGER.info("End: WriteShortlistToDB2 called!!!");
   
    }
}








