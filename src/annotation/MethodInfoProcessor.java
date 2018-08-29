package annotation;

import java.util.HashMap;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes(value = { "annotation.MethodInfo" })
public class MethodInfoProcessor extends AbstractProcessor {

	public MethodInfoProcessor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean process(Set<? extends TypeElement> arg0, RoundEnvironment arg1) {
		HashMap<String, String> map = new HashMap<String, String>();
        for (TypeElement te : arg0) {
            for (Element element : arg1.getElementsAnnotatedWith(te)) {
                MethodInfo methodInfo = element.getAnnotation(MethodInfo.class);
                map.put(element.getEnclosingElement().toString(), methodInfo.author());
            }
        }
        return false;
	}

}
