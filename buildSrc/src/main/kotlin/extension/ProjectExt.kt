package extension

import org.gradle.api.Project
import utils.getLocalProperty

/**
 * Obtain property declared on `$projectRoot/keystore.properties` file.
 *
 * @param propertyName the name of declared property
 */
fun Project.getLocalProperty(propertyName: String): String {
    return getLocalProperty(propertyName, this)
}