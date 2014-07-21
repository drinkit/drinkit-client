
package ua.kiev.naiv.drinkit.cocktail.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MixinAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(MixinAspect.class);

    @Around("@annotation(annotation)")
    public Object assignMixinToEntity(final ProceedingJoinPoint joinPoint, final JsonMixIn annotation) {
        Class<?> mixin = annotation.value();
        ObjectMapper mapper = new ObjectMapper();
        MethodSignature msig = (MethodSignature) joinPoint.getSignature();
        Class<?> targetClass = annotation.targetClass().equals(Object.class) ? msig.getMethod().getReturnType() :
                annotation.targetClass();
        mapper.addMixInAnnotations(targetClass, mixin);
        LOGGER.debug("Added mixin: '{}', targetClass: '{}', method: '{}'", mixin.getSimpleName(), targetClass.getSimpleName(),
                msig.getMethod().getName());
        try {
            mapper.writeValue(WebContext.getInstance().getResponse().getOutputStream(), joinPoint.proceed());
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
}