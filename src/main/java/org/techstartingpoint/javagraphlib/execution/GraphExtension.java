package org.techstartingpoint.javagraphlib.execution;

import org.techstartingpoint.javagraphlib.excomponents.Extension;
import org.techstartingpoint.javagraphlib.excomponents.Props;

/**
 * Interface that exposes how excomponents are created
 * @author Jose Alberto Guastavino
 *
 */
public interface GraphExtension extends Extension {

    public Props props(
            String componentBeanName,
            String fluxId,
            GraphEnvironment graphEnvironment,
            GraphRunner graphRunner) throws ClassNotFoundException;

}
