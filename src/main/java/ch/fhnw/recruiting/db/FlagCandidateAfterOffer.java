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
public class FlagCandidateAfterOffer implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger(FlagCandidateAfterOffer.class.getName());
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
 
        LOGGER.info("Start: FlagCandidateAfterOffer called!!!");
        int best_candidate_id = (int) execution.getVariable("best_candidate_id");
        boolean offer_accepted = (boolean) execution.getVariable("FormField_offer_accepted");
        String sql_flat_best_candidate = "";
        
        if (offer_accepted == true){
        	sql_flat_best_candidate = "UPDATE applicant SET HIRINGPRIO=0 WHERE id="+best_candidate_id;
        }
        else{
        	sql_flat_best_candidate = "UPDATE applicant SET HIRINGPRIO=99 WHERE id="+best_candidate_id;
        }
        LOGGER.info("Executed SQL statement sql_flat_best_candidate:" +sql_flat_best_candidate);
        jdbcTemplate.execute(sql_flat_best_candidate);      	
        LOGGER.info("End: FlagCandidateAfterOffer called!!!");
   
    }
}








