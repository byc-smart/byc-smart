package io.github.byc.smart.base.core.support.beans;

import io.github.byc.smart.base.core.constant.Constants;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * {@link org.springframework.context.annotation.AnnotationBeanNameGenerator}
 *
 * <pre class="code">com.xyz.FooServiceImpl -&gt; fooService</pre>
 * @author : bamboo
 */
public class CustomBeanNameGenerator extends AnnotationBeanNameGenerator {
    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        String beanName = super.generateBeanName(definition,registry);
        if (beanName.endsWith(Constants.BEAN_NAME_SUFFIX)){
            // 只需要beanName前部分，丢弃Impl，形成新的beanName
            beanName = beanName.substring(0, beanName.length() - 4);
        }
        return beanName;
    }
}
