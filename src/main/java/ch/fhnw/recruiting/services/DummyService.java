package ch.fhnw.recruiting.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


/**
 *
 * @author Joel Lehner / 16.04.2017
 */

@Named
public class DummyService implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger(DummyService.class.getName());
    

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        LOGGER.info("DummyService called!!!");

    }
}








