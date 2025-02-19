package code.challenge.infra.logging;

import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logmanager.Logger;

@ApplicationScoped
public class LoggingImpl implements code.challenge.app.logging.Logger {
    private final Logger logger = Logger.getLogger(LoggingImpl.class.getName());

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void warn(String message) {
        logger.warning(message);
    }

    @Override
    public void error(String message) {
        logger.severe(message);
    }
}
